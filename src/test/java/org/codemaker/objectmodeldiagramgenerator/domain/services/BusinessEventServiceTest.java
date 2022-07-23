package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEvent;
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

import static org.codemaker.objectmodeldiagramgenerator.testutil.BusinessEventTestDataCreator.createBusinessEventDescriptorMap;
import static org.codemaker.objectmodeldiagramgenerator.testutil.BusinessEventTestDataCreator.createBusinessEventMap;
import static org.codemaker.objectmodeldiagramgenerator.testutil.ScenarioTestDataCreator.createScenarioMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        return new HashSet<>(createBusinessEventDescriptorMap().values());
      }

      @Override
      public Set<OmgScenarioSequenceDescriptor> findScenarioSequenceDescriptors() {
        throw new UnsupportedOperationException();
      }
    };

    scenarioService = new ScenarioService(null) {
      @Override
      public Map<String, OmgScenario> findScenarioMap() {
        return createScenarioMap();
      }
    };
  }

  @Test
  void findBusinessEventMap() {
    // Arrange
    Map<String, OmgBusinessEvent> businessEventMap = createBusinessEventMap();

    // Act
    BusinessEventService cut = new BusinessEventService(descriptorRepository, scenarioService);
    Map<String, OmgBusinessEvent> result = cut.findBusinessEventMap();

    // Assert
    assertEquals(17, result.size());
    assertEquals(businessEventMap, result);
  }
}