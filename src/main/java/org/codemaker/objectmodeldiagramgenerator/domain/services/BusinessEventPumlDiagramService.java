package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEvent;
import org.codemaker.objectmodeldiagramgenerator.domain.valueobjects.PumlDiagram;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class BusinessEventPumlDiagramService {

  private final BusinessEventService businessEventService;

  public BusinessEventPumlDiagramService(BusinessEventService businessEventService) {
    this.businessEventService = businessEventService;
  }

  public PumlDiagram createDiagram() {
    StringBuilder builder = new StringBuilder();

    builder.append(header());
    builder.append("\n");
    builder.append("\n");
    builder.append(title());
    builder.append("\n");
    builder.append(mindmap());
    builder.append("\n");
    builder.append(footer());
    builder.append("\n");

    return new PumlDiagram(diagramName(), builder.toString());
  }

  private String diagramName() {
    return "businessevents";
  }

  private String header() {
    StringBuilder result = new StringBuilder();

    result.append("@startmindmap " + diagramName() + "\n");
    result.append("\n");
    result.append("skinparam shadowing false\n");
    result.append("\n");
    result.append("<style>\n");
    result.append("    rootNode {\n");
    result.append("        BackgroundColor lightgrey\n");
    result.append("        FontSize 16\n");
    result.append("        FontStyle bold\n");
    result.append("        Padding 20\n");
    result.append("        Margin 20\n");
    result.append("        HorizontalAlignment left\n");
    result.append("        RoundCorner 20\n");
    result.append("        MaximumWidth 200\n");
    result.append("        LineColor black\n");
    result.append("        LineThickness 3\n");
    result.append("    }\n");
    result.append("    leafNode {\n");
    result.append("        BackgroundColor cornsilk\n");
    result.append("        FontSize 16\n");
    result.append("        FontStyle normal\n");
    result.append("        Padding 20\n");
    result.append("        Margin 20\n");
    result.append("        HorizontalAlignment left\n");
    result.append("        LineColor black\n");
    result.append("        LineThickness 3\n");
    result.append("        RoundCorner 20\n");
    result.append("        MaximumWidth 300\n");
    result.append("    }\n");
    result.append("    arrow {\n");
    result.append("        LineStyle 5\n");
    result.append("        LineThickness 3\n");
    result.append("        LineColor black\n");
    result.append("    }\n");
    result.append("    title {\n");
    result.append("        FontSize 20\n");
    result.append("    }\n");
    result.append("</style>\n");

    return result.toString();
  }

  private String title() {
    return "title \\nBusiness events and the scenarios they belong to\\n\n";
  }

  private String mindmap() {
    StringBuilder result = new StringBuilder();

    Map<String, OmgBusinessEvent> businessEventMap = businessEventService.findBusinessEventMap();

    Set<String> scenarioKeys = new HashSet<>();
    for (OmgBusinessEvent businessEvent : businessEventMap.values()) {
      scenarioKeys.add(businessEvent.getScenario().getKey());
    }
    List<String> sortedScenarioKeys = scenarioKeys.stream().sorted().collect(Collectors.toList());
    for (String scenarioKey : sortedScenarioKeys) {
      Set<String> businessEventKeys = new HashSet<>();
      for (OmgBusinessEvent businessEvent : businessEventMap.values()) {
        if (businessEvent.getScenario().getKey().equals(scenarioKey)) {
          businessEventKeys.add(businessEvent.getKey());
        }
      }
      List<String> sortedBusinessEventKeys = businessEventKeys.stream().sorted().collect(Collectors.toList());

      result.append("* " + scenarioKey + "\n");
      for (String businessEventKey : sortedBusinessEventKeys) {
        OmgBusinessEvent businessEvent = businessEventMap.get(businessEventKey);
        result.append("** <b>" + businessEventKey + "</b>\\n\\n" + businessEvent.getDescription() + "\n");
      }
    }

    return result.toString();
  }

  private String footer() {
    StringBuilder result = new StringBuilder();

    result.append("@endmindmap\n");

    return result.toString();
  }
}
