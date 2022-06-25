package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgAction;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEvent;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgClass;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgClassDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgDomain;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgDomainDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgEra;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObject;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObjectDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgOrigin;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenario;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioSequence;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioSequenceDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioSequenceStep;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioSequenceStepDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionState;
import org.codemaker.objectmodeldiagramgenerator.domain.repositories.DescriptorRepository;
import org.codemaker.objectmodeldiagramgenerator.domain.valueobjects.IndexOutOfMaxIndex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgAction.create;
import static org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgAction.delete;
import static org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEvent.FINAL;
import static org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEvent.INITIAL;
import static org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgEra.future;
import static org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgEra.past;
import static org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgEra.present;
import static org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgOrigin.inside;
import static org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgOrigin.outside;

public class ScenarioSequenceService {

  private final BusinessEventService businessEventService;
  private final ScenarioService scenarioService;
  private final TransitionStateService transitionStateService;
  private final DescriptorRepository descriptorRepository;

  public ScenarioSequenceService(BusinessEventService businessEventService, ScenarioService scenarioService,
                                 TransitionStateService transitionStateService, DescriptorRepository descriptorRepository) {
    this.businessEventService = businessEventService;
    this.scenarioService = scenarioService;
    this.transitionStateService = transitionStateService;
    this.descriptorRepository = descriptorRepository;
  }

  public Set<OmgScenarioSequence> findScenarioSequences() {
    Set<OmgScenarioSequence> result = new HashSet<>();

    Set<OmgScenarioSequenceDescriptor> scenSeqDescriptors = descriptorRepository.findScenarioSequenceDescriptors();

    // Pass 1: As a preparation step, iterate over all steps from all scenario sequence descriptods, collect all the somehow defined or
    // referenced information, and store them as helper objects for later use.
    Set<HelperObject> helperObjects = new HashSet<>();
    for (OmgScenarioSequenceDescriptor scenSeqDescriptor : scenSeqDescriptors) {
      OmgTransitionState transitionState = transitionStateService.findTransitionStateMap()
              .get(scenSeqDescriptor.getTransitionStateDescriptorKey());
      for (int i = 0; i < scenSeqDescriptor.getScenarioSequenceStepDescriptors().size(); i++) {
        OmgScenarioSequenceStepDescriptor scenSeqStepDesc = scenSeqDescriptor.getScenarioSequenceStepDescriptors().get(i);
        OmgAction action = scenSeqStepDesc.action();
        OmgScenario scenario = businessEventService.findBusinessEventMap().get(scenSeqStepDesc.getBusinessEventDescriptorKey())
                .getScenario();
        for (OmgObjectDescriptor objDescriptor : scenSeqStepDesc.getObjectDescriptors()) {
          int scenarioStepIndex = i + 2; // +1 because the index counting starts at 1, and another +1 because of the initial step
          HelperObject helperObject = new HelperObject(objDescriptor, action, transitionState, scenario, scenarioStepIndex);
          helperObjects.add(helperObject);
        }
      }
    }

    // Pass 2: Now do the real work. For each defined scenario sequence create all the steps plus the initial and final step.
    for (OmgScenarioSequenceDescriptor scenSeqDescriptor : scenSeqDescriptors) {
      result.add(findScenarioSequence(scenSeqDescriptor, helperObjects));
    }

    return result;
  }

