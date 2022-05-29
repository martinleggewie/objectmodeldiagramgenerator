package org.codemaker.objectmodeldiagramgenerator.infrastructure;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEventDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionStateDescriptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class XSSFWorkbookDescriptionRepositoryTest {

  private XSSFWorkbook workbook;
  private XSSFWorkbookDescriptionRepository cut;

  @org.junit.jupiter.api.BeforeEach
  void setUp() throws IOException {
    workbook = new XSSFWorkbook(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("testobjectmodeldefinition.xlsx")));
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
    Set<OmgTransitionStateDescriptor> result = cut.findAllTransitionStateDescriptors();

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
    Set<OmgScenarioDescriptor> result = cut.findAllScenarioDescriptors();

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
    Set<OmgBusinessEventDescriptor> result = cut.findAllBusinessEventDescriptors();

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
}