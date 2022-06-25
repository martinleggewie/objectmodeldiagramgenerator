package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenario;
import org.codemaker.objectmodeldiagramgenerator.domain.valueobjects.PumlDiagram;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.codemaker.objectmodeldiagramgenerator.testutil.ScenarioTestDataCreator.createScenarioMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ScenarioPumlDiagramServiceTest {

  private ScenarioService scenarioService;
  private List<String> expectedDiagramContent;

  @BeforeEach
  void setUp() {
    scenarioService = new ScenarioService(null) {
      @Override
      public Map<String, OmgScenario> findScenarioMap() {
        return createScenarioMap();
      }
    };

    BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("testscenariosdiagram.puml"))));
    expectedDiagramContent = bufferedReader.lines().collect(Collectors.toList());
  }

  @Test
  void createDiagram() {
    // Arrange ... has already happened in the setUp method.

    // Act
    ScenarioPumlDiagramService cut = new ScenarioPumlDiagramService(scenarioService);
    PumlDiagram result = cut.createDiagram();
    List<String> resultContent = Arrays.asList(result.getContent().split("\n"));

    // Assert
    assertEquals("scenarios", result.getName());
    assertEquals(expectedDiagramContent.size(), resultContent.size());
    for (int i = 0; i < resultContent.size(); i++) {
      assertEquals(expectedDiagramContent.get(i), resultContent.get(i), "Content differs in line " + (i + 1));
    }
  }
}