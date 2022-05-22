package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEvent;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgDefinition;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObject;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObjectModel;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObjectModelSequence;
import org.codemaker.objectmodeldiagramgenerator.domain.valueobjects.PumlDiagram;

import java.util.ArrayList;
import java.util.List;

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
      List<OmgObjectModel> previousObjectModels = new ArrayList<>();
      List<OmgObjectModel> futureObjectModels = new ArrayList<>(objectModelSequence.getObjectModels());

      int objectModelCounter = 0;
      OmgBusinessEvent previousBusinessEvent = null;
      for (OmgObjectModel objectModel : objectModelSequence.getObjectModels()) {

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

    return result;
  }

  private String diagramName(OmgObjectModelSequence objectModelSequence, OmgObjectModel objectModel, int objectModelCounter, Mode mode) {
    return objectModelSequence.getName() + "_" + objectModel.getBusinessEvent().getScenario()
            .getKey() + "_" + mode + "_" + objectModel.getBusinessEvent().getKey() + "_" + String.format("%03d", objectModelCounter);
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
    result.append("skinparam titleFontStyle bold\n");
    result.append("\n");
    result.append("\n");

    return result.toString();
  }

  private String title(OmgObjectModelSequence objectModelSequence, OmgObjectModel objectModel, int objectModelCounter) {
    return "title " + objectModelSequence.getName() + " - " + objectModel.getBusinessEvent().getScenario()
            .getDescription() + "\\n\\n" + objectModel.getBusinessEvent().getDescription() + " - step " + objectModelCounter + "\\n\\n\n\n";
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