  private OmgScenarioSequence findScenarioSequence(OmgScenarioSequenceDescriptor scenSeqDescriptor, Set<HelperObject> helperObjects) {
    OmgTransitionState transitionState = transitionStateService.findTransitionStateMap()
            .get(scenSeqDescriptor.getTransitionStateDescriptorKey());
    OmgScenario scenario = businessEventService.findBusinessEventMap()
            .get(scenSeqDescriptor.getScenarioSequenceStepDescriptors().get(0).getBusinessEventDescriptorKey()).getScenario();

    // Find all the helper objects which represent objects which are explicitly defined (either as being created or deleted) in the
    // current scenario.
    Set<HelperObject> insideHelperObjects = helperObjects.stream()
            .filter(o -> o.transitionState.equals(transitionState) && o.scenario.equals(scenario)).collect(Collectors.toSet());

    // In addition to the objects which are explicitly defined in this scenario, we now have to find all the other objects which are
    // defined elsewhere and to which the explicitly defined objects could have a dependency. For this we transitively collect all the
    // other scenarios to which the current scenario depend on, and then collect all the helper objects from these other scenarios.
    Set<OmgScenario> transitiveDependeeScenarios = findTransitiveDependees(scenario, new HashSet<>());
    Set<HelperObject> allOutsideHelperObjects = helperObjects.stream()
            .filter(o -> o.transitionState.equals(transitionState) && transitiveDependeeScenarios.contains(o.scenario))
            .collect(Collectors.toSet());

    // So far we have collected all the helper objects which could become relevant for the current scenario sequence. But we still need
    // to narrow the set of all these helper objects down because a) maybe some of the helper objects are being referenced at all,
    // and b) there might still be conflicts like a reference leads to a deleted object, or the reference leads to an object which is
    // only defined later in the sequence. I think I could have also modeled all of this in the two sets filtered out of the complete
    // set of helper objects, but I am not sure if the resulting code would still be comprehensible later.
    Set<HelperObject> outsideHelperObjects = relevantOutsideHelperObjects(insideHelperObjects, allOutsideHelperObjects);

    // Now that we have collected all the needed objects for the given scenario, we can start creating the scenario and its steps. In
    // addition to the steps defined in the descriptors we also create an initial and a final step.
    String title = scenSeqDescriptor.getTitle();
    OmgScenarioSequence result = new OmgScenarioSequence(title, scenario, transitionState);

    // Prepare the counter for the overall number of scenario sequence steps.
    int scenarioStepCounter = 1;
    int maxScenarioStepCounter = scenSeqDescriptor.getScenarioSequenceStepDescriptors().size() + 2; // +2 because of initial and final step

    // Add the initial step to the resulting scenario sequence
    result.getScenarioSequenceSteps().add(enclosingStep(INITIAL, classMap(scenSeqDescriptor), insideHelperObjects, outsideHelperObjects,
            new IndexOutOfMaxIndex(scenarioStepCounter, maxScenarioStepCounter)));

    // Pass 1: Iterate over all the scenario sequence step descriptors to collect the information of how many steps are there for a given
    // business event. Store the information in a map for later convenient access.
    Map<String, Integer> businessEventDescriptorKeyMaxStepCounterMap = new HashMap<>();
    String businessEventDescriptorKey = null;
    int businessEventStepCounter = 0;
    for (int i = 0; i < scenSeqDescriptor.getScenarioSequenceStepDescriptors().size(); i++) {
      OmgScenarioSequenceStepDescriptor scenSeqStepDesc = scenSeqDescriptor.getScenarioSequenceStepDescriptors().get(i);
      if (!scenSeqStepDesc.getBusinessEventDescriptorKey().equals(businessEventDescriptorKey)) {
        // We found a new business event descriptor key. Therefore we store the counter counted so far for the corresponding business
        // event descriptor key, if there already is one.
        if (businessEventDescriptorKey != null) {
          businessEventDescriptorKeyMaxStepCounterMap.put(businessEventDescriptorKey, businessEventStepCounter);
        }
        businessEventDescriptorKey = scenSeqStepDesc.getBusinessEventDescriptorKey();
        businessEventStepCounter = 0;
      }
      businessEventStepCounter++;
      if (i == scenSeqDescriptor.getScenarioSequenceStepDescriptors().size() - 1) {
        // We reached the last step of the current business event descriptor. That means we also need to store this last business event
        // descriptor key and its corresponding counter in the map.
        businessEventDescriptorKeyMaxStepCounterMap.put(businessEventDescriptorKey, businessEventStepCounter);
      }
    }

    // Pass 2: Now do the real work. Iterate over all scenario sequence step descriptors and create the corresponding scenario sequence
    // step out of that. You could say that this is the main business logic in this service.
    businessEventDescriptorKey = null;
    businessEventStepCounter = 0;
    for (OmgScenarioSequenceStepDescriptor scenSeqStepDesc : scenSeqDescriptor.getScenarioSequenceStepDescriptors()) {
      if (businessEventDescriptorKey != null && !scenSeqStepDesc.getBusinessEventDescriptorKey().equals(businessEventDescriptorKey)) {
        businessEventStepCounter = 0;
      }
      businessEventDescriptorKey = scenSeqStepDesc.getBusinessEventDescriptorKey();
      scenarioStepCounter++;
      businessEventStepCounter++;
      IndexOutOfMaxIndex scenarioStepIndex = new IndexOutOfMaxIndex(scenarioStepCounter, maxScenarioStepCounter);
      IndexOutOfMaxIndex businessEventStepIndex = new IndexOutOfMaxIndex(businessEventStepCounter,
              businessEventDescriptorKeyMaxStepCounterMap.get(businessEventDescriptorKey));

      OmgScenarioSequenceStep scenSeqStep = normalStep(scenSeqStepDesc, classMap(scenSeqDescriptor), insideHelperObjects,
              outsideHelperObjects, scenarioStepIndex, businessEventStepIndex);
      result.getScenarioSequenceSteps().add(scenSeqStep);
    }

    // Before we leave: add the final step to the resulting scenario sequence
    result.getScenarioSequenceSteps().add(enclosingStep(FINAL, classMap(scenSeqDescriptor), insideHelperObjects, outsideHelperObjects,
            new IndexOutOfMaxIndex(maxScenarioStepCounter, maxScenarioStepCounter)));

    return result;
  }

