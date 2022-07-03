package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgAction;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEvent;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgClass;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgDomain;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgEra;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObject;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgOrigin;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenario;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioSequence;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioSequenceStep;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionState;
import org.codemaker.objectmodeldiagramgenerator.domain.valueobjects.IndexOutOfMaxIndex;
import org.codemaker.objectmodeldiagramgenerator.domain.valueobjects.PumlDiagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgAction.create;
import static org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgAction.delete;
import static org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgEra.future;
import static org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgEra.past;
import static org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgEra.present;
import static org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgOrigin.inside;
import static org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgOrigin.outside;

public class ScenarioSequencePumlDiagramService {

  private final ScenarioSequenceService scenarioSequenceService;

  private final Map<String, String> objectBackgroundColorMap;
  private final Map<String, String> objectTextColorMap;
  private final Map<String, String> relationColorMap;

  public ScenarioSequencePumlDiagramService(ScenarioSequenceService scenarioSequenceService) {
    this.scenarioSequenceService = scenarioSequenceService;

    objectBackgroundColorMap = new HashMap<>();
    objectBackgroundColorMap.put(actionEraOriginKey(create, past, inside), "DDFFCC");
    objectBackgroundColorMap.put(actionEraOriginKey(create, past, outside), "EEEEEE");
    objectBackgroundColorMap.put(actionEraOriginKey(create, present, inside), "55EE00");
    objectBackgroundColorMap.put(actionEraOriginKey(create, present, outside), "EEEEEE");
    objectBackgroundColorMap.put(actionEraOriginKey(create, future, inside), "white");
    objectBackgroundColorMap.put(actionEraOriginKey(create, future, outside), "white");
    objectBackgroundColorMap.put(actionEraOriginKey(delete, past, inside), "FFE0E0");
    objectBackgroundColorMap.put(actionEraOriginKey(delete, past, outside), "darkgrey");
    objectBackgroundColorMap.put(actionEraOriginKey(delete, present, inside), "FF7766");
    objectBackgroundColorMap.put(actionEraOriginKey(delete, present, outside), "pink");
    objectBackgroundColorMap.put(actionEraOriginKey(delete, future, inside), "white");
    objectBackgroundColorMap.put(actionEraOriginKey(delete, future, outside), "white");

    objectTextColorMap = new HashMap<>();
    objectTextColorMap.put(actionEraOriginKey(create, past, inside), "black");
    objectTextColorMap.put(actionEraOriginKey(create, past, outside), "black");
    objectTextColorMap.put(actionEraOriginKey(create, present, inside), "black");
    objectTextColorMap.put(actionEraOriginKey(create, present, outside), "black");
    objectTextColorMap.put(actionEraOriginKey(create, future, inside), "white");
    objectTextColorMap.put(actionEraOriginKey(create, future, outside), "white");
    objectTextColorMap.put(actionEraOriginKey(delete, past, inside), "black");
    objectTextColorMap.put(actionEraOriginKey(delete, past, outside), "black");
    objectTextColorMap.put(actionEraOriginKey(delete, present, inside), "black");
    objectTextColorMap.put(actionEraOriginKey(delete, present, outside), "black");
    objectTextColorMap.put(actionEraOriginKey(delete, future, inside), "white");
    objectTextColorMap.put(actionEraOriginKey(delete, future, outside), "white");

    relationColorMap = new HashMap<>();
    relationColorMap.put(actionEraOriginKey(create, past, inside), "555555");
    relationColorMap.put(actionEraOriginKey(create, past, outside), "pink");
    relationColorMap.put(actionEraOriginKey(create, present, inside), "44BB00");
    relationColorMap.put(actionEraOriginKey(create, present, outside), "pink");
    relationColorMap.put(actionEraOriginKey(create, future, inside), "white");
    relationColorMap.put(actionEraOriginKey(create, future, outside), "pink");
    relationColorMap.put(actionEraOriginKey(delete, past, inside), "EEB3B3");
    relationColorMap.put(actionEraOriginKey(delete, past, outside), "pink");
    relationColorMap.put(actionEraOriginKey(delete, present, inside), "FF7766");
    relationColorMap.put(actionEraOriginKey(delete, present, outside), "pink");
    relationColorMap.put(actionEraOriginKey(delete, future, inside), "pink");
    relationColorMap.put(actionEraOriginKey(delete, future, outside), "pink");
  }

  public List<PumlDiagram> createDiagrams() {
    List<PumlDiagram> result = new ArrayList<>();

    Set<OmgScenarioSequence> scenarioSequences = scenarioSequenceService.findScenarioSequences();
    for (OmgScenarioSequence scenarioSequence : scenarioSequences) {
      List<OmgScenarioSequenceStep> scenarioSequenceSteps = scenarioSequence.getScenarioSequenceSteps();
      for (OmgScenarioSequenceStep scenarioSequenceStep : scenarioSequenceSteps) {
        result.add(createDiagram(scenarioSequence, scenarioSequenceStep));
      }
    }

    return result;
  }

