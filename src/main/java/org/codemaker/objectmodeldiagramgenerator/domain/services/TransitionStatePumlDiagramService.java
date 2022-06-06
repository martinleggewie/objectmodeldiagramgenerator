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
    result.append("<style>\n");
    result.append("    Shadowing false\n");
    result.append("    Rectangle {\n");
    result.append("        BackgroundColor cornsilk\n");
    result.append("        FontSize 18\n");
    result.append("        FontStyle normal\n");
    result.append("        HorizontalAlignment left\n");
    result.append("        LineColor black\n");
    result.append("        LineThickness 3\n");
    result.append("        RoundCorner 20\n");
    result.append("    }\n");
    result.append("    Arrow {\n");
    result.append("        LineColor black\n");
    result.append("        LineThickness 3\n");
    result.append("    }\n");
    result.append("    Title {\n");
    result.append("        BackgroundColor white\n");
    result.append("        FontColor darkblue\n");
    result.append("        FontSize 20\n");
    result.append("        FontStyle bold\n");
    result.append("        HorizontalAlignment center\n");
    result.append("        LineColor darkblue\n");
    result.append("        LineThickness 2\n");
    result.append("        Margin 50\n");
    result.append("        Padding 20\n");
    result.append("    }\n");
    result.append("</style>\n");

    return result.toString();
  }

  private String title() {
    return "title Transition states and their predecessors\n";
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