  Set<HelperObject> relevantOutsideHelperObjects(Set<HelperObject> insideHelperObjects, Set<HelperObject> outsideHelperObjects) {
    Set<HelperObject> result = new HashSet<>();

    for (HelperObject insideHelperObject : insideHelperObjects) {
      // Dependees are only relevant when the current inside object is about to be created. If the inside object is to be deleted, then we
      // don't care of its dependees anymore.
      if (insideHelperObject.action.equals(create)) {
        for (String dependeeKey : insideHelperObject.dependeeKeys()) {
          // First check if the current dependee belongs to the inside objects. For this the dependee object must have the correct key,
          // must have been created, and it must have been defined in an earlier step.
          Set<HelperObject> insideDependeeHelperObjects = insideHelperObjects.stream()
                  .filter(o -> o.key().equals(dependeeKey) && o.action.equals(create) && o.stepIndex < insideHelperObject.stepIndex)
                  .collect(Collectors.toSet());
          if (insideDependeeHelperObjects.size() == 1) {
            continue;
          }

          // Ok, the dependee is not part of the inside objects. That means it must have been defined in one of the outside objects.
          Set<HelperObject> outsideDependeeHelperObjects = outsideHelperObjects.stream()
                  .filter(o -> o.key().equals(dependeeKey) && o.action.equals(create)).collect(Collectors.toSet());
          if (outsideDependeeHelperObjects.size() == 0) {
            throw new RuntimeException(
                    String.format("Error! Could not find any valid dependee object with foreign key \"%s\": %s", dependeeKey,
                            insideHelperObject));
          }
          if (outsideDependeeHelperObjects.size() > 1) {
            throw new RuntimeException(
                    String.format("Error! Found more than one valid dependee object with foreign key \"%s\": %s", dependeeKey,
                            insideHelperObject));
          }
          result.addAll(outsideDependeeHelperObjects);
        }
      }
    }
    return result;
  }

  private Set<OmgScenario> findTransitiveDependees(OmgScenario scenario, Set<OmgScenario> scenariosFoundSoFar) {
    if (scenario.getPredecessors().size() == 0) {
      return scenariosFoundSoFar;
    } else {
      Set<OmgScenario> result = null;
      for (OmgScenario predecessorScenario : scenario.getPredecessors()) {
        scenariosFoundSoFar.add(predecessorScenario);
        result = findTransitiveDependees(predecessorScenario, scenariosFoundSoFar);
      }
      return result;
    }
  }

