package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionState;
import org.codemaker.objectmodeldiagramgenerator.domain.valueobjects.PumlDiagram;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
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
        final String shortSentence = "One two three four five six seven eight nine ten.";
        final String mediumSentence = shortSentence + " " + shortSentence;
        final String longSentence = shortSentence + " " + shortSentence + " " + shortSentence;

        Map<String, OmgTransitionState> result = new HashMap<>();

        OmgTransitionState state1 = new OmgTransitionState("state1", "This is transition state 1.", null);
        OmgTransitionState state2 = new OmgTransitionState("state2", "This is transition state 2. " + shortSentence, state1);
        OmgTransitionState state3 = new OmgTransitionState("state3", "This is transition state 3. " + mediumSentence, state2);
        OmgTransitionState state4 = new OmgTransitionState("state4", "This is transition state 4. " + longSentence, state3);
        OmgTransitionState state5 = new OmgTransitionState("state5", "This is transition state 5.", null);

        result.put(state1.getKey(), state1);
        result.put(state2.getKey(), state2);
        result.put(state3.getKey(), state3);
        result.put(state4.getKey(), state4);
        result.put(state5.getKey(), state5);
        return result;
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