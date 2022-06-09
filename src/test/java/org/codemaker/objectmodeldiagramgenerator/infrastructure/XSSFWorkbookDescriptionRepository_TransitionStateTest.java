package org.codemaker.objectmodeldiagramgenerator.infrastructure;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionStateDescriptor;
import org.codemaker.objectmodeldiagramgenerator.testutil.TransitionStateTestDataCreator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class XSSFWorkbookDescriptionRepository_TransitionStateTest {

  private XSSFWorkbook workbook;

  @org.junit.jupiter.api.BeforeEach
  void setUp() throws IOException {
    workbook = new XSSFWorkbook(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("testobjectmodeldefinition.xlsx")));
  }

  @Test
  void findTransitionStateDescriptors() {
    // Arrange
    OmgTransitionStateDescriptor descriptor1 = TransitionStateTestDataCreator.createDescriptor("state1");
    OmgTransitionStateDescriptor descriptor2 = TransitionStateTestDataCreator.createDescriptor("state2");
    OmgTransitionStateDescriptor descriptor3 = TransitionStateTestDataCreator.createDescriptor("state3");
    OmgTransitionStateDescriptor descriptor0 = TransitionStateTestDataCreator.createDescriptor("state0");

    // Act
    XSSFWorkbookDescriptionRepository cut = new XSSFWorkbookDescriptionRepository(workbook);
    Set<OmgTransitionStateDescriptor> result = cut.findTransitionStateDescriptors();

    // Assert
    assertEquals(3, result.size());
    assertTrue(result.contains(descriptor1));
    assertTrue(result.contains(descriptor2));
    assertTrue(result.contains(descriptor3));
    assertFalse(result.contains(descriptor0));
  }
}