  private OmgScenarioSequenceStep enclosingStep(OmgBusinessEvent businessEvent, Map<String, OmgClass> classMap,
                                                Set<HelperObject> insideHelperObjects, Set<HelperObject> outsideHelperObjects,
                                                IndexOutOfMaxIndex scenarioStepIndex) {
    OmgScenarioSequenceStep result = new OmgScenarioSequenceStep(businessEvent, scenarioStepIndex, new IndexOutOfMaxIndex(1, 1));
    OmgEra era = businessEvent.equals(INITIAL) ? future : past;

    Map<HelperObject, OmgObject> helperObjectObjectMap = new HashMap<>();

    // 1. Create all the objects, but don't care for the dependee objects for the moment.
    for (HelperObject insideHelperObject : insideHelperObjects) {
      OmgClass clazz = classMap.get(insideHelperObject.classKey());
      OmgObject insideObject = new OmgObject(insideHelperObject.key(), clazz, insideHelperObject.action, era, inside);
      insideObject.getPropertyMap().putAll(insideHelperObject.objectDescriptor.getPropertyMap());
      helperObjectObjectMap.put(insideHelperObject, insideObject);
    }
    for (HelperObject outsideHelperObject : outsideHelperObjects) {
      OmgClass clazz = classMap.get(outsideHelperObject.classKey());
      OmgObject outsideObject = new OmgObject(outsideHelperObject.key(), clazz, outsideHelperObject.action, era, outside);
      outsideObject.getPropertyMap().putAll(outsideHelperObject.objectDescriptor.getPropertyMap());
      helperObjectObjectMap.put(outsideHelperObject, outsideObject);
    }

    // 2. Now that we have created all the objects, we can take care of the dependee objects. We only need to take care of the objects
    // which are inside and are being created.
    for (HelperObject insideHelperObject : insideHelperObjects.stream().filter(o -> o.action.equals(create)).collect(Collectors.toSet())) {
      OmgObject insideObject = helperObjectObjectMap.get(insideHelperObject);
      for (String dependeeKey : insideHelperObject.dependeeKeys()) {
        Set<OmgObject> dependeeObjects = helperObjectObjectMap.values().stream()
                .filter(o -> o.getKey().equals(dependeeKey) && o.getAction().equals(create)).collect(Collectors.toSet());
        if (dependeeObjects.size() == 1) {
          OmgObject dependeeObject = dependeeObjects.stream().findFirst().get();
          insideObject.getDependeeObjects().add(dependeeObject);
        } else {
          throw new RuntimeException(String.format(
                  "Error! Could not find exactly one valid dependee object with foreign key \"%s\" while creating objects for %s step: %s",
                  dependeeKey, era, insideHelperObject));
        }
      }
    }

    // 3. We have collected and connected all the objects and have them still stored in the helperObjectObjectMap. The only thing which
    // is left is to add all these objects to the current scenario sequence step.
    result.getObjects().addAll(helperObjectObjectMap.values());
    return result;
  }

