package org.codemaker.objectmodeldiagramgenerator.infrastructure;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioDescriptor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static org.codemaker.objectmodeldiagramgenerator.testutil.ScenarioTestDataCreator.createScenarioDescriptorMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

class XSSFWorkbookDescriptionRepository_ScenarioTest {

  private XSSFWorkbook workbook;

  @org.junit.jupiter.api.BeforeEach
  void setUp() throws IOException {
    workbook = new XSSFWorkbook(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("testobjectmodeldefinition.xlsx")));
  }

  @Test
  void findScenarioDescriptors() {
    // Arrange
    Map<String, OmgScenarioDescriptor> scenarioDescriptorMap = createScenarioDescriptorMap();

    // Act
    XSSFWorkbookDescriptionRepository cut = new XSSFWorkbookDescriptionRepository(workbook);
    Set<OmgScenarioDescriptor> result = cut.findScenarioDescriptors();

    // Assert
    assertEquals(5, result.size());
    assertEquals(new HashSet<>(scenarioDescriptorMap.values()), result);
  }
}
