package org.codemaker.objectmodeldiagramgenerator.domain.services;

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

class ScenarioPumlDiagramServiceTest {

  private ScenarioService scenarioService;
  private List<String> expectedDiagramContent;

  @BeforeEach
  void setUp() {
    scenarioService = new ScenarioService(null) {
      @Override
      public Map<String, OmgScenario> findScenarioMap() {
        final String shortSentence = "One two three four five six seven eight nine ten.";
        final String mediumSentence = shortSentence + " " + shortSentence;
        final String longSentence = shortSentence + " " + shortSentence + " " + shortSentence;

        Map<String, OmgScenario> result = new HashMap<>();
        OmgScenario scenario1 = new OmgScenario("scenario1", "This is scenario 1.", new HashSet<>());
        OmgScenario scenario2 = new OmgScenario("scenario2", "This is scenario 2.", new HashSet<>(Arrays.asList(scenario1)));
        OmgScenario scenario3 = new OmgScenario("scenario3", "This is scenario 3. " + shortSentence,
                new HashSet<>(Arrays.asList(scenario1, scenario2)));
        OmgScenario scenario4 = new OmgScenario("scenario4", "This is scenario 4. " + mediumSentence,
                new HashSet<>(Arrays.asList(scenario1, scenario2, scenario3)));
        OmgScenario scenario5 = new OmgScenario("scenario5", "This is scenario 5. " + longSentence,
                new HashSet<>(Arrays.asList(scenario3, scenario4)));
        OmgScenario scenario6 = new OmgScenario("scenario6", "This is scenario 6.", new HashSet<>());
        OmgScenario scenario7 = new OmgScenario("scenario7", "This is scenario 7. " + mediumSentence,
                new HashSet<>(Arrays.asList(scenario1, scenario3, scenario5)));
        OmgScenario scenario8 = new OmgScenario("scenario8", "This is scenario 8. " + shortSentence,
                new HashSet<>(Arrays.asList(scenario5, scenario6, scenario7)));
        OmgScenario scenario9 = new OmgScenario("scenario9", "This is scenario 9. " + longSentence,
                new HashSet<>(Arrays.asList(scenario1, scenario2, scenario3, scenario4, scenario5, scenario6, scenario7, scenario8)));
        result.put(scenario1.getKey(), scenario1);
        result.put(scenario2.getKey(), scenario2);
        result.put(scenario3.getKey(), scenario3);
        result.put(scenario4.getKey(), scenario4);
        result.put(scenario5.getKey(), scenario5);
        result.put(scenario6.getKey(), scenario6);
        result.put(scenario7.getKey(), scenario7);
        result.put(scenario8.getKey(), scenario8);
        result.put(scenario9.getKey(), scenario9);
        return result;
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