  private PumlDiagram createDiagram(OmgScenarioSequence scenarioSequence, OmgScenarioSequenceStep scenarioSequenceStep) {
    String diagramName = diagramName(scenarioSequence, scenarioSequenceStep);
    StringBuilder diagramContentBuilder = new StringBuilder();
    diagramContentBuilder.append(header(diagramName));
    diagramContentBuilder.append("\n");
    diagramContentBuilder.append(title(scenarioSequence, scenarioSequenceStep));
    diagramContentBuilder.append("\n");
    diagramContentBuilder.append(entities(scenarioSequenceStep));
    diagramContentBuilder.append("\n");
    diagramContentBuilder.append(relations(scenarioSequenceStep));
    diagramContentBuilder.append("\n");
    diagramContentBuilder.append(footer());
    diagramContentBuilder.append("\n");
    return new PumlDiagram(diagramName, diagramContentBuilder.toString());
  }

  private String actionEraOriginKey(OmgAction action, OmgEra era, OmgOrigin origin) {
    return action + "_" + era + "_" + origin;
  }

  private String fullObjectKey(OmgObject object) {
    return object.getClazz().getDomain().getKey() + "_" + object.getClazz().getKey() + "_" + object.getKey();
  }

  private String diagramName(OmgScenarioSequence scenarioSequence, OmgScenarioSequenceStep scenarioSequenceStep) {
    StringBuilder result = new StringBuilder();
    OmgBusinessEvent businessEvent = scenarioSequenceStep.getBusinessEvent();

    result.append(scenarioSequence.getTitle());
    result.append("_");
    result.append(scenarioSequence.getScenario().getKey());
    result.append("_");
    result.append(scenarioSequence.getTransitionState().getKey());
    result.append("_");
    result.append(String.format("%03d", scenarioSequenceStep.getScenarioStepIndex().getIndex()));
    result.append("_");
    if (businessEvent.equals(OmgBusinessEvent.INITIAL)) {
      result.append("initial");
    } else if (businessEvent.equals(OmgBusinessEvent.FINAL)) {
      result.append("final");
    } else {
      result.append(businessEvent.getKey());
      result.append("_");
      result.append(String.format("%03d", scenarioSequenceStep.getBusinessEventStepIndex().getIndex()));
    }

    return result.toString();
  }

  /**
   * Filters out all the objects from a set which are duplicates, at least when only looking at the objects' keys. That we can have
   * duplicates is not a bug, but a feature. Depending on the original object definition provided by the repositories it can happen that we
   * have both an object representing that this object is to be created as well as to be deleted as well. And this happens if we first add
   * an object (typically outside the current scenario, but in an earlier scenario), and then we delete this very object inside the current
   * scenario. Depending on the other attributes "era" and "origin" this method decides which objects becomes the "winner".
   */
  private Set<OmgObject> filterDuplicatesFromObjects(Set<OmgObject> objects) {
    Set<OmgObject> result = new HashSet<>(objects);

    for (OmgObject object : objects) {
      for (OmgObject otherObject : objects) {
        if (object != otherObject && object.getKey().equals(otherObject.getKey())) {
          // We found a duplicate. Now we have to decide which object we need to remove from the result.
          OmgObject toBeRemovedObject = null; // also needed for the consistency check at the end of this if-else cascade
          if (object.getEra().equals(past) && otherObject.getEra().equals(past)) {
            if (object.getAction().equals(create) && otherObject.getAction().equals(delete)) {
              toBeRemovedObject = object;
            } else if (object.getAction().equals(delete) && otherObject.getAction().equals(create)) {
              toBeRemovedObject = otherObject;
            }
          } else if (object.getEra().equals(past) && otherObject.getEra().equals(present)) {
            toBeRemovedObject = object;
          } else if (object.getEra().equals(past) && otherObject.getEra().equals(future)) {
            toBeRemovedObject = otherObject;
          } else if (object.getEra().equals(present) && otherObject.getEra().equals(past)) {
            toBeRemovedObject = otherObject;
          } else if (object.getEra().equals(present) && otherObject.getEra().equals(present)) {
            if (object.getAction().equals(create) && otherObject.getAction().equals(delete)) {
              toBeRemovedObject = object;
            } else if (object.getAction().equals(delete) && otherObject.getAction().equals(create)) {
              toBeRemovedObject = otherObject;
            }
          } else if (object.getEra().equals(present) && otherObject.getEra().equals(future)) {
            toBeRemovedObject = otherObject;
          } else if (object.getEra().equals(future) && otherObject.getEra().equals(past)) {
            toBeRemovedObject = object;
          } else if (object.getEra().equals(future) && otherObject.getEra().equals(present)) {
            toBeRemovedObject = object;
          } else if (object.getEra().equals(future) && otherObject.getEra().equals(future)) {
            if (object.getAction().equals(create) && otherObject.getAction().equals(delete)) {
              toBeRemovedObject = otherObject;
            } else if (object.getAction().equals(delete) && otherObject.getAction().equals(create)) {
              toBeRemovedObject = object;
            }
          }
          // Do some consistency check
          if (toBeRemovedObject != null) {
            result.remove(toBeRemovedObject);
          } else {
            throw new RuntimeException(
                    String.format("Error. Could not decide on which object with same key to remove. Object 1: %s, " + "object 2: %s",
                            object, otherObject));
          }
        }
      }
    }

    return result;
  }

