package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEvent;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgDefinition;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObject;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObjectModel;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObjectModelSequence;
import org.codemaker.objectmodeldiagramgenerator.domain.valueobjects.PumlDiagram;

import java.util.ArrayList;
import java.util.List;

public class PumlDiagramService {

  private final OmgDefinition definition;

  public PumlDiagramService(OmgDefinition definition) {
    this.definition = definition;
  }

  public List<PumlDiagram> createDiagrams() {
    List<PumlDiagram> result = new ArrayList<>();

    for (OmgObjectModelSequence objectModelSequence : definition.getObjectModelSequences()) {
      List<OmgObjectModel> previousObjectModels = new ArrayList<>();
      int objectModelCounter = 0;
      OmgBusinessEvent previousBusinessEvent = null;
      for (OmgObjectModel objectModel : objectModelSequence.getObjectModels()) {
        OmgBusinessEvent currentBusinessEvent = objectModel.getBusinessEvent();
        if (!currentBusinessEvent.equals(previousBusinessEvent)) {
          objectModelCounter = 0;
          previousBusinessEvent = currentBusinessEvent;
        }
        StringBuilder builder = new StringBuilder();
        objectModelCounter++;

        String diagramName = diagramName(objectModelSequence, objectModel, objectModelCounter);

        builder.append(header(diagramName));
        builder.append(title(objectModelSequence, objectModel, objectModelCounter));
        builder.append(objects(objectModel, previousObjectModels));
        builder.append(relations(objectModel, previousObjectModels));
        builder.append(footer());

        PumlDiagram diagram = new PumlDiagram(diagramName, builder.toString());
        result.add(diagram);

        previousObjectModels.add(objectModel);
      }
    }

    return result;
  }

  private String diagramName(OmgObjectModelSequence objectModelSequence, OmgObjectModel objectModel, int objectModelCounter) {
    return objectModelSequence.getName() + "_" + objectModel.getBusinessEvent().getKey() + "_" + String.format("%03d", objectModelCounter);
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
    result.append("skinparam objectAttributeFontColor #444444\n");
    result.append("skinparam objectBackgroundColor #cornsilk\n");
    result.append("skinparam objectBorderColor #black\n");
    result.append("skinparam objectBorderThickness 3\n");
    result.append("skinparam objectFontSize 20\n");
    result.append("skinparam objectFontStyle bold\n");
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
    return "title " + objectModelSequence.getName() + " - " + objectModel.getBusinessEvent().getDescription() + " - step " + objectModelCounter + "\n\n";
  }

  private String objects(OmgObjectModel objectModel, List<OmgObjectModel> previousObjectModels) {
    StringBuilder result = new StringBuilder();

    for (OmgObject object : objectModel.getObjects()) {
      result.append(object(object, true));
    }

    for (OmgObjectModel previousObjectModel : previousObjectModels) {
      for (OmgObject previousObject : previousObjectModel.getObjects()) {
        result.append(object(previousObject, false));
      }
    }

    return result.toString();
  }

  private String object(OmgObject object, boolean isNew) {
    StringBuilder result = new StringBuilder();

    String domainDisplayName = object.getClazz().getDomain().getDisplayName();
    String domainKey = object.getClazz().getDomain().getKey();
    String classDisplayName = object.getClazz().getDisplayName();
    String classKey = object.getClazz().getKey();
    String objectDisplayName = object.displayName();
    String objectKey = object.getKey();

    result.append("rectangle \"" + domainDisplayName + "\" as " + domainKey + " #DDDDDD {\n");
    if (isNew) {
      result.append("    rectangle \"" + classDisplayName + "\" as " + classKey + " #white {\n");
      result.append("        object \"" + objectDisplayName + "\" as " + objectKey + " #palegreen {\n");
      for (String propertyKey : object.getPropertyMap().keySet()) {
        result.append("            <color:black>" + propertyKey + " = \"" + object.getPropertyMap().get(propertyKey) + "\"</color>\n");
      }
    } else {
      result.append("    rectangle \"" + classDisplayName + "\" as " + classKey + " {\n");
      result.append("        object \"" + objectDisplayName + "\" as " + objectKey + " {\n");
      for (String propertyKey : object.getPropertyMap().keySet()) {
        result.append("            " + propertyKey + " = \"" + object.getPropertyMap().get(propertyKey) + "\"\n");
      }
    }
    result.append("        }\n");
    result.append("    }\n");
    result.append("}\n");

    return result.toString();
  }

  private String relations(OmgObjectModel objectModel, List<OmgObjectModel> previousObjectModels) {
    StringBuilder result = new StringBuilder();

    for (OmgObject object : objectModel.getObjects()) {
      result.append(relation(object, true));
    }

    for (OmgObjectModel previousObjectModel : previousObjectModels) {
      for (OmgObject previousObject : previousObjectModel.getObjects()) {
        result.append(relation(previousObject, false));
      }
    }

    return result.toString();
  }

  private String relation(OmgObject object, boolean isNew) {
    StringBuilder result = new StringBuilder();

    for (OmgObject dependeeObject : object.getDependeeObjects()) {
      result.append(object.getKey() + " ---> " + dependeeObject.getKey() + (isNew ? " #green" : "") + "\n");
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
