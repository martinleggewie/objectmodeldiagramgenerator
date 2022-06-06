package org.codemaker.objectmodeldiagramgenerator.infrastructure;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEventDescriptor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class XSSFWorkbookDescriptionRepository_BusinessEventTest {

  private XSSFWorkbook workbook;

  @org.junit.jupiter.api.BeforeEach
  void setUp() throws IOException {
    workbook = new XSSFWorkbook(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("testobjectmodeldefinition.xlsx")));
  }

  @Test
  void findAllBusinessEventDescriptors() {
    // Arrange
    OmgBusinessEventDescriptor descriptor1 = new OmgBusinessEventDescriptor("bevt11", "Business Event 1 for Scenario 1.", "scenario1");
    OmgBusinessEventDescriptor descriptor2 = new OmgBusinessEventDescriptor("bevt12", "Business Event 2 for Scenario 1.", "scenario1");
    OmgBusinessEventDescriptor descriptor3 = new OmgBusinessEventDescriptor("bevt13", "Business Event 3 for Scenario 1.", "scenario1");
    OmgBusinessEventDescriptor descriptor4 = new OmgBusinessEventDescriptor("bevt21", "Business Event 1 for Scenario 2.", "scenario2");
    OmgBusinessEventDescriptor descriptor5 = new OmgBusinessEventDescriptor("bevt22", "Business Event 2 for Scenario 2.", "scenario2");
    OmgBusinessEventDescriptor descriptor6 = new OmgBusinessEventDescriptor("bevt31", "Business Event 1 for Scenario 3.", "scenario3");
    OmgBusinessEventDescriptor descriptor7 = new OmgBusinessEventDescriptor("bevt41", "Business Event 1 for Scenario 4.", "scenario4");
    OmgBusinessEventDescriptor descriptor8 = new OmgBusinessEventDescriptor("bevt42", "Business Event 2 for Scenario 4.", "scenario4");
    OmgBusinessEventDescriptor descriptor9 = new OmgBusinessEventDescriptor("bevt51", "Business Event 1 for Scenario 5.", "scenario5");
    OmgBusinessEventDescriptor descriptor0 = new OmgBusinessEventDescriptor("bevt00", "Business Event 0 for Scenario 0.", "scenario0");

    // Act
    XSSFWorkbookDescriptionRepository cut = new XSSFWorkbookDescriptionRepository(workbook);
    Set<OmgBusinessEventDescriptor> result = cut.findBusinessEventDescriptors();

    // Assert
    assertEquals(9, result.size());
    assertTrue(result.contains(descriptor1));
    assertTrue(result.contains(descriptor2));
    assertTrue(result.contains(descriptor3));
    assertTrue(result.contains(descriptor4));
    assertTrue(result.contains(descriptor5));
    assertTrue(result.contains(descriptor6));
    assertTrue(result.contains(descriptor7));
    assertTrue(result.contains(descriptor8));
    assertTrue(result.contains(descriptor9));
    assertFalse(result.contains(descriptor0));
  }

  @Test
  void findBusinessEventDescriptors() {
    // Arrange
    OmgBusinessEventDescriptor descriptor1 = new OmgBusinessEventDescriptor("bevt11", "Business Event 1 for Scenario 1.", "scenario1");
    OmgBusinessEventDescriptor descriptor2 = new OmgBusinessEventDescriptor("bevt12", "Business Event 2 for Scenario 1.", "scenario1");
    OmgBusinessEventDescriptor descriptor3 = new OmgBusinessEventDescriptor("bevt13", "Business Event 3 for Scenario 1.", "scenario1");
    OmgBusinessEventDescriptor descriptor4 = new OmgBusinessEventDescriptor("bevt21", "Business Event 1 for Scenario 2.", "scenario2");
    OmgBusinessEventDescriptor descriptor5 = new OmgBusinessEventDescriptor("bevt22", "Business Event 2 for Scenario 2.", "scenario2");
    OmgBusinessEventDescriptor descriptor6 = new OmgBusinessEventDescriptor("bevt31", "Business Event 1 for Scenario 3.", "scenario3");
    OmgBusinessEventDescriptor descriptor7 = new OmgBusinessEventDescriptor("bevt41", "Business Event 1 for Scenario 4.", "scenario4");
    OmgBusinessEventDescriptor descriptor8 = new OmgBusinessEventDescriptor("bevt42", "Business Event 2 for Scenario 4.", "scenario4");
    OmgBusinessEventDescriptor descriptor9 = new OmgBusinessEventDescriptor("bevt51", "Business Event 1 for Scenario 5.", "scenario5");

    // Act
    XSSFWorkbookDescriptionRepository cut = new XSSFWorkbookDescriptionRepository(workbook);
    OmgBusinessEventDescriptor result1 = cut.findBusinessEventDescriptor("bevt11");
    OmgBusinessEventDescriptor result2 = cut.findBusinessEventDescriptor("bevt12");
    OmgBusinessEventDescriptor result3 = cut.findBusinessEventDescriptor("bevt13");
    OmgBusinessEventDescriptor result4 = cut.findBusinessEventDescriptor("bevt21");
    OmgBusinessEventDescriptor result5 = cut.findBusinessEventDescriptor("bevt22");
    OmgBusinessEventDescriptor result6 = cut.findBusinessEventDescriptor("bevt31");
    OmgBusinessEventDescriptor result7 = cut.findBusinessEventDescriptor("bevt41");
    OmgBusinessEventDescriptor result8 = cut.findBusinessEventDescriptor("bevt42");
    OmgBusinessEventDescriptor result9 = cut.findBusinessEventDescriptor("bevt51");
    OmgBusinessEventDescriptor result0 = cut.findBusinessEventDescriptor("bevt00");

    // Assert
    assertEquals(descriptor1, result1);
    assertEquals(descriptor2, result2);
    assertEquals(descriptor3, result3);
    assertEquals(descriptor4, result4);
    assertEquals(descriptor5, result5);
    assertEquals(descriptor6, result6);
    assertEquals(descriptor7, result7);
    assertEquals(descriptor8, result8);
    assertEquals(descriptor9, result9);
    assertNull(result0);
  }
}
