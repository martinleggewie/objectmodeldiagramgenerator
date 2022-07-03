package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioSequence;
import org.codemaker.objectmodeldiagramgenerator.domain.valueobjects.PumlDiagram;
import org.codemaker.objectmodeldiagramgenerator.testutil.ScenarioSequenceTestDataCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScenarioSequencePumlDiagramServiceTest {

  private ScenarioSequenceService scenarioSequenceService;

  private Map<String, List<String>> expectedDiagramContentMap;

  private List<String> expectedDiagramNames;

  @BeforeEach
  void setUp() {
    expectedDiagramNames = new ArrayList<>();
    expectedDiagramNames.add("PersonsAndHouses_scenario1_state1_001_initial");
    expectedDiagramNames.add("PersonsAndHouses_scenario1_state1_002_event0101_001");
    expectedDiagramNames.add("PersonsAndHouses_scenario1_state1_003_event0101_002");
    expectedDiagramNames.add("PersonsAndHouses_scenario1_state1_004_event0101_003");
    expectedDiagramNames.add("PersonsAndHouses_scenario1_state1_005_event0102_001");
    expectedDiagramNames.add("PersonsAndHouses_scenario1_state1_006_event0103_001");
    expectedDiagramNames.add("PersonsAndHouses_scenario1_state1_007_final");
    expectedDiagramNames.add("PersonsAndHouses_scenario1_state2_001_initial");
    expectedDiagramNames.add("PersonsAndHouses_scenario1_state2_002_event0101_001");
    expectedDiagramNames.add("PersonsAndHouses_scenario1_state2_003_final");
    expectedDiagramNames.add("PersonsAndHouses_scenario2_state1_001_initial");
    expectedDiagramNames.add("PersonsAndHouses_scenario2_state1_002_event0201_001");
    expectedDiagramNames.add("PersonsAndHouses_scenario2_state1_003_event0202_001");
    expectedDiagramNames.add("PersonsAndHouses_scenario2_state1_004_event0203_001");
    expectedDiagramNames.add("PersonsAndHouses_scenario2_state1_005_final");
    expectedDiagramNames.add("PersonsAndHouses_scenario3_state1_001_initial");
    expectedDiagramNames.add("PersonsAndHouses_scenario3_state1_002_event0301_001");
    expectedDiagramNames.add("PersonsAndHouses_scenario3_state1_003_event0301_002");
    expectedDiagramNames.add("PersonsAndHouses_scenario3_state1_004_event0301_003");
    expectedDiagramNames.add("PersonsAndHouses_scenario3_state1_005_event0301_004");
    expectedDiagramNames.add("PersonsAndHouses_scenario3_state1_006_event0301_005");
    expectedDiagramNames.add("PersonsAndHouses_scenario3_state1_007_event0302_001");
    expectedDiagramNames.add("PersonsAndHouses_scenario3_state1_008_event0302_002");
    expectedDiagramNames.add("PersonsAndHouses_scenario3_state1_009_event0302_003");
    expectedDiagramNames.add("PersonsAndHouses_scenario3_state1_010_event0302_004");
    expectedDiagramNames.add("PersonsAndHouses_scenario3_state1_011_final");
    expectedDiagramNames.add("PersonsAndHouses_scenario4_state1_001_initial");
    expectedDiagramNames.add("PersonsAndHouses_scenario4_state1_002_event0401_001");
    expectedDiagramNames.add("PersonsAndHouses_scenario4_state1_003_event0402_001");
    expectedDiagramNames.add("PersonsAndHouses_scenario4_state1_004_final");
    expectedDiagramNames.add("PersonsAndHouses_scenario5_state1_001_initial");
    expectedDiagramNames.add("PersonsAndHouses_scenario5_state1_002_event0501_001");
    expectedDiagramNames.add("PersonsAndHouses_scenario5_state1_003_event0502_001");
    expectedDiagramNames.add("PersonsAndHouses_scenario5_state1_004_event0502_002");
    expectedDiagramNames.add("PersonsAndHouses_scenario5_state1_005_event0502_003");
    expectedDiagramNames.add("PersonsAndHouses_scenario5_state1_006_event0502_004");
    expectedDiagramNames.add("PersonsAndHouses_scenario5_state1_007_final");

    expectedDiagramContentMap = new HashMap<>();
    for (String expectedDiagramName : expectedDiagramNames) {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
              Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(expectedDiagramName + ".puml"))));
      expectedDiagramContentMap.put(expectedDiagramName, bufferedReader.lines().collect(Collectors.toList()));
    }

    scenarioSequenceService = new ScenarioSequenceService(null, null, null, null) {
      @Override
      public Set<OmgScenarioSequence> findScenarioSequences() {
        Set<OmgScenarioSequence> result = ScenarioSequenceTestDataCreator.createScenarioSequences_state1();
        result.addAll(ScenarioSequenceTestDataCreator.createScenarioSequences_state2());
        return result;
      }
    };
  }

  @Test
  void createDiagrams() {
    // Arrange ... has already happened in the setUp method.

    // Act
    ScenarioSequencePumlDiagramService cut = new ScenarioSequencePumlDiagramService(scenarioSequenceService);
    List<PumlDiagram> resultPumlDiagrams = cut.createDiagrams();

    // Assert
    assertEquals(37, resultPumlDiagrams.size());
    for (PumlDiagram resultPumlDiagram : resultPumlDiagrams) {
      String resultDiagramName = resultPumlDiagram.getName();
      List<String> expectedContent = expectedDiagramContentMap.get(resultDiagramName);
      List<String> actualContent = Arrays.asList(resultPumlDiagram.getContent().split("\n"));
      assertEquals(expectedContent, actualContent);
    }
  }
}