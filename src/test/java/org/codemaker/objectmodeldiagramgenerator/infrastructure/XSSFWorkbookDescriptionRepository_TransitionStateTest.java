package org.codemaker.objectmodeldiagramgenerator.infrastructure;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionStateDescriptor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static org.codemaker.objectmodeldiagramgenerator.testutil.TransitionStateTestDataCreator.createTransitionStateDescriptorMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

class XSSFWorkbookDescriptionRepository_TransitionStateTest {

  private XSSFWorkbook workbook;

  @org.junit.jupiter.api.BeforeEach
  void setUp() throws IOException {
    workbook = new XSSFWorkbook(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("testobjectmodeldefinition.xlsx")));
  }

  @Test
  void findTransitionStateDescriptors() {
    // Arrange
    Map<String, OmgTransitionStateDescriptor> transitionStateDescriptorMap = createTransitionStateDescriptorMap();

    // Act
    XSSFWorkbookDescriptionRepository cut = new XSSFWorkbookDescriptionRepository(workbook);
    Set<OmgTransitionStateDescriptor> result = cut.findTransitionStateDescriptors();

    // Assert
    assertEquals(3, result.size());
    assertEquals(new HashSet<>(transitionStateDescriptorMap.values()), result);
  }
}
