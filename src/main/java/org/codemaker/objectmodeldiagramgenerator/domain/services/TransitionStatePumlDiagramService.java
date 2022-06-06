package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionState;
import org.codemaker.objectmodeldiagramgenerator.domain.valueobjects.PumlDiagram;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.codemaker.objectmodeldiagramgenerator.util.PumlUtil.lineWrap;

public class TransitionStatePumlDiagramService {

  private final TransitionStateService transitionStateService;

  public TransitionStatePumlDiagramService(TransitionStateService transitionStateService) {
    this.transitionStateService = transitionStateService;
  }

  public PumlDiagram createDiagram() {
    StringBuilder builder = new StringBuilder();

    builder.append(header());
    builder.append("\n");
    builder.append("\n");
    builder.append(title());
    builder.append("\n");
    builder.append(transitionstates());
    builder.append("\n");
    builder.append(relations());
    builder.append("\n");
    builder.append(footer());
    builder.append("\n");

    return new PumlDiagram(diagramName(), builder.toString());
  }

  private String diagramName() {
    return "transitionstates";
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
    return "title \\nTransition states and their predecessors\\n\n";
  }

  private String transitionstates() {
    StringBuilder result = new StringBuilder();

    Map<String, OmgTransitionState> transitionStateMap = transitionStateService.findTransitionStateMap();
    List<String> sortedKeys = transitionStateMap.keySet().stream().sorted().collect(Collectors.toList());
    for (String key : sortedKeys) {
      OmgTransitionState state = transitionStateMap.get(key);
      result.append("rectangle \"<b>" + key + "</b>\\n\\n" + lineWrap(state.getDescription(), 30) + "\" as " + key + "\n");
    }

    return result.toString();
  }

  private String relations() {
    StringBuilder result = new StringBuilder();

    Map<String, OmgTransitionState> transitionStateMap = transitionStateService.findTransitionStateMap();
    List<String> sortedKeys = transitionStateMap.keySet().stream().sorted().collect(Collectors.toList());
    for (String key : sortedKeys) {
      OmgTransitionState state = transitionStateMap.get(key);
      if (state.getPredecessor() != null) {
        result.append(key + " ---> " + state.getPredecessor().getKey() + "\n");
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
