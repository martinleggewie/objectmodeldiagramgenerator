package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEventDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenario;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioSequenceDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionStateDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.repositories.DescriptorRepository;
import org.codemaker.objectmodeldiagramgenerator.testutil.ScenarioTestDataCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScenarioServiceTest {

  private DescriptorRepository descriptorRepository;

  @BeforeEach
  void setUp() {
    descriptorRepository = new DescriptorRepository() {
      @Override
      public Set<OmgTransitionStateDescriptor> findTransitionStateDescriptors() {
        throw new UnsupportedOperationException();
      }

      @Override
      public Set<OmgScenarioDescriptor> findScenarioDescriptors() {
        Set<OmgScenarioDescriptor> result = new HashSet<>();
        result.add(ScenarioTestDataCreator.createDescriptor("scenario1"));
        result.add(ScenarioTestDataCreator.createDescriptor("scenario2"));
        result.add(ScenarioTestDataCreator.createDescriptor("scenario3"));
        result.add(ScenarioTestDataCreator.createDescriptor("scenario4"));
        result.add(ScenarioTestDataCreator.createDescriptor("scenario5"));
        return result;
      }

      @Override
      public Set<OmgBusinessEventDescriptor> findBusinessEventDescriptors() {
        throw new UnsupportedOperationException();
      }

      @Override
      public Set<OmgScenarioSequenceDescriptor> findScenarioSequenceDescriptors() {
        throw new UnsupportedOperationException();
      }
    };
  }

  @Test
  void findScenarioMap() {
    // Arrange
    OmgScenario scenario1 = ScenarioTestDataCreator.create("scenario1");
    OmgScenario scenario2 = ScenarioTestDataCreator.create("scenario2");
    OmgScenario scenario3 = ScenarioTestDataCreator.create("scenario3");
    OmgScenario scenario4 = ScenarioTestDataCreator.create("scenario4");
    OmgScenario scenario5 = ScenarioTestDataCreator.create("scenario5");
    OmgScenario scenario0 = ScenarioTestDataCreator.create("scenario0");

    // Act
    ScenarioService cut = new ScenarioService(descriptorRepository);
    Map<String, OmgScenario> result = cut.findScenarioMap();

    // Assert
    assertEquals(5, result.size());
    assertTrue(result.containsKey(scenario1.getKey()));
    assertTrue(result.containsKey(scenario2.getKey()));
    assertTrue(result.containsKey(scenario3.getKey()));
    assertTrue(result.containsKey(scenario4.getKey()));
    assertTrue(result.containsKey(scenario5.getKey()));
    assertFalse(result.containsKey(scenario0.getKey()));
    assertEquals(scenario1, result.get(scenario1.getKey()));
    assertEquals(scenario2, result.get(scenario2.getKey()));
    assertEquals(scenario3, result.get(scenario3.getKey()));
    assertEquals(scenario4, result.get(scenario4.getKey()));
    assertEquals(scenario5, result.get(scenario5.getKey()));
  }
}