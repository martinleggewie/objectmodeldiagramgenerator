package org.codemaker.objectmodeldiagramgenerator.infrastructure;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioSequenceDescriptor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static org.codemaker.objectmodeldiagramgenerator.testutil.ScenarioSequenceTestDataCreator.createScenarioSequenceDescriptors_state1;
import static org.codemaker.objectmodeldiagramgenerator.testutil.ScenarioSequenceTestDataCreator.createScenarioSequenceDescriptors_state2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class XSSFWorkbookDescriptionRepository_ScenarioSequenceTest {

  private XSSFWorkbook workbook;

  @org.junit.jupiter.api.BeforeEach
  void setUp() throws IOException {
    workbook = new XSSFWorkbook(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("testobjectmodeldefinition.xlsx")));
  }

  @Test
  void findScenarioSequenceDescriptors() {
    // Arrange ... has already happened in the setUp method.

    // Act
    XSSFWorkbookDescriptionRepository cut = new XSSFWorkbookDescriptionRepository(workbook);
    Set<OmgScenarioSequenceDescriptor> scenarioSequenceDescriptors = cut.findScenarioSequenceDescriptors();

    // Assert - check number of scenario sequence descriptors
    assertEquals(7, scenarioSequenceDescriptors.size());
  }

  @Test
  void findScenarioSequenceDescriptors_state1() {
    // Arrange
    Set<OmgScenarioSequenceDescriptor> scenarioSequenceDescriptors = createScenarioSequenceDescriptors_state1();

    // Act
    XSSFWorkbookDescriptionRepository cut = new XSSFWorkbookDescriptionRepository(workbook);
    Set<OmgScenarioSequenceDescriptor> allScenarioSequenceDescriptors = cut.findScenarioSequenceDescriptors();
    Set<OmgScenarioSequenceDescriptor> result = allScenarioSequenceDescriptors.stream()
            .filter(d -> d.getTransitionStateDescriptorKey().equals("state1")).collect(Collectors.toSet());

    // Assert
    assertNotNull(result);
    assertEquals(6, result.size());
    assertEquals(scenarioSequenceDescriptors, result);
  }

  @Test
  void findObjectSequenceDescriptors_state2() {
    // Arrange
    Set<OmgScenarioSequenceDescriptor> scenarioSequenceDescriptors = createScenarioSequenceDescriptors_state2();

    // Act
    XSSFWorkbookDescriptionRepository cut = new XSSFWorkbookDescriptionRepository(workbook);
    Set<OmgScenarioSequenceDescriptor> allScenarioSequenceDescriptors = cut.findScenarioSequenceDescriptors();
    Set<OmgScenarioSequenceDescriptor> result = allScenarioSequenceDescriptors.stream()
            .filter(d -> d.getTransitionStateDescriptorKey().equals("state2")).collect(Collectors.toSet());

    // Assert
    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals(scenarioSequenceDescriptors, result);
  }
}