  private OmgScenarioSequenceStep normalStep(OmgScenarioSequenceStepDescriptor scenSeqStepDesc, Map<String, OmgClass> classMap,
                                             Set<HelperObject> insideHelperObjects, Set<HelperObject> outsideHelperObjects,
                                             IndexOutOfMaxIndex scenarioStepIndex, IndexOutOfMaxIndex businessEventStepIndex) {
    OmgScenarioSequenceStep result = new OmgScenarioSequenceStep(
            businessEventService.findBusinessEventMap().get(scenSeqStepDesc.getBusinessEventDescriptorKey()), scenarioStepIndex,
            businessEventStepIndex);

    // Collect all helper objects in different sets, depending on in which era their corresponding objects have to be created.
    // Start with the inside helper objects. This is pretty straightforward.
    Set<HelperObject> pastInsideHelperObjects = insideHelperObjects.stream().filter(o -> o.stepIndex < scenarioStepIndex.getIndex())
            .collect(Collectors.toSet());
    Set<HelperObject> presentInsideHelperObjects = insideHelperObjects.stream().filter(o -> o.stepIndex == scenarioStepIndex.getIndex())
            .collect(Collectors.toSet());
    Set<HelperObject> futureInsideHelperObjects = insideHelperObjects.stream().filter(o -> o.stepIndex > scenarioStepIndex.getIndex())
            .collect(Collectors.toSet());
    // Finish with the outside helper objects. For them we also need to know in which era they will appear. This depends on when such
    // outside helper object is referenced by an inside helper object as a dependee.
    Set<HelperObject> pastOutsideHelperObjects = new HashSet<>();
    Set<HelperObject> presentOutsideHelperObjects = new HashSet<>();
    Set<HelperObject> futureOutsideHelperObjects = new HashSet<>();
    for (HelperObject outsideHelperObject : outsideHelperObjects) {
      if (outsideHelperObject.action.equals(delete)) {
        // If an outside helper object is to be deleted, then it cannot be a candidate for being a dependee of some inside helper object.
        continue;
      }
      boolean foundAsDependee = false;
      for (HelperObject pastInsideHelperObject : pastInsideHelperObjects) {
        if (pastInsideHelperObject.dependeeKeys().contains(outsideHelperObject.key())) {
          pastOutsideHelperObjects.add(outsideHelperObject);
          foundAsDependee = true;
        }
      }
      if (!foundAsDependee) {
        for (HelperObject presentInsideHelperObject : presentInsideHelperObjects) {
          if (presentInsideHelperObject.dependeeKeys().contains(outsideHelperObject.key())) {
            presentOutsideHelperObjects.add(outsideHelperObject);
            foundAsDependee = true;
          }
        }
      }
      if (!foundAsDependee) {
        for (HelperObject futureInsideHelperObject : futureInsideHelperObjects) {
          if (futureInsideHelperObject.dependeeKeys().contains(outsideHelperObject.key())) {
            futureOutsideHelperObjects.add(outsideHelperObject);
          }
        }
      }
    }

    // Create all the objects out of the in total six different sets of helper objects. Ignore the dependee relations for the moment.
    Map<OmgObject, HelperObject> objectHelperObjectMap = new HashMap<>();
    objectHelperObjectMap.putAll(objectHelperObjectMap(pastInsideHelperObjects, classMap, past, inside));
    objectHelperObjectMap.putAll(objectHelperObjectMap(presentInsideHelperObjects, classMap, present, inside));
    objectHelperObjectMap.putAll(objectHelperObjectMap(futureInsideHelperObjects, classMap, future, inside));
    objectHelperObjectMap.putAll(objectHelperObjectMap(pastOutsideHelperObjects, classMap, past, outside));
    objectHelperObjectMap.putAll(objectHelperObjectMap(presentOutsideHelperObjects, classMap, present, outside));
    objectHelperObjectMap.putAll(objectHelperObjectMap(futureOutsideHelperObjects, classMap, future, outside));

    // But even with the dependees set correctly, we are already sure that our current step needs to have all the created objects.
    // Therefore we can already add all of them to the resulting scenario sequence step.
    result.getObjects().addAll(objectHelperObjectMap.keySet());

    // Now deal with the dependee relationships. For each object we check if they need their dependee objects, and if yes we temporarily
    // store them in a map for later use. (In the original version of this implementation I used the objectHelperObjectMap which I create
    // some lines above, but then had to realize that it confuses a map if you change the keys after you have added a key-value pair. The
    // keys are the OmgObjects, and I had changed them by adding dependee objects to them already while I was still looking for more
    // dependee objects).
    Map<OmgObject, Set<OmgObject>> objectDependeeObjectsMap = new HashMap<>();
    for (OmgObject object : result.getObjects()) {
      if (!(object.getAction().equals(create) && object.getOrigin().equals(inside))) {
        // We only need to find dependee objects for objects which are inside and created.
        continue;
      }
      HelperObject helperObject = objectHelperObjectMap.get(object);
      for (String dependeeKey : helperObject.dependeeKeys()) {
        boolean dependeeFound = false;
        OmgObject dependeeObject = null;
        for (OmgObject potentialDependeeObject : result.getObjects()) {
          if (!potentialDependeeObject.getKey().equals(dependeeKey)) {
            // The current potential dependee object cannot be a match because its key is different from the key we are looking for.
            continue;
          }
          HelperObject potentialDependeeHelperObject = objectHelperObjectMap.get(potentialDependeeObject);
          if (potentialDependeeObject.getAction().equals(create) && (potentialDependeeObject.getOrigin()
                  .equals(outside) || potentialDependeeHelperObject.stepIndex < helperObject.stepIndex)) {
            // A potential dependee object can only be the real dependee if this object is a) to be created, and b) either outside or has
            // been created in a previous step).
            if (!dependeeFound) {
              dependeeObject = potentialDependeeObject;
              dependeeFound = true;
            } else {
              // If we arrive here then we have found more than one possible dependeeObject. This is an error which will lead to an
              // exception some lines below.
              dependeeFound = false;
            }
          }
        }

        if (!dependeeFound) {
          throw new RuntimeException(String.format(
                  "Error! Could not find exactly one valid dependee object with key \"%s\" while trying to set dependee objects for " +
                          "object %s in step %s",
                  dependeeKey, object, result));
        }

        // Ok, the current potential dependee object really is a match. We store it for later use.
        Set<OmgObject> dependeeObjects = objectDependeeObjectsMap.computeIfAbsent(object, s -> new HashSet<>());
        dependeeObjects.add(dependeeObject);
      }
    }

    // Before we return the result we need to set the collected dependee objects to their corresponding dependent objects.
    for (OmgObject object : objectDependeeObjectsMap.keySet()) {
      object.getDependeeObjects().addAll(objectDependeeObjectsMap.get(object));
    }

    return result;
  }

