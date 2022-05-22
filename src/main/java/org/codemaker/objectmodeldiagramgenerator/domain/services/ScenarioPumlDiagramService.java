package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgDefinition;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenario;
import org.codemaker.objectmodeldiagramgenerator.domain.valueobjects.PumlDiagram;

public class ScenarioPumlDiagramService {

  private final OmgDefinition definition;

  public ScenarioPumlDiagramService(OmgDefinition definition) {
    this.definition = definition;
  }

  public PumlDiagram createDiagram() {
    StringBuilder builder = new StringBuilder();

    builder.append(header());
    builder.append(title());
    builder.append(scenarios());
    builder.append(relations());
    builder.append(footer());

    return new PumlDiagram(diagramName(), builder.toString());
  }

  private String diagramName() {
    return "scenarios";
  }

  private String header() {
    StringBuilder result = new StringBuilder();

    result.append("\n");
    result.append("@startuml " + diagramName() + "\n");
    result.append("\n");
    result.append("top to bottom direction\n");
    result.append("skinparam shadowing false\n");
    result.append("skinparam componentStyle uml2\n");
    result.append("skinparam roundCorner 10\n");
    result.append("\n");
    result.append("skinparam rectangleRoundCorner 20\n");
    result.append("skinparam rectangleFontStyle normal\n");
    result.append("skinparam rectangleFontSize 18\n");
    result.append("skinparam rectangleBorderColor #black\n");
    result.append("skinparam rectangleBorderThickness 3\n");
    result.append("skinparam rectangleBackgroundColor #cornsilk\n");
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

  private String title() {
    return "title \\nScenarios and their predecessors\\n\n";
  }

  private String scenarios() {
    StringBuilder result = new StringBuilder();

    for (OmgScenario scenario : definition.getScenarioMap().values()) {
      result.append("rectangle \"<b>" + scenario.getKey() + "</b>\\n\\n" + lineWrap(scenario.getDescription(),
              30) + "\" as " + scenario.getKey() + " " + "{\n");
      result.append("}\n");
    }

    return result.toString();
  }

  private String lineWrap(String rawLine, int maxLineLength) {
    StringBuilder result = new StringBuilder();
    int lineLength = 0;
    for (String word : rawLine.split(" ")) {
      result.append(word);
      if (lineLength > maxLineLength) {
        result.append("\\n");
        lineLength = 0;
      } else {
        result.append(" ");
        lineLength += word.length() + 1;
      }
    }

    return result.toString();
  }

  private String relations() {
    StringBuilder result = new StringBuilder();

    for (OmgScenario scenario : definition.getScenarioMap().values()) {
      for (OmgScenario predecessorScenario : scenario.getPredecessors()) {
        result.append(scenario.getKey() + " ---> " + predecessorScenario.getKey() + "\n");
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
