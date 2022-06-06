package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenario;
import org.codemaker.objectmodeldiagramgenerator.domain.valueobjects.PumlDiagram;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScenarioPumlDiagramService {

  private final ScenarioService scenarioService;

  public ScenarioPumlDiagramService(ScenarioService scenarioService) {
    this.scenarioService = scenarioService;
  }

  public PumlDiagram createDiagram() {
    StringBuilder builder = new StringBuilder();

    builder.append(header());
    builder.append("\n");
    builder.append("\n");
    builder.append(title());
    builder.append("\n");
    builder.append(scenarios());
    builder.append("\n");
    builder.append(relations());
    builder.append("\n");
    builder.append(footer());
    builder.append("\n");

    return new PumlDiagram(diagramName(), builder.toString());
  }

  private String diagramName() {
    return "scenarios";
  }

  private String header() {
    StringBuilder result = new StringBuilder();

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

    return result.toString();
  }

  private String title() {
    return "title \\nScenarios and their predecessors\\n\n";
  }

  private String scenarios() {
    StringBuilder result = new StringBuilder();

    Map<String, OmgScenario> scenarioMap = scenarioService.findScenarioMap();
    List<String> sortedKeys = scenarioMap.keySet().stream().sorted().collect(Collectors.toList());
    for (String key : sortedKeys) {
      OmgScenario scenario = scenarioMap.get(key);
      result.append("rectangle \"<b>" + key + "</b>\\n\\n" + lineWrap(scenario.getDescription(), 30) + "\" as " + key + "\n");
    }

    return result.toString();
  }

  private String lineWrap(String rawLine, int maxLineLength) {
    StringBuilder result = new StringBuilder();

    String[] words = rawLine.trim().split(" ");
    int lineLength = 0;
    for (int i = 0; i < words.length; i++) {
      String word = words[i];
      result.append(word);
      if (lineLength > maxLineLength) {
        result.append("\\n");
        lineLength = 0;
      } else {
        if (i < (words.length - 1)) {
          result.append(" ");
          lineLength += word.length() + 1;
        }
      }
    }

    return result.toString();
  }

  private String relations() {
    StringBuilder result = new StringBuilder();

    Map<String, OmgScenario> scenarioMap = scenarioService.findScenarioMap();
    List<String> sortedKeys = scenarioMap.keySet().stream().sorted().collect(Collectors.toList());
    for (String key : sortedKeys) {
      OmgScenario scenario = scenarioMap.get(key);
      List<String> sortedPredecessorKeys = scenario.getPredecessors().stream().map(OmgScenario::getKey).sorted()
              .collect(Collectors.toList());
      for (String predecessorKey : sortedPredecessorKeys) {
        result.append(key + " ---> " + predecessorKey + "\n");
      }
    }

    return result.toString();
  }

  private String footer() {
    StringBuilder result = new StringBuilder();

    result.append("@enduml\n");

    return result.toString();
  }
}