  private Map<OmgObject, HelperObject> objectHelperObjectMap(Set<HelperObject> helperObjects, Map<String, OmgClass> classMap, OmgEra era,
                                                             OmgOrigin origin) {
    Map<OmgObject, HelperObject> result = new HashMap<>();
    for (HelperObject helperObject : helperObjects) {
      OmgObject object = new OmgObject(helperObject.key(), classMap.get(helperObject.classKey()), helperObject.action, era, origin);
      object.getPropertyMap().putAll(helperObject.objectDescriptor.getPropertyMap());
      result.put(object, helperObject);
    }
    return result;
  }

  private Map<String, OmgDomain> domainMap(OmgScenarioSequenceDescriptor scenarioSequenceDescriptor) {
    Map<String, OmgDomain> result = new HashMap<>();
    for (OmgDomainDescriptor domainDescriptor : scenarioSequenceDescriptor.getDomainDescriptors()) {
      result.put(domainDescriptor.getKey(), new OmgDomain(domainDescriptor.getKey(), domainDescriptor.getDisplayName()));
    }
    return result;
  }

  private Map<String, OmgClass> classMap(OmgScenarioSequenceDescriptor scenarioSequenceDescriptor) {
    Map<String, OmgClass> result = new HashMap<>();
    Map<String, OmgDomain> domainMap = domainMap(scenarioSequenceDescriptor);
    for (OmgClassDescriptor classDescriptor : scenarioSequenceDescriptor.getClassDescriptors()) {
      result.put(classDescriptor.getKey(),
              new OmgClass(classDescriptor.getKey(), classDescriptor.getDisplayName(), domainMap.get(classDescriptor.getDomainKey())));
    }
    return result;
  }

  /**
   * Stores all information about a given object in such a way that the service can then more or less conveniently do its business logic.
   */
  private static final class HelperObject {
    final OmgObjectDescriptor objectDescriptor;
    final OmgAction action;
    final OmgTransitionState transitionState;
    final OmgScenario scenario;
    /**
     * The index of the scenario sequence step in which this object has been defined. With this index it is easier to calculate if a given
     * object has been defined before or after or at the same step as another object. And this information is needed to know if such a given
     * object can have a dependency to this other object.
     */
    final int stepIndex;

    public HelperObject(OmgObjectDescriptor objectDescriptor, OmgAction action, OmgTransitionState transitionState, OmgScenario scenario,
                        int stepIndex) {
      this.objectDescriptor = objectDescriptor;
      this.action = action;
      this.transitionState = transitionState;
      this.scenario = scenario;
      this.stepIndex = stepIndex;
    }

    public String key() {
      return objectDescriptor.getKey();
    }

    public String classKey() {
      return objectDescriptor.getClassKey();
    }

    public Set<String> dependeeKeys() {
      return objectDescriptor.getDependeeKeys();
    }

    @Override
    public boolean equals(Object o) {
      if (this == o)
        return true;
      if (!(o instanceof HelperObject))
        return false;
      HelperObject helper = (HelperObject) o;
      return stepIndex == helper.stepIndex && objectDescriptor.equals(
              helper.objectDescriptor) && action == helper.action && transitionState.equals(helper.transitionState) && scenario.equals(
              helper.scenario);
    }

    @Override
    public int hashCode() {
      return Objects.hash(objectDescriptor, action, transitionState, scenario, stepIndex);
    }

    @Override
    public String toString() {
      return "HelperObject{" + "objectDescriptor=" + objectDescriptor + ", action=" + action + ", transitionState=" + transitionState +
              ", scenario=" + scenario + ", scenarioStepIndex=" + stepIndex + '}';
    }
  }
}