  private String header(String diagramName) {
    StringBuilder result = new StringBuilder();

    result.append("@startuml " + diagramName + "\n");
    result.append("\n");
    result.append("<style>\n");
    result.append("    Shadowing false\n");
    result.append("    Rectangle {\n");
    result.append("        FontSize 18\n");
    result.append("        FontStyle bold\n");
    result.append("        HorizontalAlignment left\n");
    result.append("        LineThickness 0\n");
    result.append("        RoundCorner 20\n");
    result.append("    }\n");
    result.append("    Object {\n");
    result.append("        FontSize 20\n");
    result.append("        FontStyle normal\n");
    result.append("        HorizontalAlignment left\n");
    result.append("        LineColor white\n");
    result.append("        LineThickness 3\n");
    result.append("        RoundCorner 20\n");
    result.append("    }\n");
    result.append("    Arrow {\n");
    result.append("        LineThickness 3\n");
    result.append("    }\n");
    result.append("    Title {\n");
    result.append("        BackgroundColor white\n");
    result.append("        FontColor darkblue\n");
    result.append("        FontSize 20\n");
    result.append("        FontStyle normal\n");
    result.append("        HorizontalAlignment left\n");
    result.append("        LineColor darkblue\n");
    result.append("        LineThickness 2\n");
    result.append("        Margin 50\n");
    result.append("        Padding 20\n");
    result.append("    }\n");
    result.append("</style>\n");

    return result.toString();
  }

  private String title(OmgScenarioSequence scenarioSequence, OmgScenarioSequenceStep scenarioSequenceStep) {
    StringBuilder result = new StringBuilder();

    OmgTransitionState transitionState = scenarioSequence.getTransitionState();
    OmgScenario scenario = scenarioSequence.getScenario();
    OmgBusinessEvent businessEvent = scenarioSequenceStep.getBusinessEvent();

    result.append("title \\\n");
    result.append("<b>Sequence:</b> " + scenarioSequence.getTitle() + "\\n\\\n");
    result.append("<b>Scenario:</b> " + scenario.getKey() + " - " + scenario.getDescription() + "\\n\\\n");
    result.append("<b>Transition State:</b> " + transitionState.getKey() + " - " + transitionState.getDescription() + "\\n\\n\\\n");

    if (businessEvent.equals(OmgBusinessEvent.INITIAL)) {
      result.append("<b>Initial setup</b>\\n\\n\\\n");
    } else if (businessEvent.equals(OmgBusinessEvent.FINAL)) {
      result.append("<b>Final object model</b>\\n\\n\\\n");
    } else {
      IndexOutOfMaxIndex businessEventStepIndex = scenarioSequenceStep.getBusinessEventStepIndex();
      result.append("<b>Business Event:</b> " + businessEvent.getKey() + " - " + businessEvent.getDescription() + String.format(
              " (Step %03d of %03d)", businessEventStepIndex.getIndex(), businessEventStepIndex.getMaxIndex()) + "\\n\\n\\\n");
    }
    IndexOutOfMaxIndex scenarioStepIndex = scenarioSequenceStep.getScenarioStepIndex();
    result.append(String.format("Diagram %03d of %03d\n", scenarioStepIndex.getIndex(), scenarioStepIndex.getMaxIndex()));
    return result.toString();
  }

