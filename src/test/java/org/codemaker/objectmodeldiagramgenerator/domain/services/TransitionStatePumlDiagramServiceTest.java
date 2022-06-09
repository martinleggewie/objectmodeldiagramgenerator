package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionState;
import org.codemaker.objectmodeldiagramgenerator.domain.valueobjects.PumlDiagram;
import org.codemaker.objectmodeldiagramgenerator.testutil.TransitionStateTestDataCreator;
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

class TransitionStatePumlDiagramServiceTest {

  private TransitionStateService transitionStateService;
  private List<String> expectedDiagramContent;

  @BeforeEach
  void setUp() {
    transitionStateService = new TransitionStateService(null) {
      @Override
      public Map<String, OmgTransitionState> findTransitionStateMap() {
        return TransitionStateTestDataCreator.createTransitionStateMap();
      }
    };

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
            Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("testtransitionstatesdiagram.puml"))));
    expectedDiagramContent = bufferedReader.lines().collect(Collectors.toList());
  }

  @Test
  void createDiagram() {
    // Arrange ... has already happened in the setUp method.

    // Act
    TransitionStatePumlDiagramService cut = new TransitionStatePumlDiagramService(transitionStateService);
    PumlDiagram result = cut.createDiagram();
    List<String> resultContent = Arrays.asList(result.getContent().split("\n"));

    // Assert
    assertEquals("transitionstates", result.getName());
    assertEquals(expectedDiagramContent.size(), resultContent.size());
    for (int i = 0; i < resultContent.size(); i++) {
      assertEquals(expectedDiagramContent.get(i), resultContent.get(i), "Content differs in line " + (i + 1));
    }
  }
}