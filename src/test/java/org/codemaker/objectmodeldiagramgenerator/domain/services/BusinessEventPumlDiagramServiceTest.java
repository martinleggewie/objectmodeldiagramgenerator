package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEvent;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenario;
import org.codemaker.objectmodeldiagramgenerator.domain.valueobjects.PumlDiagram;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BusinessEventPumlDiagramServiceTest {

  private BusinessEventService businessEventService;
  private List<String> expectedDiagramContent;

  @BeforeEach
  void setUp() {
    businessEventService = new BusinessEventService(null, null) {
      @Override
      public Map<String, OmgBusinessEvent> findBusinessEventMap() {
        final String shortSentence = "One two three four five six seven eight nine ten.";
        final String mediumSentence = shortSentence + " " + shortSentence;
        final String longSentence = shortSentence + " " + shortSentence + " " + shortSentence;

        Map<String, OmgBusinessEvent> result = new HashMap<>();

        OmgScenario scenario1 = new OmgScenario("scenario1", "This is scenario 1.", new HashSet<>());
        OmgScenario scenario2 = new OmgScenario("scenario2", "This is scenario 2.", new HashSet<>());
        OmgScenario scenario3 = new OmgScenario("scenario3", "This is scenario 3.", new HashSet<>());
        OmgScenario scenario4 = new OmgScenario("scenario4", "This is scenario 4.", new HashSet<>());

        OmgBusinessEvent event1 = new OmgBusinessEvent("event1", "This is business event 1.", scenario1);
        OmgBusinessEvent event2 = new OmgBusinessEvent("event2", "This is business event 2. " + shortSentence, scenario1);
        OmgBusinessEvent event3 = new OmgBusinessEvent("event3", "This is business event 3. " + mediumSentence, scenario1);
        OmgBusinessEvent event4 = new OmgBusinessEvent("event4", "This is business event 4. " + longSentence, scenario2);
        OmgBusinessEvent event5 = new OmgBusinessEvent("event5", "This is business event 5.", scenario2);
        OmgBusinessEvent event6 = new OmgBusinessEvent("event6", "This is business event 6. " + shortSentence, scenario3);
        OmgBusinessEvent event7 = new OmgBusinessEvent("event7", "This is business event 7.", scenario4);
        OmgBusinessEvent event8 = new OmgBusinessEvent("event8", "This is business event 8. " + longSentence, scenario4);
        OmgBusinessEvent event9 = new OmgBusinessEvent("event9", "This is business event 9. " + longSentence, scenario4);

        result.put(event1.getKey(), event1);
        result.put(event2.getKey(), event2);
        result.put(event3.getKey(), event3);
        result.put(event4.getKey(), event4);
        result.put(event5.getKey(), event5);
        result.put(event6.getKey(), event6);
        result.put(event7.getKey(), event7);
        result.put(event8.getKey(), event8);
        result.put(event9.getKey(), event9);

        return result;
      }
    };

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
            Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("testbusinesseventdiagram.puml"))));
    expectedDiagramContent = bufferedReader.lines().collect(Collectors.toList());
  }

  @Test
  void createDiagram() {
    // Arrange ... has already happened in the setUp method.

    // Act
    BusinessEventPumlDiagramService cut = new BusinessEventPumlDiagramService(businessEventService);
    PumlDiagram result = cut.createDiagram();
    List<String> resultContent = Arrays.asList(result.getContent().split("\n"));

    // Assert
    assertEquals("businessevents", result.getName());
    assertEquals(expectedDiagramContent.size(), resultContent.size());
    for (int i = 0; i < resultContent.size(); i++) {
      assertEquals(expectedDiagramContent.get(i), resultContent.get(i), "Content differs in line " + (i + 1));
    }
  }
}