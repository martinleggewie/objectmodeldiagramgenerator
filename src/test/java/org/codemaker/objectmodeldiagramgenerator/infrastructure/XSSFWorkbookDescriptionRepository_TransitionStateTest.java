package org.codemaker.objectmodeldiagramgenerator.infrastructure;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionStateDescriptor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class XSSFWorkbookDescriptionRepository_TransitionStateTest {

  private XSSFWorkbookDescriptionRepository cut;

  @org.junit.jupiter.api.BeforeEach
  void setUp() throws IOException {
    XSSFWorkbook workbook = new XSSFWorkbook(
            Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("testobjectmodeldefinition.xlsx")));
    cut = new XSSFWorkbookDescriptionRepository(workbook);
  }

  @Test
  void findAllTransitionStateDescriptors() {
    // Arrange
    OmgTransitionStateDescriptor descriptor1 = new OmgTransitionStateDescriptor("state1", "This is state 1.", "");
    OmgTransitionStateDescriptor descriptor2 = new OmgTransitionStateDescriptor("state2", "This is state 2.", "state1");
    OmgTransitionStateDescriptor descriptor3 = new OmgTransitionStateDescriptor("state3", "This is state 3.", "state1");
    OmgTransitionStateDescriptor descriptor4 = new OmgTransitionStateDescriptor("state4", "This is state 4.", "state1");

    // Act
    Set<OmgTransitionStateDescriptor> result = cut.findTransitionStateDescriptors();

    // Assert
    assertEquals(3, result.size());
    assertTrue(result.contains(descriptor1));
    assertTrue(result.contains(descriptor2));
    assertTrue(result.contains(descriptor3));
    assertFalse(result.contains(descriptor4));
  }

  @Test
  void findTransitionStateDescriptor() {
    // Arrange
    OmgTransitionStateDescriptor descriptor1 = new OmgTransitionStateDescriptor("state1", "This is state 1.", "");
    OmgTransitionStateDescriptor descriptor2 = new OmgTransitionStateDescriptor("state2", "This is state 2.", "state1");
    OmgTransitionStateDescriptor descriptor3 = new OmgTransitionStateDescriptor("state3", "This is state 3.", "state1");

    // Act
    OmgTransitionStateDescriptor result1 = cut.findTransitionStateDescriptor("state1");
    OmgTransitionStateDescriptor result2 = cut.findTransitionStateDescriptor("state2");
    OmgTransitionStateDescriptor result3 = cut.findTransitionStateDescriptor("state3");
    OmgTransitionStateDescriptor result4 = cut.findTransitionStateDescriptor("state4");

    // Assert
    assertEquals(result1, descriptor1);
    assertEquals(result2, descriptor2);
    assertEquals(result3, descriptor3);
    assertNull(result4);
  }
}
