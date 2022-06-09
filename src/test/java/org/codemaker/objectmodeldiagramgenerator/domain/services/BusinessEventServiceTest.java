package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEvent;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEventDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenario;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioSequenceDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionStateDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.repositories.DescriptorRepository;
import org.codemaker.objectmodeldiagramgenerator.testutil.BusinessEventTestDataCreator;
import org.codemaker.objectmodeldiagramgenerator.testutil.ScenarioTestDataCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BusinessEventServiceTest {

  private DescriptorRepository descriptorRepository;
  private ScenarioService scenarioService;

  @BeforeEach
  void setUp() {
    descriptorRepository = new DescriptorRepository() {
      @Override
      public Set<OmgTransitionStateDescriptor> findTransitionStateDescriptors() {
        throw new UnsupportedOperationException();
      }

      @Override
      public Set<OmgScenarioDescriptor> findScenarioDescriptors() {
        throw new UnsupportedOperationException();
      }

      @Override
      public Set<OmgBusinessEventDescriptor> findBusinessEventDescriptors() {
        Set<OmgBusinessEventDescriptor> result = new HashSet<>();
        result.add(BusinessEventTestDataCreator.createDescriptor("event0101"));
        result.add(BusinessEventTestDataCreator.createDescriptor("event0102"));
        result.add(BusinessEventTestDataCreator.createDescriptor("event0103"));
        result.add(BusinessEventTestDataCreator.createDescriptor("event0201"));
        result.add(BusinessEventTestDataCreator.createDescriptor("event0202"));
        result.add(BusinessEventTestDataCreator.createDescriptor("event0203"));
        result.add(BusinessEventTestDataCreator.createDescriptor("event0301"));
        result.add(BusinessEventTestDataCreator.createDescriptor("event0302"));
        result.add(BusinessEventTestDataCreator.createDescriptor("event0401"));
        result.add(BusinessEventTestDataCreator.createDescriptor("event0402"));
        result.add(BusinessEventTestDataCreator.createDescriptor("event0501"));
        result.add(BusinessEventTestDataCreator.createDescriptor("event0502"));
        return result;
      }

      @Override
      public Set<OmgScenarioSequenceDescriptor> findScenarioSequenceDescriptors() {
        throw new UnsupportedOperationException();
      }
    };
    scenarioService = new ScenarioService(null) {
      @Override
      public Map<String, OmgScenario> findScenarioMap() {
        return ScenarioTestDataCreator.createScenarioMap();
      }
    };
  }

  @Test
  void findBusinessEventMap() {
    // Arrange
    OmgBusinessEvent event0101 = BusinessEventTestDataCreator.create("event0101");
    OmgBusinessEvent event0102 = BusinessEventTestDataCreator.create("event0102");
    OmgBusinessEvent event0103 = BusinessEventTestDataCreator.create("event0103");
    OmgBusinessEvent event0201 = BusinessEventTestDataCreator.create("event0201");
    OmgBusinessEvent event0202 = BusinessEventTestDataCreator.create("event0202");
    OmgBusinessEvent event0203 = BusinessEventTestDataCreator.create("event0203");
    OmgBusinessEvent event0301 = BusinessEventTestDataCreator.create("event0301");
    OmgBusinessEvent event0302 = BusinessEventTestDataCreator.create("event0302");
    OmgBusinessEvent event0401 = BusinessEventTestDataCreator.create("event0401");
    OmgBusinessEvent event0402 = BusinessEventTestDataCreator.create("event0402");
    OmgBusinessEvent event0501 = BusinessEventTestDataCreator.create("event0501");
    OmgBusinessEvent event0502 = BusinessEventTestDataCreator.create("event0502");
    OmgBusinessEvent event0000 = BusinessEventTestDataCreator.create("event0000");

    // Act
    BusinessEventService cut = new BusinessEventService(descriptorRepository, scenarioService);
    Map<String, OmgBusinessEvent> result = cut.findBusinessEventMap();

    // Assert
    assertEquals(12, result.size());
    assertTrue(result.containsKey(event0101.getKey()));
    assertTrue(result.containsKey(event0102.getKey()));
    assertTrue(result.containsKey(event0103.getKey()));
    assertTrue(result.containsKey(event0201.getKey()));
    assertTrue(result.containsKey(event0202.getKey()));
    assertTrue(result.containsKey(event0203.getKey()));
    assertTrue(result.containsKey(event0301.getKey()));
    assertTrue(result.containsKey(event0302.getKey()));
    assertTrue(result.containsKey(event0401.getKey()));
    assertTrue(result.containsKey(event0402.getKey()));
    assertTrue(result.containsKey(event0501.getKey()));
    assertTrue(result.containsKey(event0502.getKey()));
    assertFalse(result.containsKey(event0000.getKey()));
    assertEquals(event0101, result.get(event0101.getKey()));
    assertEquals(event0102, result.get(event0102.getKey()));
    assertEquals(event0103, result.get(event0103.getKey()));
    assertEquals(event0201, result.get(event0201.getKey()));
    assertEquals(event0202, result.get(event0202.getKey()));
    assertEquals(event0203, result.get(event0203.getKey()));
    assertEquals(event0301, result.get(event0301.getKey()));
    assertEquals(event0302, result.get(event0302.getKey()));
    assertEquals(event0401, result.get(event0401.getKey()));
    assertEquals(event0402, result.get(event0402.getKey()));
    assertEquals(event0501, result.get(event0501.getKey()));
    assertEquals(event0502, result.get(event0502.getKey()));
  }
}