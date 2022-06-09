package org.codemaker.objectmodeldiagramgenerator.infrastructure;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEventDescriptor;
import org.codemaker.objectmodeldiagramgenerator.testutil.BusinessEventTestDataCreator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class XSSFWorkbookDescriptionRepository_BusinessEventTest {

  private XSSFWorkbook workbook;

  @org.junit.jupiter.api.BeforeEach
  void setUp() throws IOException {
    workbook = new XSSFWorkbook(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("testobjectmodeldefinition.xlsx")));
  }

  @Test
  void findBusinessEventDescriptors() {
    // Arrange
    OmgBusinessEventDescriptor descriptor0101 = BusinessEventTestDataCreator.createDescriptor("event0101");
    OmgBusinessEventDescriptor descriptor0102 = BusinessEventTestDataCreator.createDescriptor("event0102");
    OmgBusinessEventDescriptor descriptor0103 = BusinessEventTestDataCreator.createDescriptor("event0103");
    OmgBusinessEventDescriptor descriptor0201 = BusinessEventTestDataCreator.createDescriptor("event0201");
    OmgBusinessEventDescriptor descriptor0202 = BusinessEventTestDataCreator.createDescriptor("event0202");
    OmgBusinessEventDescriptor descriptor0203 = BusinessEventTestDataCreator.createDescriptor("event0203");
    OmgBusinessEventDescriptor descriptor0301 = BusinessEventTestDataCreator.createDescriptor("event0301");
    OmgBusinessEventDescriptor descriptor0302 = BusinessEventTestDataCreator.createDescriptor("event0302");
    OmgBusinessEventDescriptor descriptor0401 = BusinessEventTestDataCreator.createDescriptor("event0401");
    OmgBusinessEventDescriptor descriptor0402 = BusinessEventTestDataCreator.createDescriptor("event0402");
    OmgBusinessEventDescriptor descriptor0501 = BusinessEventTestDataCreator.createDescriptor("event0501");
    OmgBusinessEventDescriptor descriptor0502 = BusinessEventTestDataCreator.createDescriptor("event0502");
    OmgBusinessEventDescriptor descriptor0000 = BusinessEventTestDataCreator.createDescriptor("event0000");

    // Act
    XSSFWorkbookDescriptionRepository cut = new XSSFWorkbookDescriptionRepository(workbook);
    Set<OmgBusinessEventDescriptor> result = cut.findBusinessEventDescriptors();

    // Assert
    assertEquals(12, result.size());
    assertTrue(result.contains(descriptor0101));
    assertTrue(result.contains(descriptor0102));
    assertTrue(result.contains(descriptor0103));
    assertTrue(result.contains(descriptor0201));
    assertTrue(result.contains(descriptor0202));
    assertTrue(result.contains(descriptor0203));
    assertTrue(result.contains(descriptor0301));
    assertTrue(result.contains(descriptor0302));
    assertTrue(result.contains(descriptor0401));
    assertTrue(result.contains(descriptor0402));
    assertTrue(result.contains(descriptor0501));
    assertTrue(result.contains(descriptor0502));
    assertFalse(result.contains(descriptor0000));
  }
}
