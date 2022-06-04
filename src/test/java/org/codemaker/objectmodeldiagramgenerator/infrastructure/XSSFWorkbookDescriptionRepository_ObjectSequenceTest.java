package org.codemaker.objectmodeldiagramgenerator.infrastructure;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgClassDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgDomainDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObjectDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObjectSequenceDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObjectSequenceStepDescriptor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class XSSFWorkbookDescriptionRepository_ObjectSequenceTest {

  private XSSFWorkbookDescriptionRepository cut;

  @org.junit.jupiter.api.BeforeEach
  void setUp() throws IOException {
    XSSFWorkbook workbook = new XSSFWorkbook(
            Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("testobjectmodeldefinition.xlsx")));
    cut = new XSSFWorkbookDescriptionRepository(workbook);
  }

  @Test
  void findObjectSequenceDescriptors() {
    // Act
    Set<OmgObjectSequenceDescriptor> objectSequenceDescriptors = cut.findObjectSequenceDescriptors();

    // Assert - check number of object sequence descriptors
    assertEquals(2, objectSequenceDescriptors.size());
  }

  @Test
  void findObjectSequenceDescriptors_state1() {

    // Arrange - 1. Create the domain descriptors
    OmgDomainDescriptor domainDescriptorD1 = new OmgDomainDescriptor("domain1", "My First Domain");
    OmgDomainDescriptor domainDescriptorD2 = new OmgDomainDescriptor("domain2", "My Second Domain");
    OmgDomainDescriptor domainDescriptorD3 = new OmgDomainDescriptor("domain3", "My Third Domain");

    // Arrange - 2. Create the class descriptors
    OmgClassDescriptor classDescriptorD1C1 = new OmgClassDescriptor("class1", "My First Class", "domain1");
    OmgClassDescriptor classDescriptorD1C2 = new OmgClassDescriptor("class2", "My Second Class", "domain1");
    OmgClassDescriptor classDescriptorD1C3 = new OmgClassDescriptor("class3", "My Third Class", "domain1");
    OmgClassDescriptor classDescriptorD2C1 = new OmgClassDescriptor("class1", "My First Class", "domain2");
    OmgClassDescriptor classDescriptorD2C2 = new OmgClassDescriptor("class2", "My Second Class", "domain2");
    OmgClassDescriptor classDescriptorD3C1 = new OmgClassDescriptor("class1", "My First Class", "domain3");

    // Arrange - 3a. Create all the object descriptors
    OmgObjectDescriptor objectDescriptorD1C1_1_create = new OmgObjectDescriptor("domain1", "class1", "d1c1-1");
    objectDescriptorD1C1_1_create.getPropertyMap().put("name1", "value001");
    objectDescriptorD1C1_1_create.getPropertyMap().put("name2", "value002");
    objectDescriptorD1C1_1_create.getPropertyMap().put("name3", "value003");
    OmgObjectDescriptor objectDescriptorD1C2_1_create = new OmgObjectDescriptor("domain1", "class2", "d1c2-1");
    objectDescriptorD1C2_1_create.getPropertyMap().put("name1", "value004");
    objectDescriptorD1C2_1_create.getPropertyMap().put("name2", "value005");
    OmgObjectDescriptor objectDescriptorD1C2_2_create = new OmgObjectDescriptor("domain1", "class2", "d1c2-2");
    objectDescriptorD1C2_2_create.getPropertyMap().put("name1", "value006");
    objectDescriptorD1C2_2_create.getPropertyMap().put("name2", "value007");
    OmgObjectDescriptor objectDescriptorD1C3_1_create = new OmgObjectDescriptor("domain1", "class3", "d1c3-1");
    objectDescriptorD1C3_1_create.getPropertyMap().put("name1", "value008");
    objectDescriptorD1C3_1_create.getPropertyMap().put("name2", "value009");
    objectDescriptorD1C3_1_create.getPropertyMap().put("d1c1_fk", "d1c1-1");
    objectDescriptorD1C3_1_create.getDependeeKeys().add("d1c1-1");
    OmgObjectDescriptor objectDescriptorD2C1_1_create = new OmgObjectDescriptor("domain2", "class1", "d2c1-1");
    objectDescriptorD2C1_1_create.getPropertyMap().put("name1", "value010");
    objectDescriptorD2C1_1_create.getPropertyMap().put("d1c2_fk", "d1c2-1");
    objectDescriptorD2C1_1_create.getPropertyMap().put("d1c3_fk", "d1c3-1");
    objectDescriptorD2C1_1_create.getDependeeKeys().add("d1c2-1");
    objectDescriptorD2C1_1_create.getDependeeKeys().add("d1c3-1");
    OmgObjectDescriptor objectDescriptorD2C2_1_create = new OmgObjectDescriptor("domain2", "class2", "d2c2-1");
    objectDescriptorD2C2_1_create.getPropertyMap().put("name1", "value011");
    objectDescriptorD2C2_1_create.getPropertyMap().put("name2", "value012");
    objectDescriptorD2C2_1_create.getPropertyMap().put("d1c1_fk", "d1c1-1");
    objectDescriptorD2C2_1_create.getPropertyMap().put("d1c2_fk", "d1c2-1");
    objectDescriptorD2C2_1_create.getPropertyMap().put("d1c3_fk", "d1c3-1");
    objectDescriptorD2C2_1_create.getPropertyMap().put("d2c1_fk", "d2c1-1");
    objectDescriptorD2C2_1_create.getDependeeKeys().add("d1c1-1");
    objectDescriptorD2C2_1_create.getDependeeKeys().add("d1c2-1");
    objectDescriptorD2C2_1_create.getDependeeKeys().add("d1c3-1");
    objectDescriptorD2C2_1_create.getDependeeKeys().add("d2c1-1");
    OmgObjectDescriptor objectDescriptorD3C1_1_create = new OmgObjectDescriptor("domain3", "class1", "d3c1-1");
    objectDescriptorD3C1_1_create.getPropertyMap().put("d1c1_fk", "d1c1-1");
    objectDescriptorD3C1_1_create.getPropertyMap().put("d1c2_fk", "(d1c2-1,d1c2-2)");
    objectDescriptorD3C1_1_create.getPropertyMap().put("d2c1_fk", "d2c1-1");
    objectDescriptorD3C1_1_create.getDependeeKeys().add("d1c1-1");
    objectDescriptorD3C1_1_create.getDependeeKeys().add("d1c2-1");
    objectDescriptorD3C1_1_create.getDependeeKeys().add("d1c2-2");
    objectDescriptorD3C1_1_create.getDependeeKeys().add("d2c1-1");
    OmgObjectDescriptor objectDescriptorD3C1_1_remove = new OmgObjectDescriptor("domain3", "class1", "d3c1-1");
    objectDescriptorD3C1_1_remove.getPropertyMap().put("d1c1_fk", "-");
    objectDescriptorD3C1_1_remove.getPropertyMap().put("d1c2_fk", "-");
    objectDescriptorD3C1_1_remove.getPropertyMap().put("d2c1_fk", "-");
    OmgObjectDescriptor objectDescriptorD2C2_1_remove = new OmgObjectDescriptor("domain2", "class2", "d2c2-1");
    objectDescriptorD2C2_1_remove.getPropertyMap().put("name1", "-");
    objectDescriptorD2C2_1_remove.getPropertyMap().put("name2", "-");
    objectDescriptorD2C2_1_remove.getPropertyMap().put("d1c1_fk", "-");
    objectDescriptorD2C2_1_remove.getPropertyMap().put("d1c2_fk", "-");
    objectDescriptorD2C2_1_remove.getPropertyMap().put("d1c3_fk", "-");
    objectDescriptorD2C2_1_remove.getPropertyMap().put("d2c1_fk", "-");
    OmgObjectDescriptor objectDescriptorD2C1_1_remove = new OmgObjectDescriptor("domain2", "class1", "d2c1-1");
    objectDescriptorD2C1_1_remove.getPropertyMap().put("name1", "-");
    objectDescriptorD2C1_1_remove.getPropertyMap().put("d1c2_fk", "-");
    objectDescriptorD2C1_1_remove.getPropertyMap().put("d1c3_fk", "-");
    OmgObjectDescriptor objectDescriptorD1C3_1_remove = new OmgObjectDescriptor("domain1", "class3", "d1c3-1");
    objectDescriptorD1C3_1_remove.getPropertyMap().put("name1", "-");
    objectDescriptorD1C3_1_remove.getPropertyMap().put("name2", "-");
    objectDescriptorD1C3_1_remove.getPropertyMap().put("d1c1_fk", "-");
    OmgObjectDescriptor objectDescriptorD1C2_2_remove = new OmgObjectDescriptor("domain1", "class2", "d1c2-2");
    objectDescriptorD1C2_2_remove.getPropertyMap().put("name1", "-");
    objectDescriptorD1C2_2_remove.getPropertyMap().put("name2", "-");
    OmgObjectDescriptor objectDescriptorD1C2_1_remove = new OmgObjectDescriptor("domain1", "class2", "d1c2-1");
    objectDescriptorD1C2_1_remove.getPropertyMap().put("name1", "-");
    objectDescriptorD1C2_1_remove.getPropertyMap().put("name2", "-");
    OmgObjectDescriptor objectDescriptorD1C1_1_remove = new OmgObjectDescriptor("domain1", "class1", "d1c1-1");
    objectDescriptorD1C1_1_remove.getPropertyMap().put("name1", "-");
    objectDescriptorD1C1_1_remove.getPropertyMap().put("name2", "-");
    objectDescriptorD1C1_1_remove.getPropertyMap().put("name3", "-");

    // Arrange - 3b. Create all the object sequence step descriptors and add the corresponding object descriptors to them
    OmgObjectSequenceStepDescriptor objectSequenceStepDescriptor1 = new OmgObjectSequenceStepDescriptor("bevt11", "create");
    objectSequenceStepDescriptor1.getObjectDescriptors().add(objectDescriptorD1C1_1_create);
    OmgObjectSequenceStepDescriptor objectSequenceStepDescriptor2 = new OmgObjectSequenceStepDescriptor("bevt11", "create");
    objectSequenceStepDescriptor2.getObjectDescriptors().add(objectDescriptorD1C2_1_create);
    OmgObjectSequenceStepDescriptor objectSequenceStepDescriptor3 = new OmgObjectSequenceStepDescriptor("bevt12", "create");
    objectSequenceStepDescriptor3.getObjectDescriptors().add(objectDescriptorD1C2_2_create);
    objectSequenceStepDescriptor3.getObjectDescriptors().add(objectDescriptorD1C3_1_create);
    OmgObjectSequenceStepDescriptor objectSequenceStepDescriptor4 = new OmgObjectSequenceStepDescriptor("bevt12", "create");
    objectSequenceStepDescriptor4.getObjectDescriptors().add(objectDescriptorD2C1_1_create);
    OmgObjectSequenceStepDescriptor objectSequenceStepDescriptor5 = new OmgObjectSequenceStepDescriptor("bevt12", "create");
    objectSequenceStepDescriptor5.getObjectDescriptors().add(objectDescriptorD2C2_1_create);
    OmgObjectSequenceStepDescriptor objectSequenceStepDescriptor6 = new OmgObjectSequenceStepDescriptor("bevt13", "create");
    objectSequenceStepDescriptor6.getObjectDescriptors().add(objectDescriptorD3C1_1_create);
    OmgObjectSequenceStepDescriptor objectSequenceStepDescriptor7 = new OmgObjectSequenceStepDescriptor("bevt21", "delete");
    objectSequenceStepDescriptor7.getObjectDescriptors().add(objectDescriptorD3C1_1_remove);
    OmgObjectSequenceStepDescriptor objectSequenceStepDescriptor8 = new OmgObjectSequenceStepDescriptor("bevt22", "delete");
    objectSequenceStepDescriptor8.getObjectDescriptors().add(objectDescriptorD2C2_1_remove);
    OmgObjectSequenceStepDescriptor objectSequenceStepDescriptor9 = new OmgObjectSequenceStepDescriptor("bevt31", "delete");
    objectSequenceStepDescriptor9.getObjectDescriptors().add(objectDescriptorD2C1_1_remove);
    OmgObjectSequenceStepDescriptor objectSequenceStepDescriptor10 = new OmgObjectSequenceStepDescriptor("bevt41", "delete");
    objectSequenceStepDescriptor10.getObjectDescriptors().add(objectDescriptorD1C3_1_remove);
    OmgObjectSequenceStepDescriptor objectSequenceStepDescriptor11 = new OmgObjectSequenceStepDescriptor("bevt42", "delete");
    objectSequenceStepDescriptor11.getObjectDescriptors().add(objectDescriptorD1C2_2_remove);
    OmgObjectSequenceStepDescriptor objectSequenceStepDescriptor12 = new OmgObjectSequenceStepDescriptor("bevt42", "delete");
    objectSequenceStepDescriptor12.getObjectDescriptors().add(objectDescriptorD1C2_1_remove);
    OmgObjectSequenceStepDescriptor objectSequenceStepDescriptor13 = new OmgObjectSequenceStepDescriptor("bevt51", "delete");
    objectSequenceStepDescriptor13.getObjectDescriptors().add(objectDescriptorD1C1_1_remove);

    // Arrange - 3c. Now, in the grand finale, add all the object sequence step descriptors to the one object sequence descriptors
    OmgObjectSequenceDescriptor objectSequenceDescriptor = new OmgObjectSequenceDescriptor("state1", "OkSequence");
    objectSequenceDescriptor.getObjectSequenceStepDescriptors().add(objectSequenceStepDescriptor1);
    objectSequenceDescriptor.getObjectSequenceStepDescriptors().add(objectSequenceStepDescriptor2);
    objectSequenceDescriptor.getObjectSequenceStepDescriptors().add(objectSequenceStepDescriptor3);
    objectSequenceDescriptor.getObjectSequenceStepDescriptors().add(objectSequenceStepDescriptor4);
    objectSequenceDescriptor.getObjectSequenceStepDescriptors().add(objectSequenceStepDescriptor5);
    objectSequenceDescriptor.getObjectSequenceStepDescriptors().add(objectSequenceStepDescriptor6);
    objectSequenceDescriptor.getObjectSequenceStepDescriptors().add(objectSequenceStepDescriptor7);
    objectSequenceDescriptor.getObjectSequenceStepDescriptors().add(objectSequenceStepDescriptor8);
    objectSequenceDescriptor.getObjectSequenceStepDescriptors().add(objectSequenceStepDescriptor9);
    objectSequenceDescriptor.getObjectSequenceStepDescriptors().add(objectSequenceStepDescriptor10);
    objectSequenceDescriptor.getObjectSequenceStepDescriptors().add(objectSequenceStepDescriptor11);
    objectSequenceDescriptor.getObjectSequenceStepDescriptors().add(objectSequenceStepDescriptor12);
    objectSequenceDescriptor.getObjectSequenceStepDescriptors().add(objectSequenceStepDescriptor13);

    // Act
    Set<OmgObjectSequenceDescriptor> objectSequenceDescriptors = cut.findObjectSequenceDescriptors();
    assertEquals(1, objectSequenceDescriptors.stream().filter(d -> d.getTransitionStateDescriptorKey().equals("state1")).count());
    Optional<OmgObjectSequenceDescriptor> first = objectSequenceDescriptors.stream()
            .filter(d -> d.getTransitionStateDescriptorKey().equals("state1")).findFirst();
    OmgObjectSequenceDescriptor result = null;
    if (first.isPresent()) {
      result = first.get();
    }
    assertNotNull(result);

    // Assert - check transition state descriptor and title
    assertEquals(objectSequenceDescriptor.getTransitionStateDescriptorKey(), result.getTransitionStateDescriptorKey());
    assertEquals(objectSequenceDescriptor.getTitle(), result.getTitle());

    // Assert - check domain descriptors
    assertEquals(3, result.getDomainDescriptors().size());
    assertTrue(result.getDomainDescriptors().contains(domainDescriptorD1));
    assertTrue(result.getDomainDescriptors().contains(domainDescriptorD2));
    assertTrue(result.getDomainDescriptors().contains(domainDescriptorD3));

    // Assert - check class descriptors
    assertEquals(6, result.getClassDescriptors().size());
    assertTrue(result.getClassDescriptors().contains(classDescriptorD1C1));
    assertTrue(result.getClassDescriptors().contains(classDescriptorD1C2));
    assertTrue(result.getClassDescriptors().contains(classDescriptorD1C3));
    assertTrue(result.getClassDescriptors().contains(classDescriptorD2C1));
    assertTrue(result.getClassDescriptors().contains(classDescriptorD2C2));
    assertTrue(result.getClassDescriptors().contains(classDescriptorD3C1));

    // Assert - check object sequence step descriptors and their contained object descriptors
    assertEquals(13, result.getObjectSequenceStepDescriptors().size());
    assertEquals(objectSequenceStepDescriptor1, result.getObjectSequenceStepDescriptors().get(0));
    assertEquals(objectSequenceStepDescriptor2, result.getObjectSequenceStepDescriptors().get(1));
    assertEquals(objectSequenceStepDescriptor3, result.getObjectSequenceStepDescriptors().get(2));
    assertEquals(objectSequenceStepDescriptor4, result.getObjectSequenceStepDescriptors().get(3));
    assertEquals(objectSequenceStepDescriptor5, result.getObjectSequenceStepDescriptors().get(4));
    assertEquals(objectSequenceStepDescriptor6, result.getObjectSequenceStepDescriptors().get(5));
    assertEquals(objectSequenceStepDescriptor7, result.getObjectSequenceStepDescriptors().get(6));
    assertEquals(objectSequenceStepDescriptor8, result.getObjectSequenceStepDescriptors().get(7));
    assertEquals(objectSequenceStepDescriptor9, result.getObjectSequenceStepDescriptors().get(8));
    assertEquals(objectSequenceStepDescriptor10, result.getObjectSequenceStepDescriptors().get(9));
    assertEquals(objectSequenceStepDescriptor11, result.getObjectSequenceStepDescriptors().get(10));
    assertEquals(objectSequenceStepDescriptor12, result.getObjectSequenceStepDescriptors().get(11));
    assertEquals(objectSequenceStepDescriptor13, result.getObjectSequenceStepDescriptors().get(12));

    assertEquals(1, result.getObjectSequenceStepDescriptors().get(0).getObjectDescriptors().size());
    assertTrue(result.getObjectSequenceStepDescriptors().get(0).getObjectDescriptors().contains(objectDescriptorD1C1_1_create));
    assertEquals(1, result.getObjectSequenceStepDescriptors().get(1).getObjectDescriptors().size());
    assertTrue(result.getObjectSequenceStepDescriptors().get(1).getObjectDescriptors().contains(objectDescriptorD1C2_1_create));
    assertEquals(2, result.getObjectSequenceStepDescriptors().get(2).getObjectDescriptors().size());
    assertTrue(result.getObjectSequenceStepDescriptors().get(2).getObjectDescriptors().contains(objectDescriptorD1C2_2_create));
    assertTrue(result.getObjectSequenceStepDescriptors().get(2).getObjectDescriptors().contains(objectDescriptorD1C3_1_create));
    assertEquals(1, result.getObjectSequenceStepDescriptors().get(3).getObjectDescriptors().size());
    assertTrue(result.getObjectSequenceStepDescriptors().get(3).getObjectDescriptors().contains(objectDescriptorD2C1_1_create));
    assertEquals(1, result.getObjectSequenceStepDescriptors().get(4).getObjectDescriptors().size());
    assertTrue(result.getObjectSequenceStepDescriptors().get(4).getObjectDescriptors().contains(objectDescriptorD2C2_1_create));
    assertEquals(1, result.getObjectSequenceStepDescriptors().get(5).getObjectDescriptors().size());
    assertTrue(result.getObjectSequenceStepDescriptors().get(5).getObjectDescriptors().contains(objectDescriptorD3C1_1_create));
  }

  @Test
  void findObjectSequenceDescriptors_state2() {

    // Arrange - 1. Create the domain descriptors
    OmgDomainDescriptor domainDescriptorD1 = new OmgDomainDescriptor("domain1", "My First Domain");
    OmgDomainDescriptor domainDescriptorD2 = new OmgDomainDescriptor("domain2", "My Second Domain");
    OmgDomainDescriptor domainDescriptorD3 = new OmgDomainDescriptor("domain3", "My Third Domain");

    // Arrange - 2. Create the class descriptors
    OmgClassDescriptor classDescriptorD1C1 = new OmgClassDescriptor("class1", "My First Class", "domain1");
    OmgClassDescriptor classDescriptorD1C2 = new OmgClassDescriptor("class2", "My Second Class", "domain1");
    OmgClassDescriptor classDescriptorD1C3 = new OmgClassDescriptor("class3", "My Third Class", "domain1");
    OmgClassDescriptor classDescriptorD2C1 = new OmgClassDescriptor("class1", "My First Class", "domain2");
    OmgClassDescriptor classDescriptorD2C2 = new OmgClassDescriptor("class2", "My Second Class", "domain2");
    OmgClassDescriptor classDescriptorD3C1 = new OmgClassDescriptor("class1", "My First Class", "domain3");

    // Arrange - 3a. Create all the object descriptors
    OmgObjectDescriptor objectDescriptorD1C1_1_create = new OmgObjectDescriptor("domain1", "class1", "d1c1-1");
    objectDescriptorD1C1_1_create.getPropertyMap().put("name1", "value001");
    objectDescriptorD1C1_1_create.getPropertyMap().put("name2", "value002");
    objectDescriptorD1C1_1_create.getPropertyMap().put("name3", "value003");

    // Arrange - 3b. Create all the object sequence step descriptors and add the corresponding object descriptors to them
    OmgObjectSequenceStepDescriptor objectSequenceStepDescriptor1 = new OmgObjectSequenceStepDescriptor("bevt11", "create");
    objectSequenceStepDescriptor1.getObjectDescriptors().add(objectDescriptorD1C1_1_create);

    // Arrange - 3c. Now, in the grand finale, add all the object sequence step descriptors to the one object sequence descriptors
    OmgObjectSequenceDescriptor objectSequenceDescriptor = new OmgObjectSequenceDescriptor("state2", "OkSequence");
    objectSequenceDescriptor.getObjectSequenceStepDescriptors().add(objectSequenceStepDescriptor1);

    // Act
    Set<OmgObjectSequenceDescriptor> objectSequenceDescriptors = cut.findObjectSequenceDescriptors();

    assertEquals(1, objectSequenceDescriptors.stream().filter(d -> d.getTransitionStateDescriptorKey().equals("state2")).count());
    Optional<OmgObjectSequenceDescriptor> first = objectSequenceDescriptors.stream()
            .filter(d -> d.getTransitionStateDescriptorKey().equals("state2")).findFirst();
    OmgObjectSequenceDescriptor result = null;
    if (first.isPresent()) {
      result = first.get();
    }
    assertNotNull(result);

    // Assert - check transition state descriptor and title
    assertEquals(objectSequenceDescriptor.getTransitionStateDescriptorKey(), result.getTransitionStateDescriptorKey());
    assertEquals(objectSequenceDescriptor.getTitle(), result.getTitle());

    // Assert - check domain descriptors
    assertEquals(3, result.getDomainDescriptors().size());
    assertTrue(result.getDomainDescriptors().contains(domainDescriptorD1));
    assertTrue(result.getDomainDescriptors().contains(domainDescriptorD2));
    assertTrue(result.getDomainDescriptors().contains(domainDescriptorD3));

    // Assert - check class descriptors
    assertEquals(6, result.getClassDescriptors().size());
    assertTrue(result.getClassDescriptors().contains(classDescriptorD1C1));
    assertTrue(result.getClassDescriptors().contains(classDescriptorD1C2));
    assertTrue(result.getClassDescriptors().contains(classDescriptorD1C3));
    assertTrue(result.getClassDescriptors().contains(classDescriptorD2C1));
    assertTrue(result.getClassDescriptors().contains(classDescriptorD2C2));
    assertTrue(result.getClassDescriptors().contains(classDescriptorD3C1));

    // Assert - check object sequence step descriptors and their contained object descriptors
    assertEquals(1, result.getObjectSequenceStepDescriptors().size());
    assertEquals(objectSequenceStepDescriptor1, result.getObjectSequenceStepDescriptors().get(0));

    assertEquals(1, result.getObjectSequenceStepDescriptors().get(0).getObjectDescriptors().size());
    assertTrue(result.getObjectSequenceStepDescriptors().get(0).getObjectDescriptors().contains(objectDescriptorD1C1_1_create));
  }
}