  private String entities(OmgScenarioSequenceStep scenarioSequenceStep) {
    StringBuilder result = new StringBuilder();

    //    Set<OmgObject> allObjects = scenarioSequenceStep.getObjects();
    Set<OmgObject> relevantObjects = filterDuplicatesFromObjects(scenarioSequenceStep.getObjects());

    // 1. Find all the domains, sort them by their key, and then store them in a list so that we can iterate over it.
    Set<OmgDomain> domains = new HashSet<>();
    for (OmgObject object : relevantObjects) {
      domains.add(object.getClazz().getDomain());
    }
    List<OmgDomain> sortedDomains = domains.stream().sorted((d1, d2) -> d1.getKey().compareTo(d2.getKey())).collect(Collectors.toList());

    // 2. Iterate over the sorted domains and create the corresponding puml output.
    for (OmgDomain domain : sortedDomains) {
      String domainKey = domain.getKey();
      result.append("rectangle \"" + domain.getDisplayName() + "\" as " + domainKey + " #DDDDDD {\n");

      // 3. Find all the classes relevant for the current domain, sort them by their class key, and then them in a list so that we can
      // iterate over it as well.
      Set<OmgClass> classes = new HashSet<>();
      for (OmgObject object : relevantObjects) {
        if (object.getClazz().getDomain().equals(domain)) {
          classes.add(object.getClazz());
        }
      }
      List<OmgClass> sortedClasses = classes.stream().sorted((c1, c2) -> c1.getKey().compareTo(c2.getKey())).collect(Collectors.toList());

      // 4. Iterate over the sorted classes and create the corresponding puml output.
      for (OmgClass clazz : sortedClasses) {
        String classKey = domainKey + "_" + clazz.getKey();
        result.append("    rectangle \"" + clazz.getDisplayName() + "\" as " + classKey + " #white {\n");

        // 5. Find all the objects which belong to the current class (and thus also domain), sort them by their object key, and then
        // store them in a list so that can - you guessed it already - iterate over it as well.
        Set<OmgObject> objects = relevantObjects.stream().filter(o -> o.getClazz().equals(clazz))
                .filter(o -> o.getClazz().getDomain().equals(domain)).collect(Collectors.toSet());
        List<OmgObject> sortedObjects = objects.stream().sorted((o1, o2) -> o1.getKey().compareTo(o2.getKey()))
                .collect(Collectors.toList());

        // 6. Iterate over all the objects and create the corresponding puml output.
        for (OmgObject object : sortedObjects) {
          result.append(object(object));
        }
        result.append("    }\n");
      }
      result.append("}\n");
    }

    return result.toString();
  }

  private String object(OmgObject object) {
    StringBuilder result = new StringBuilder();
    String objectKey = fullObjectKey(object);

    // 1. Determine the color of the object's background and text
    String actionEraOriginKey = actionEraOriginKey(object.getAction(), object.getEra(), object.getOrigin());
    String objectBackgroundColor = objectBackgroundColorMap.get(actionEraOriginKey);
    String objectTextColor = objectTextColorMap.get(actionEraOriginKey);

    // 2. Create puml output for the current object
    result.append(
            "        object \"<color:" + objectTextColor + "><b>" + object.getKey() + "</b></color>\" as " + objectKey + " #" + objectBackgroundColor + " {\n");

    // 3. Create puml output for all the properties of the current object. But first sort the keys.
    List<String> sortedPropertyKeys = object.getPropertyMap().keySet().stream().sorted().collect(Collectors.toList());
    for (String propertyKey : sortedPropertyKeys) {
      String propertyValue = object.getPropertyMap().get(propertyKey);
      result.append("            <color:" + objectTextColor + ">" + propertyKey + " = \"" + propertyValue + "\"</color>\n");
    }
    result.append("        }\n");

    return result.toString();
  }

  private String relations(OmgScenarioSequenceStep scenarioSequenceStep) {
    StringBuilder result = new StringBuilder();
    Set<OmgObject> relevantObjects = filterDuplicatesFromObjects(scenarioSequenceStep.getObjects());
    List<OmgObject> sortedRelevantObjects = relevantObjects.stream().sorted((o1, o2) -> o1.getKey().compareTo(o2.getKey()))
            .collect(Collectors.toList());

    // Iterate over the sorted list of objects and then over the sorted list of dependee objects and then create the corresponding puml
    // output for their relations to their dependees.
    for (OmgObject object : sortedRelevantObjects) {
      List<OmgObject> sortedDependeeObjects = object.getDependeeObjects().stream()
              .sorted((o1, o2) -> fullObjectKey(o1).compareTo(fullObjectKey(o2))).collect(Collectors.toList());
      for (OmgObject dependeeObject : sortedDependeeObjects) {
        boolean hidden = object.getEra().equals(future) || object.getOrigin().equals(outside);
        String relationArrow = hidden ? " --[hidden]--> " : " ----> ";
        String relationColor = relationColorMap.get(actionEraOriginKey(object.getAction(), object.getEra(), object.getOrigin()));
        result.append(fullObjectKey(object) + relationArrow + fullObjectKey(dependeeObject));
        if (!hidden) {
          result.append(" #" + relationColor);
        }
        result.append("\n");
      }
    }
    return result.toString();
  }

  private String footer() {
    return "@enduml\n";
  }
}
