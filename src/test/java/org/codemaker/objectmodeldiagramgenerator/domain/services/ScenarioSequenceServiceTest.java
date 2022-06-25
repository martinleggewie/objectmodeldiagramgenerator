package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.apache.commons.collections4.CollectionUtils;
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

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.codemaker.objectmodeldiagramgenerator.testutil.BusinessEventTestDataCreator.createBusinessEventMap;
import static org.codemaker.objectmodeldiagramgenerator.testutil.ScenarioSequenceTestDataCreator.createScenarioSequenceDescriptors_state1;
import static org.codemaker.objectmodeldiagramgenerator.testutil.ScenarioSequenceTestDataCreator.createScenarioSequenceDescriptors_state2;
import static org.codemaker.objectmodeldiagramgenerator.testutil.ScenarioSequenceTestDataCreator.createScenarioSequences_state1;
import static org.codemaker.objectmodeldiagramgenerator.testutil.ScenarioSequenceTestDataCreator.createScenarioSequences_state2;
import static org.codemaker.objectmodeldiagramgenerator.testutil.ScenarioTestDataCreator.createScenarioMap;
import static org.codemaker.objectmodeldiagramgenerator.testutil.TransitionStateTestDataCreator.createTransitionStateMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        Set<OmgScenarioSequenceDescriptor> result = new HashSet<>();
        result.addAll(createScenarioSequenceDescriptors_state1());
        result.addAll(createScenarioSequenceDescriptors_state2());
        return result;
      }
    };
  }

  @Test
  void findScenarioSequences() {
    // Arrange
    Map<String, OmgTransitionState> transitionStateMap = transitionStateService.findTransitionStateMap();
    Map<String, OmgScenario> scenarioMap = scenarioService.findScenarioMap();
    Set<OmgScenarioSequence> scenarioSequences = createScenarioSequences_state1();
    scenarioSequences.addAll(createScenarioSequences_state2());
    OmgScenarioSequence state1Scen1Seq = scenarioSequences.stream()
            .filter(s -> s.getTransitionState().equals(transitionStateMap.get("state1")))
            .filter(s -> s.getScenario().equals(scenarioMap.get("scenario1"))).findFirst().get();
    OmgScenarioSequence state1Scen2Seq = scenarioSequences.stream()
            .filter(s -> s.getTransitionState().equals(transitionStateMap.get("state1")))
            .filter(s -> s.getScenario().equals(scenarioMap.get("scenario2"))).findFirst().get();
    OmgScenarioSequence state1Scen3Seq = scenarioSequences.stream()
            .filter(s -> s.getTransitionState().equals(transitionStateMap.get("state1")))
            .filter(s -> s.getScenario().equals(scenarioMap.get("scenario3"))).findFirst().get();
    OmgScenarioSequence state1Scen4Seq = scenarioSequences.stream()
            .filter(s -> s.getTransitionState().equals(transitionStateMap.get("state1")))
            .filter(s -> s.getScenario().equals(scenarioMap.get("scenario4"))).findFirst().get();
    OmgScenarioSequence state1Scen5Seq = scenarioSequences.stream()
            .filter(s -> s.getTransitionState().equals(transitionStateMap.get("state1")))
            .filter(s -> s.getScenario().equals(scenarioMap.get("scenario5"))).findFirst().get();
    OmgScenarioSequence state2Scen1Seq = scenarioSequences.stream()
            .filter(s -> s.getTransitionState().equals(transitionStateMap.get("state2")))
            .filter(s -> s.getScenario().equals(scenarioMap.get("scenario1"))).findFirst().get();

    // Act
    ScenarioSequenceService cut = new ScenarioSequenceService(businessEventService, scenarioService, transitionStateService,
            descriptorRepository);
    Set<OmgScenarioSequence> result = cut.findScenarioSequences();

    // Assert
    assertEquals(6, result.size());

    OmgScenarioSequence resultState1Scen1Seq = result.stream().filter(s -> s.getTransitionState().equals(transitionStateMap.get("state1")))
            .filter(s -> s.getScenario().equals(scenarioMap.get("scenario1"))).findFirst().get();
    OmgScenarioSequence resultState1Scen2Seq = result.stream().filter(s -> s.getTransitionState().equals(transitionStateMap.get("state1")))
            .filter(s -> s.getScenario().equals(scenarioMap.get("scenario2"))).findFirst().get();
    OmgScenarioSequence resultState1Scen3Seq = result.stream().filter(s -> s.getTransitionState().equals(transitionStateMap.get("state1")))
            .filter(s -> s.getScenario().equals(scenarioMap.get("scenario3"))).findFirst().get();
    OmgScenarioSequence resultState1Scen4Seq = result.stream().filter(s -> s.getTransitionState().equals(transitionStateMap.get("state1")))
            .filter(s -> s.getScenario().equals(scenarioMap.get("scenario4"))).findFirst().get();
    OmgScenarioSequence resultState1Scen5Seq = result.stream().filter(s -> s.getTransitionState().equals(transitionStateMap.get("state1")))
            .filter(s -> s.getScenario().equals(scenarioMap.get("scenario5"))).findFirst().get();
    OmgScenarioSequence resultState2Scen1Seq = result.stream().filter(s -> s.getTransitionState().equals(transitionStateMap.get("state2")))
            .filter(s -> s.getScenario().equals(scenarioMap.get("scenario1"))).findFirst().get();

    assertEquals(7, resultState1Scen1Seq.getScenarioSequenceSteps().size());
    assertEquals(5, resultState1Scen2Seq.getScenarioSequenceSteps().size());
    assertEquals(11, resultState1Scen3Seq.getScenarioSequenceSteps().size());
    assertEquals(4, resultState1Scen4Seq.getScenarioSequenceSteps().size());
    assertEquals(7, resultState1Scen5Seq.getScenarioSequenceSteps().size());
    assertEquals(3, resultState2Scen1Seq.getScenarioSequenceSteps().size());

    assertEquals(state1Scen1Seq.getScenarioSequenceSteps().get(0), resultState1Scen1Seq.getScenarioSequenceSteps().get(0));
    assertEquals(state1Scen1Seq.getScenarioSequenceSteps().get(1), resultState1Scen1Seq.getScenarioSequenceSteps().get(1));
    assertEquals(state1Scen1Seq.getScenarioSequenceSteps().get(2), resultState1Scen1Seq.getScenarioSequenceSteps().get(2));
    assertEquals(state1Scen1Seq.getScenarioSequenceSteps().get(3), resultState1Scen1Seq.getScenarioSequenceSteps().get(3));
    assertEquals(state1Scen1Seq.getScenarioSequenceSteps().get(4), resultState1Scen1Seq.getScenarioSequenceSteps().get(4));
    assertEquals(state1Scen1Seq.getScenarioSequenceSteps().get(5), resultState1Scen1Seq.getScenarioSequenceSteps().get(5));
    assertEquals(state1Scen1Seq.getScenarioSequenceSteps().get(6), resultState1Scen1Seq.getScenarioSequenceSteps().get(6));

    assertEquals(state1Scen2Seq.getScenarioSequenceSteps().get(0), resultState1Scen2Seq.getScenarioSequenceSteps().get(0));
    assertEquals(state1Scen2Seq.getScenarioSequenceSteps().get(1), resultState1Scen2Seq.getScenarioSequenceSteps().get(1));
    assertEquals(state1Scen2Seq.getScenarioSequenceSteps().get(2), resultState1Scen2Seq.getScenarioSequenceSteps().get(2));
    assertEquals(state1Scen2Seq.getScenarioSequenceSteps().get(3), resultState1Scen2Seq.getScenarioSequenceSteps().get(3));
    assertEquals(state1Scen2Seq.getScenarioSequenceSteps().get(4), resultState1Scen2Seq.getScenarioSequenceSteps().get(4));

    assertEquals(state1Scen3Seq.getScenarioSequenceSteps().get(0), resultState1Scen3Seq.getScenarioSequenceSteps().get(0));
    assertEquals(state1Scen3Seq.getScenarioSequenceSteps().get(1), resultState1Scen3Seq.getScenarioSequenceSteps().get(1));
    assertEquals(state1Scen3Seq.getScenarioSequenceSteps().get(2), resultState1Scen3Seq.getScenarioSequenceSteps().get(2));
    assertEquals(state1Scen3Seq.getScenarioSequenceSteps().get(3), resultState1Scen3Seq.getScenarioSequenceSteps().get(3));
    assertEquals(state1Scen3Seq.getScenarioSequenceSteps().get(4), resultState1Scen3Seq.getScenarioSequenceSteps().get(4));
    assertEquals(state1Scen3Seq.getScenarioSequenceSteps().get(5), resultState1Scen3Seq.getScenarioSequenceSteps().get(5));
    assertEquals(state1Scen3Seq.getScenarioSequenceSteps().get(6), resultState1Scen3Seq.getScenarioSequenceSteps().get(6));
    assertEquals(state1Scen3Seq.getScenarioSequenceSteps().get(7), resultState1Scen3Seq.getScenarioSequenceSteps().get(7));
    assertEquals(state1Scen3Seq.getScenarioSequenceSteps().get(8), resultState1Scen3Seq.getScenarioSequenceSteps().get(8));
    assertEquals(state1Scen3Seq.getScenarioSequenceSteps().get(9), resultState1Scen3Seq.getScenarioSequenceSteps().get(9));
    assertEquals(state1Scen3Seq.getScenarioSequenceSteps().get(10), resultState1Scen3Seq.getScenarioSequenceSteps().get(10));

    assertEquals(state1Scen4Seq.getScenarioSequenceSteps().get(0), resultState1Scen4Seq.getScenarioSequenceSteps().get(0));
    assertEquals(state1Scen4Seq.getScenarioSequenceSteps().get(1), resultState1Scen4Seq.getScenarioSequenceSteps().get(1));
    assertEquals(state1Scen4Seq.getScenarioSequenceSteps().get(2), resultState1Scen4Seq.getScenarioSequenceSteps().get(2));
    assertEquals(state1Scen4Seq.getScenarioSequenceSteps().get(3), resultState1Scen4Seq.getScenarioSequenceSteps().get(3));

    assertEquals(state1Scen5Seq.getScenarioSequenceSteps().get(0), resultState1Scen5Seq.getScenarioSequenceSteps().get(0));
    assertEquals(state1Scen5Seq.getScenarioSequenceSteps().get(1), resultState1Scen5Seq.getScenarioSequenceSteps().get(1));
    assertEquals(state1Scen5Seq.getScenarioSequenceSteps().get(2), resultState1Scen5Seq.getScenarioSequenceSteps().get(2));
    assertEquals(state1Scen5Seq.getScenarioSequenceSteps().get(3), resultState1Scen5Seq.getScenarioSequenceSteps().get(3));
    assertEquals(state1Scen5Seq.getScenarioSequenceSteps().get(4), resultState1Scen5Seq.getScenarioSequenceSteps().get(4));
    assertEquals(state1Scen5Seq.getScenarioSequenceSteps().get(5), resultState1Scen5Seq.getScenarioSequenceSteps().get(5));
    assertEquals(state1Scen5Seq.getScenarioSequenceSteps().get(6), resultState1Scen5Seq.getScenarioSequenceSteps().get(6));

    assertEquals(state2Scen1Seq.getScenarioSequenceSteps().get(0), resultState2Scen1Seq.getScenarioSequenceSteps().get(0));
    assertEquals(state2Scen1Seq.getScenarioSequenceSteps().get(1), resultState2Scen1Seq.getScenarioSequenceSteps().get(1));
    assertEquals(state2Scen1Seq.getScenarioSequenceSteps().get(2), resultState2Scen1Seq.getScenarioSequenceSteps().get(2));

    assertEquals(state1Scen1Seq, resultState1Scen1Seq);
    assertEquals(state1Scen2Seq, resultState1Scen2Seq);
    assertEquals(state1Scen3Seq, resultState1Scen3Seq);
    assertEquals(state1Scen4Seq, resultState1Scen4Seq);
    assertEquals(state1Scen5Seq, resultState1Scen5Seq);
    assertEquals(state2Scen1Seq, resultState2Scen1Seq);

    assertTrue(CollectionUtils.isEqualCollection(scenarioSequences, result));
  }
}