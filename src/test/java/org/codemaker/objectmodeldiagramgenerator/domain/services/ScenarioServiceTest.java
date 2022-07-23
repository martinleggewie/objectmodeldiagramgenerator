package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEventDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenario;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioSequenceDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionStateDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.repositories.DescriptorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.codemaker.objectmodeldiagramgenerator.testutil.ScenarioTestDataCreator.createScenarioDescriptorMap;
import static org.codemaker.objectmodeldiagramgenerator.testutil.ScenarioTestDataCreator.createScenarioMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        return new HashSet<>(createScenarioDescriptorMap().values());
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
    Map<String, OmgScenario> scenarioMap = createScenarioMap();

    // Act
    ScenarioService cut = new ScenarioService(descriptorRepository);
    Map<String, OmgScenario> result = cut.findScenarioMap();

    // Assert
    assertEquals(6, result.size());
    assertEquals(scenarioMap, result);
  }
}