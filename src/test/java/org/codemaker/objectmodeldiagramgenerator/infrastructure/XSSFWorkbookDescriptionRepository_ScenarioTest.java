package org.codemaker.objectmodeldiagramgenerator.infrastructure;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioDescriptor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class XSSFWorkbookDescriptionRepository_ScenarioTest {

  private XSSFWorkbookDescriptionRepository cut;

  @org.junit.jupiter.api.BeforeEach
  void setUp() throws IOException {
    XSSFWorkbook workbook = new XSSFWorkbook(
            Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("testobjectmodeldefinition.xlsx")));
    cut = new XSSFWorkbookDescriptionRepository(workbook);
  }

  @Test
  void findAllScenarioDescriptors() {
    // Arrange
    OmgScenarioDescriptor descriptor1 = new OmgScenarioDescriptor("scenario1", "Description for scenario 1.", new ArrayList<>());
    List<String> predecessorKeysDescriptor2 = new ArrayList<>();
    predecessorKeysDescriptor2.add("scenario1");
    OmgScenarioDescriptor descriptor2 = new OmgScenarioDescriptor("scenario2", "Description for scenario 2.", predecessorKeysDescriptor2);
    List<String> predecessorKeysDescriptor3 = new ArrayList<>();
    predecessorKeysDescriptor3.add("scenario1");
    OmgScenarioDescriptor descriptor3 = new OmgScenarioDescriptor("scenario3", "Description for scenario 3.", predecessorKeysDescriptor3);
    List<String> predecessorKeysDescriptor4 = new ArrayList<>();
    predecessorKeysDescriptor4.add("scenario2");
    predecessorKeysDescriptor4.add("scenario3");
    OmgScenarioDescriptor descriptor4 = new OmgScenarioDescriptor("scenario4", "Description for scenario 4.", predecessorKeysDescriptor4);
    List<String> predecessorKeysDescriptor5 = new ArrayList<>();
    predecessorKeysDescriptor5.add("scenario1");
    predecessorKeysDescriptor5.add("scenario3");
    predecessorKeysDescriptor5.add("scenario4");
    OmgScenarioDescriptor descriptor5 = new OmgScenarioDescriptor("scenario5", "Description for scenario 5.", predecessorKeysDescriptor5);
    OmgScenarioDescriptor descriptor6 = new OmgScenarioDescriptor("scenario6", "Description for scenario 6.", new ArrayList<>());

    // Act
    Set<OmgScenarioDescriptor> result = cut.findScenarioDescriptors();

    // Assert
    assertEquals(5, result.size());
    assertTrue(result.contains(descriptor1));
    assertTrue(result.contains(descriptor2));
    assertTrue(result.contains(descriptor3));
    assertTrue(result.contains(descriptor4));
    assertTrue(result.contains(descriptor5));
    assertFalse(result.contains(descriptor6));
  }

  @Test
  void findScenarioDescriptor() {
    // Arrange
    OmgScenarioDescriptor descriptor1 = new OmgScenarioDescriptor("scenario1", "Description for scenario 1.", new ArrayList<>());
    List<String> predecessorKeysDescriptor2 = new ArrayList<>();
    predecessorKeysDescriptor2.add("scenario1");
    OmgScenarioDescriptor descriptor2 = new OmgScenarioDescriptor("scenario2", "Description for scenario 2.", predecessorKeysDescriptor2);
    List<String> predecessorKeysDescriptor3 = new ArrayList<>();
    predecessorKeysDescriptor3.add("scenario1");
    OmgScenarioDescriptor descriptor3 = new OmgScenarioDescriptor("scenario3", "Description for scenario 3.", predecessorKeysDescriptor3);
    List<String> predecessorKeysDescriptor4 = new ArrayList<>();
    predecessorKeysDescriptor4.add("scenario2");
    predecessorKeysDescriptor4.add("scenario3");
    OmgScenarioDescriptor descriptor4 = new OmgScenarioDescriptor("scenario4", "Description for scenario 4.", predecessorKeysDescriptor4);
    List<String> predecessorKeysDescriptor5 = new ArrayList<>();
    predecessorKeysDescriptor5.add("scenario1");
    predecessorKeysDescriptor5.add("scenario3");
    predecessorKeysDescriptor5.add("scenario4");
    OmgScenarioDescriptor descriptor5 = new OmgScenarioDescriptor("scenario5", "Description for scenario 5.", predecessorKeysDescriptor5);

    // Act
    OmgScenarioDescriptor result1 = cut.findScenarioDescriptor("scenario1");
    OmgScenarioDescriptor result2 = cut.findScenarioDescriptor("scenario2");
    OmgScenarioDescriptor result3 = cut.findScenarioDescriptor("scenario3");
    OmgScenarioDescriptor result4 = cut.findScenarioDescriptor("scenario4");
    OmgScenarioDescriptor result5 = cut.findScenarioDescriptor("scenario5");
    OmgScenarioDescriptor result6 = cut.findScenarioDescriptor("scenario6");

    // Assert
    assertEquals(result1, descriptor1);
    assertEquals(result2, descriptor2);
    assertEquals(result3, descriptor3);
    assertEquals(result4, descriptor4);
    assertEquals(result5, descriptor5);
    assertNull(result6);
  }
}
