package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEvent;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEventDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenario;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioSequence;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioSequenceDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionState;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionStateDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.repositories.DescriptorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.codemaker.objectmodeldiagramgenerator.testutil.BusinessEventTestDataCreator.createBusinessEventMap;
import static org.codemaker.objectmodeldiagramgenerator.testutil.ScenarioSequenceTestDataCreator.createScenarioSequenceDescriptors_state1;
import static org.codemaker.objectmodeldiagramgenerator.testutil.ScenarioSequenceTestDataCreator.createScenarioSequences_state1;
import static org.codemaker.objectmodeldiagramgenerator.testutil.ScenarioSequenceTestDataCreator.createScenarioSequences_state2;
import static org.codemaker.objectmodeldiagramgenerator.testutil.ScenarioTestDataCreator.createScenarioMap;
import static org.codemaker.objectmodeldiagramgenerator.testutil.TransitionStateTestDataCreator.createTransitionStateMap;

class ScenarioSequenceServiceTest {

  private BusinessEventService businessEventService;
  private ScenarioService scenarioService;
  private TransitionStateService transitionStateService;
  private DescriptorRepository descriptorRepository;

  @BeforeEach
  void setUp() {
    // Set up the TransitionStateService
    transitionStateService = new TransitionStateService(null) {
      @Override
      public Map<String, OmgTransitionState> findTransitionStateMap() {
        return createTransitionStateMap();
      }
    };

    // Set up the ScenarioService
    scenarioService = new ScenarioService(null) {
      @Override
      public Map<String, OmgScenario> findScenarioMap() {
        return createScenarioMap();
      }
    };

    // Set up the BusinessEventService, and directly make use of the just previously created ScenarioService
    businessEventService = new BusinessEventService(null, scenarioService) {
      @Override
      public Map<String, OmgBusinessEvent> findBusinessEventMap() {
        return createBusinessEventMap();
      }
    };

    // Set up the ObjectSequenceDescriptor part of the DescriptorRepository
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
        throw new UnsupportedOperationException();
      }

      @Override
      public Set<OmgScenarioSequenceDescriptor> findScenarioSequenceDescriptors() {
        return createScenarioSequenceDescriptors_state1();
      }
    };
  }

  @Test
  void findScenarioSequences() {
    // Arrange
    Set<OmgScenarioSequence> scenarioSequences = createScenarioSequences_state1();
    scenarioSequences.addAll(createScenarioSequences_state2());

    // Act
    ScenarioSequenceService cut = new ScenarioSequenceService(businessEventService, scenarioService, transitionStateService,
            descriptorRepository);
    Set<OmgScenarioSequence> result = cut.findScenarioSequences();

    // Assert
    //    assertEquals(6, result.size());
    //    assertEquals(scenarioSequences, result);
  }
}