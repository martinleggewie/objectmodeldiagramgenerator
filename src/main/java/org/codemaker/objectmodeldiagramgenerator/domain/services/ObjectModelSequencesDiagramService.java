package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEvent;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgDefinition;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObject;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObjectModel;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObjectModel.Action;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObjectModelSequence;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenario;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionState;
import org.codemaker.objectmodeldiagramgenerator.domain.valueobjects.PumlDiagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ObjectModelSequencesDiagramService {

  public static enum Mode {
    gradually,
    everything;
  }

  public static enum Phase {
    pastCreate,
    pastRemove,
    currentCreate,
    currentRemove,
    future,
    outside
  }

  public static class RelationDescriptor {
    private final OmgObject from;
    private final OmgObject to;

    public RelationDescriptor(OmgObject from, OmgObject to) {
      this.from = from;
      this.to = to;
    }

    public OmgObject getFrom() {
      return from;
    }

    public OmgObject getTo() {
      return to;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o)
        return true;
      if (!(o instanceof RelationDescriptor))
        return false;
      RelationDescriptor that = (RelationDescriptor) o;
      return getFrom().equals(that.getFrom()) && getTo().equals(that.getTo());
    }

    @Override
    public int hashCode() {
      return Objects.hash(getFrom(), getTo());
    }
  }

  private final OmgDefinition definition;

  public ObjectModelSequencesDiagramService(OmgDefinition definition) {
    this.definition = definition;
  }

  public List<PumlDiagram> createDiagrams(Mode mode) {
    List<PumlDiagram> result = new ArrayList<>();

    for (OmgObjectModelSequence objectModelSequence : definition.getObjectModelSequences()) {
      OmgTransitionState transitionState = objectModelSequence.getTransitionState();

      Map<OmgScenario, List<OmgObjectModel>> scenarioObjectModelMap = scenarioObjectModelMap(objectModelSequence);
      for (OmgScenario scenario : scenarioObjectModelMap.keySet()) {
        List<OmgObjectModel> previousObjectModels = new ArrayList<>();
        List<OmgObjectModel> futureObjectModels = new ArrayList<>(scenarioObjectModelMap.get(scenario));

        int objectModelCounter = 0;
        OmgBusinessEvent previousBusinessEvent = null;

        for (OmgObjectModel objectModel : scenarioObjectModelMap.get(scenario)) {

          futureObjectModels.remove(objectModel);

          OmgBusinessEvent currentBusinessEvent = objectModel.getBusinessEvent();
          if (!currentBusinessEvent.equals(previousBusinessEvent)) {
            objectModelCounter = 0;
            previousBusinessEvent = currentBusinessEvent;
          }
          StringBuilder builder = new StringBuilder();
          objectModelCounter++;

          String diagramName = diagramName(objectModelSequence, objectModel, objectModelCounter, mode);

          builder.append(header(diagramName));
          builder.append(title(objectModelSequence, objectModel, objectModelCounter));
          builder.append(objects(objectModel, previousObjectModels, futureObjectModels, mode));
          builder.append(relations(objectModel, previousObjectModels, futureObjectModels, mode));
          builder.append(footer());

          PumlDiagram diagram = new PumlDiagram(diagramName, builder.toString());
          result.add(diagram);

          previousObjectModels.add(objectModel);
        }
      }
    }

    return result;
  }

  private Map<OmgScenario, List<OmgObjectModel>> scenarioObjectModelMap(OmgObjectModelSequence objectModelSequence) {
    Map<OmgScenario, List<OmgObjectModel>> result = new HashMap<>();

    for (OmgObjectModel objectModel : objectModelSequence.getObjectModels()) {
      OmgScenario scenario = objectModel.getBusinessEvent().getScenario();
      List<OmgObjectModel> objectModels = result.computeIfAbsent(scenario, k -> new ArrayList<>());
      objectModels.add(objectModel);
    }

    return result;
  }

  private String diagramName(OmgObjectModelSequence objectModelSequence, OmgObjectModel objectModel, int objectModelCounter, Mode mode) {
    StringBuilder result = new StringBuilder();

    result.append(objectModelSequence.getName());
    result.append("_");
    result.append(mode);
    result.append("_");
    result.append(objectModel.getBusinessEvent().getScenario().getKey());
    result.append("_");
    result.append(objectModelSequence.getTransitionState().getKey());
    result.append("_");
    result.append(objectModel.getBusinessEvent().getKey());
    result.append("_");
    result.append(String.format("%03d", objectModelCounter));

    return result.toString();
  }

  private String header(String diagramName) {
    StringBuilder result = new StringBuilder();

    result.append("\n");
    result.append("@startuml " + diagramName + "\n");
    result.append("\n");
    result.append("top to bottom direction\n");
    result.append("skinparam shadowing false\n");
    result.append("skinparam componentStyle uml2\n");
    result.append("skinparam roundCorner 10\n");
    result.append("\n");
    result.append("skinparam rectangleRoundCorner 20\n");
    result.append("skinparam rectangleFontStyle bold\n");
    result.append("skinparam rectangleFontSize 18\n");
    result.append("skinparam rectangleBorderColor #white\n");
    result.append("skinparam rectangleBackgroundColor #white\n");
    result.append("\n");
    result.append("skinparam objectAttributeFontSize 16\n");
    result.append("skinparam objectAttributeFontColor #white\n");
    result.append("skinparam objectBackgroundColor #white\n");
    result.append("skinparam objectBorderColor #white\n");
    result.append("skinparam objectBorderThickness 3\n");
    result.append("skinparam objectFontSize 20\n");
    result.append("skinparam objectFontStyle bold\n");
    result.append("skinparam objectFontColor #white\n");
    result.append("\n");
    result.append("skinparam arrowColor #black\n");
    result.append("skinparam arrowFontSize 18\n");
    result.append("skinparam arrowThickness 3\n");
    result.append("\n");
    result.append("skinparam titleFontSize 22\n");
    result.append("skinparam titleFontStyle normal\n");
    result.append("skinparam titleBackgroundColor cornsilk\n");
    result.append("skinparam titleBorderThickness 3\n");
    result.append("skinparam titleBorderColor black\n");
    result.append("\n");
    result.append("\n");

    return result.toString();
  }

  private String title(OmgObjectModelSequence objectModelSequence, OmgObjectModel objectModel, int objectModelCounter) {
    StringBuilder result = new StringBuilder();

    OmgTransitionState transitionState = objectModelSequence.getTransitionState();
    OmgScenario scenario = objectModel.getBusinessEvent().getScenario();
    OmgBusinessEvent businessEvent = objectModel.getBusinessEvent();

    result.append("title \\n");
    result.append("<b>Sheet:</b> " + objectModelSequence.getName() + "\\n\\n");
    result.append("<b>Transition State:</b> " + transitionState.getKey() + " - " + transitionState.getDescription() + "\\n\\n");
    result.append("<b>Scenario:</b> " + scenario.getKey() + " - " + scenario.getDescription() + "\\n\\n");
    result.append("<b>Business Event:</b> " + businessEvent.getKey() + " - " + businessEvent.getDescription() + "\\n\\n");
    result.append("<b>Step:</b> " + objectModelCounter + "\\n\\n\n\n");

    return result.toString();
  }

  private String objects(OmgObjectModel objectModel, List<OmgObjectModel> previousObjectModels, List<OmgObjectModel> futureObjectModels,
                         Mode mode) {
    StringBuilder result = new StringBuilder();
    List<OmgObject> dependeeObjectsSoFar = new ArrayList<>();
    List<OmgObject> removedObjectsSoFar = new ArrayList<>();

    // Collect all the objects of this scenario in one flattened list so that we can later check if a given object is defined in this
    // scenario or not.
    List<OmgObject> allObjectsInThisScenario = new ArrayList<>(objectModel.getObjects());
    for (OmgObjectModel previousObjectModel : previousObjectModels) {
      allObjectsInThisScenario.addAll(previousObjectModel.getObjects());
    }
    for (OmgObjectModel futureObjectModel : futureObjectModels) {
      allObjectsInThisScenario.addAll(futureObjectModel.getObjects());
    }

    // Draw the objects from the given object model, and also their dependee objects in case they are defined outside this scenario
    Action action = objectModel.getAction();
    for (OmgObject object : objectModel.getObjects()) {
      result.append(object(object, action.equals(Action.create) ? Phase.currentCreate : Phase.currentRemove));
      if (action.equals(Action.delete)) {
        removedObjectsSoFar.add(object);
      }
      for (OmgObject dependeeObject : object.getDependeeObjects()) {
        if (!allObjectsInThisScenario.contains(dependeeObject) && !dependeeObjectsSoFar.contains(dependeeObject)) {
          result.append(object(dependeeObject, Phase.outside));
          dependeeObjectsSoFar.add(dependeeObject);
        }
      }
    }

    // Draw the objects which had been defined in an earlier object model of the same scenario, and also their dependee objects in case
    // they are defined outside this scenario
    for (OmgObjectModel previousObjectModel : previousObjectModels) {
      for (OmgObject previousObject : previousObjectModel.getObjects()) {
        result.append(object(previousObject, removedObjectsSoFar.contains(previousObject) ? Phase.pastRemove : Phase.pastCreate));

        for (OmgObject dependeeObject : previousObject.getDependeeObjects()) {
          if (!allObjectsInThisScenario.contains(dependeeObject) && !dependeeObjectsSoFar.contains(dependeeObject)) {
            result.append(object(dependeeObject, Phase.outside));
            dependeeObjectsSoFar.add(dependeeObject);
          }
        }
      }
    }

    if (mode.equals(Mode.everything)) {
      // Draw the objects which will be defined in a future object model of the same scenario, and also their dependee objects in case
      // they are defined outside this scenario
      for (OmgObjectModel futureObjectModel : futureObjectModels) {
        for (OmgObject futureObject : futureObjectModel.getObjects()) {
          result.append(object(futureObject, Phase.future));

          for (OmgObject dependeeObject : futureObject.getDependeeObjects()) {
            if (!allObjectsInThisScenario.contains(dependeeObject) && !dependeeObjectsSoFar.contains(dependeeObject)) {
              result.append(object(dependeeObject, Phase.future));
              dependeeObjectsSoFar.add(dependeeObject);
            }
          }
        }
      }
    }

    return result.toString();
  }

  private String object(OmgObject object, Phase phase) {
    StringBuilder result = new StringBuilder();

    String domainDisplayName = object.getClazz().getDomain().getDisplayName();
    String domainKey = object.getClazz().getDomain().getKey();
    String classDisplayName = object.getClazz().getDisplayName();
    String classKey = object.getClazz().getKey();
    String objectDisplayName = object.displayName();
    String objectKey = object.getKey();

    result.append("rectangle \"" + domainDisplayName + "\" as " + domainKey + " #DDDDDD {\n");
    result.append("    rectangle \"" + classDisplayName + "\" as " + classKey + " #white {\n");
    if (phase.equals(Phase.currentCreate)) {
      result.append("        object \"<color:black>" + objectDisplayName + "</color>\" as " + objectKey + " #palegreen {\n");
      for (String propertyKey : object.getPropertyMap().keySet()) {
        result.append("            <color:black>" + propertyKey + " = \"" + object.getPropertyMap().get(propertyKey) + "\"</color>\n");
      }
    } else if (phase.equals(Phase.currentRemove)) {
      result.append("        object \"<color:black>" + objectDisplayName + "</color>\" as " + objectKey + " #tomato {\n");
      for (String propertyKey : object.getPropertyMap().keySet()) {
        result.append("            <color:black>" + propertyKey + " = \"" + object.getPropertyMap().get(propertyKey) + "\"</color>\n");
      }
    } else if (phase.equals(Phase.future)) {
      result.append("        object \"" + objectDisplayName + "\" as " + objectKey + " {\n");
      for (String propertyKey : object.getPropertyMap().keySet()) {
        result.append("            " + propertyKey + " = \"" + object.getPropertyMap().get(propertyKey) + "\"\n");
      }
    } else if (phase.equals(Phase.pastCreate)) {
      result.append("        object \"<color:black>" + objectDisplayName + "</color>\" as " + objectKey + " #E8F0FF {\n");
      for (String propertyKey : object.getPropertyMap().keySet()) {
        result.append("            <color:black>" + propertyKey + " = \"" + object.getPropertyMap().get(propertyKey) + "\"</color>\n");
      }
    } else if (phase.equals(Phase.pastRemove)) {
      result.append("        object \"<color:black>" + objectDisplayName + "</color>\" as " + objectKey + " #magenta {\n");
      for (String propertyKey : object.getPropertyMap().keySet()) {
        result.append("            <color:black>" + propertyKey + " = \"" + object.getPropertyMap().get(propertyKey) + "\"</color>\n");
      }
    } else if (phase.equals(Phase.outside)) {
      result.append("        object \"<color:black>" + objectDisplayName + "</color>\" as " + objectKey + " #EEEEEE {\n");
      for (String propertyKey : object.getPropertyMap().keySet()) {
        result.append("            <color:black>" + propertyKey + " = \"" + object.getPropertyMap().get(propertyKey) + "\"</color>\n");
      }
    }
    result.append("        }\n");
    result.append("    }\n");
    result.append("}\n");

    return result.toString();
  }

  private String relations(OmgObjectModel objectModel, List<OmgObjectModel> previousObjectModels, List<OmgObjectModel> futureObjectModels,
                           Mode mode) {
    StringBuilder result = new StringBuilder();
    List<RelationDescriptor> relationDescriptorsSoFar = new ArrayList<>();
    List<OmgObject> removedObjectsSoFar = new ArrayList<>();

    // Process the relations which are defined in the current objects
    Action action = objectModel.getAction();
    for (OmgObject object : objectModel.getObjects()) {
      for (OmgObject dependeeObject : object.getDependeeObjects()) {
        RelationDescriptor relationDescriptor = new RelationDescriptor(object, dependeeObject);
        if (!relationDescriptorsSoFar.contains(relationDescriptor)) {
          result.append(relation(relationDescriptor, action.equals(Action.create) ? Phase.currentCreate : Phase.currentRemove));
          relationDescriptorsSoFar.add(relationDescriptor);
          if (action.equals(Action.delete)) {
            removedObjectsSoFar.add(object);
          }
        }
      }
    }

    // Process the relations which are defined in the past objects
    for (OmgObjectModel previousObjectModel : previousObjectModels) {
      for (OmgObject previousObject : previousObjectModel.getObjects()) {
        for (OmgObject dependeeObject : previousObject.getDependeeObjects()) {
          RelationDescriptor relationDescriptor = new RelationDescriptor(previousObject, dependeeObject);
          if (!relationDescriptorsSoFar.contains(relationDescriptor)) {
            result.append(relation(relationDescriptor, removedObjectsSoFar.contains(previousObject) ? Phase.pastRemove : Phase.pastCreate));
            relationDescriptorsSoFar.add(relationDescriptor);
          }
        }
      }
    }

    if (mode.equals(Mode.everything)) {
      // Process the relations which are defined in the future objects
      for (OmgObjectModel futureObjectModel : futureObjectModels) {
        for (OmgObject futureObject : futureObjectModel.getObjects()) {
          for (OmgObject dependeeObject : futureObject.getDependeeObjects()) {
            RelationDescriptor relationDescriptor = new RelationDescriptor(futureObject, dependeeObject);
            if (!relationDescriptorsSoFar.contains(relationDescriptor)) {
              result.append(relation(relationDescriptor, Phase.future));
              relationDescriptorsSoFar.add(relationDescriptor);
            }
          }
        }
      }
    }

    return result.toString();
  }

  private String relation(RelationDescriptor relationDescriptor, Phase phase) {
    StringBuilder result = new StringBuilder();

    String fromKey = relationDescriptor.getFrom().getKey();
    String toKey = relationDescriptor.getTo().getKey();

    if (phase.equals(Phase.currentCreate)) {
      result.append(fromKey + " ----> " + toKey + " #green \n");
    } else if (phase.equals(Phase.currentRemove)) {
      result.append(fromKey + " ----> " + toKey + " #red\n");
    } else if (phase.equals(Phase.future)) {
      result.append(fromKey + " --[hidden]--> " + toKey + "\n");
    } else if (phase.equals(Phase.pastCreate)) {
      result.append(fromKey + " ----> " + toKey + "\n");
    } else if (phase.equals(Phase.pastRemove)) {
      result.append(fromKey + " --[hidden]--> " + toKey + "\n");
    } else if (phase.equals(Phase.outside)) {
      result.append(fromKey + " ----> " + toKey + " #grey\n");
    }

    return result.toString();
  }


  private String footer() {
    StringBuilder result = new StringBuilder();

    result.append("\n");
    result.append("@enduml\n");
    result.append("\n");

    return result.toString();
  }
}
