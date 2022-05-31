package org.codemaker.objectmodeldiagramgenerator.infrastructure;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEventDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgClassDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgDomainDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObjectDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObjectSequenceDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObjectSequenceStepDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionStateDescriptor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class XSSFWorkbookDescriptionRepositoryTest {

  private XSSFWorkbookDescriptionRepository cut;

  @org.junit.jupiter.api.BeforeEach
  void setUp() throws IOException {
    XSSFWorkbook workbook = new XSSFWorkbook(
            Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("testobjectmodeldefinition.xlsx")));
    cut = new XSSFWorkbookDescriptionRepository(workbook);
  }

  @org.junit.jupiter.api.Test
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

  @org.junit.jupiter.api.Test
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

  @org.junit.jupiter.api.Test
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

  @org.junit.jupiter.api.Test
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

  @org.junit.jupiter.api.Test
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

  @org.junit.jupiter.api.Test
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

  @Test
  void findObjectSequenceDescriptors() {

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
    OmgObjectSequenceDescriptor objectSequenceDescriptor = new OmgObjectSequenceDescriptor("state1", "OkSequence1");
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

    // Assert - check number of object sequence descriptors
    assertEquals(1, objectSequenceDescriptors.size());

    OmgObjectSequenceDescriptor result = null;
    Optional<OmgObjectSequenceDescriptor> first = objectSequenceDescriptors.stream().findFirst();
    if (first.isPresent()) {
      result = objectSequenceDescriptors.stream().findFirst().get();
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
}
