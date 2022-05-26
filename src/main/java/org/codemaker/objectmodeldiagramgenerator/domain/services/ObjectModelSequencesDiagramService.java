package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEvent;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgDefinition;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObject;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObjectModel;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObjectModelSequence;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenario;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionState;
import org.codemaker.objectmodeldiagramgenerator.domain.valueobjects.PumlDiagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectModelSequencesDiagramService {

  public static enum Mode {
    gradually,
    everything;
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

    for (OmgObject object : objectModel.getObjects()) {
      result.append(object(object, true, false));
    }

    for (OmgObjectModel previousObjectModel : previousObjectModels) {
      for (OmgObject previousObject : previousObjectModel.getObjects()) {
        result.append(object(previousObject, false, false));
      }
    }

    if (mode.equals(Mode.everything)) {
      for (OmgObjectModel futureObjectModel : futureObjectModels) {
        for (OmgObject futureObject : futureObjectModel.getObjects()) {
          result.append(object(futureObject, false, true));
        }
      }
    }

    return result.toString();
  }

  private String object(OmgObject object, boolean isNew, boolean isFuture) {
    StringBuilder result = new StringBuilder();

    String domainDisplayName = object.getClazz().getDomain().getDisplayName();
    String domainKey = object.getClazz().getDomain().getKey();
    String classDisplayName = object.getClazz().getDisplayName();
    String classKey = object.getClazz().getKey();
    String objectDisplayName = object.displayName();
    String objectKey = object.getKey();

    result.append("rectangle \"" + domainDisplayName + "\" as " + domainKey + " #DDDDDD {\n");
    result.append("    rectangle \"" + classDisplayName + "\" as " + classKey + " #white {\n");
    if (isNew) {
      result.append("        object \"<color:black>" + objectDisplayName + "</color>\" as " + objectKey + " #palegreen {\n");
      for (String propertyKey : object.getPropertyMap().keySet()) {
        result.append("            <color:black>" + propertyKey + " = \"" + object.getPropertyMap().get(propertyKey) + "\"</color>\n");
      }
    } else {
      if (isFuture) {
        result.append("        object \"" + objectDisplayName + "\" as " + objectKey + " {\n");
        for (String propertyKey : object.getPropertyMap().keySet()) {
          result.append("            " + propertyKey + " = \"" + object.getPropertyMap().get(propertyKey) + "\"\n");
        }
      } else {
        result.append("        object \"<color:black>" + objectDisplayName + "</color>\" as " + objectKey + " #EEEEEE {\n");
        for (String propertyKey : object.getPropertyMap().keySet()) {
          result.append("            <color:black>" + propertyKey + " = \"" + object.getPropertyMap().get(propertyKey) + "\"</color>\n");
        }
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

    for (OmgObject object : objectModel.getObjects()) {
      result.append(relation(object, true, false));
    }

    for (OmgObjectModel previousObjectModel : previousObjectModels) {
      for (OmgObject previousObject : previousObjectModel.getObjects()) {
        result.append(relation(previousObject, false, false));
      }
    }

    if (mode.equals(Mode.everything)) {
      for (OmgObjectModel futureObjectModel : futureObjectModels) {
        for (OmgObject futureObject : futureObjectModel.getObjects()) {
          result.append(relation(futureObject, false, true));
        }
      }
    }

    return result.toString();
  }

  private String relation(OmgObject object, boolean isNew, boolean isFuture) {
    StringBuilder result = new StringBuilder();

    for (OmgObject dependeeObject : object.getDependeeObjects()) {
      if (isNew) {
        result.append(object.getKey() + " ----> " + dependeeObject.getKey() + " #green \n");
      } else {
        if (isFuture) {
          result.append(object.getKey() + " --[hidden]--> " + dependeeObject.getKey() + "\n");
        } else {
          result.append(object.getKey() + " ----> " + dependeeObject.getKey() + "\n");
        }
      }
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
