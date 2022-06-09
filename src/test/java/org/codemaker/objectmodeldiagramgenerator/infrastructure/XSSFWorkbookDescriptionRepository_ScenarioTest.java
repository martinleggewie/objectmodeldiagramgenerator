package org.codemaker.objectmodeldiagramgenerator.infrastructure;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioDescriptor;
import org.codemaker.objectmodeldiagramgenerator.testutil.ScenarioTestDataCreator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class XSSFWorkbookDescriptionRepository_ScenarioTest {

  private XSSFWorkbook workbook;

  @org.junit.jupiter.api.BeforeEach
  void setUp() throws IOException {
    workbook = new XSSFWorkbook(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("testobjectmodeldefinition.xlsx")));
  }

  @Test
  void findScenarioDescriptors() {
    // Arrange
    OmgScenarioDescriptor descriptor1 = ScenarioTestDataCreator.createDescriptor("scenario1");
    OmgScenarioDescriptor descriptor2 = ScenarioTestDataCreator.createDescriptor("scenario2");
    OmgScenarioDescriptor descriptor3 = ScenarioTestDataCreator.createDescriptor("scenario3");
    OmgScenarioDescriptor descriptor4 = ScenarioTestDataCreator.createDescriptor("scenario4");
    OmgScenarioDescriptor descriptor5 = ScenarioTestDataCreator.createDescriptor("scenario5");
    OmgScenarioDescriptor descriptor0 = ScenarioTestDataCreator.createDescriptor("scenario0");

    // Act
    XSSFWorkbookDescriptionRepository cut = new XSSFWorkbookDescriptionRepository(workbook);
    Set<OmgScenarioDescriptor> result = cut.findScenarioDescriptors();

    // Assert
    assertEquals(5, result.size());
    assertTrue(result.contains(descriptor1));
    assertTrue(result.contains(descriptor2));
    assertTrue(result.contains(descriptor3));
    assertTrue(result.contains(descriptor4));
    assertTrue(result.contains(descriptor5));
    assertFalse(result.contains(descriptor0));
  }
}
