package org.codemaker.objectmodeldiagramgenerator.infrastructure;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEventDescriptor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static org.codemaker.objectmodeldiagramgenerator.testutil.BusinessEventTestDataCreator.createBusinessEventDescriptorMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

class XSSFWorkbookDescriptionRepository_BusinessEventTest {

  private XSSFWorkbook workbook;

  @org.junit.jupiter.api.BeforeEach
  void setUp() throws IOException {
    workbook = new XSSFWorkbook(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("testobjectmodeldefinition.xlsx")));
  }

  @Test
  void findBusinessEventDescriptors() {
    // Arrange
    Map<String, OmgBusinessEventDescriptor> businessEventDescriptorMap = createBusinessEventDescriptorMap();

    // Act
    XSSFWorkbookDescriptionRepository cut = new XSSFWorkbookDescriptionRepository(workbook);
    Set<OmgBusinessEventDescriptor> result = cut.findBusinessEventDescriptors();

    // Assert
    assertEquals(17, result.size());
    assertEquals(new HashSet<>(businessEventDescriptorMap.values()), result);
  }
}
