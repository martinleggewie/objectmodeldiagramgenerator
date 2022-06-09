package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEvent;
import org.codemaker.objectmodeldiagramgenerator.domain.valueobjects.PumlDiagram;
import org.codemaker.objectmodeldiagramgenerator.testutil.BusinessEventTestDataCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
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
        return BusinessEventTestDataCreator.createBusinessEventMap();
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