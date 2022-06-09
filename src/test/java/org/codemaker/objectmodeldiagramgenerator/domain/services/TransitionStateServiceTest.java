package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEventDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioSequenceDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionState;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionStateDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.repositories.DescriptorRepository;
import org.codemaker.objectmodeldiagramgenerator.testutil.TransitionStateTestDataCreator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TransitionStateServiceTest {

  private DescriptorRepository descriptorRepository;

  @org.junit.jupiter.api.BeforeEach
  void setUp() throws IOException {
    descriptorRepository = new DescriptorRepository() {
      @Override
      public Set<OmgTransitionStateDescriptor> findTransitionStateDescriptors() {
        Set<OmgTransitionStateDescriptor> result = new HashSet<>();
        result.add(TransitionStateTestDataCreator.createDescriptor("state1"));
        result.add(TransitionStateTestDataCreator.createDescriptor("state2"));
        result.add(TransitionStateTestDataCreator.createDescriptor("state3"));
        return result;
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
        throw new UnsupportedOperationException();
      }
    };
  }

  @Test
  void findTransitionStateMap() {
    // Arrange
    OmgTransitionState transitionState1 = TransitionStateTestDataCreator.create("state1");
    OmgTransitionState transitionState2 = TransitionStateTestDataCreator.create("state2");
    OmgTransitionState transitionState3 = TransitionStateTestDataCreator.create("state3");
    OmgTransitionState transitionState0 = TransitionStateTestDataCreator.create("state0");

    // Act
    TransitionStateService cut = new TransitionStateService(descriptorRepository);
    Map<String, OmgTransitionState> result = cut.findTransitionStateMap();

    // Assert
    assertEquals(3, result.size());
    assertTrue(result.containsKey(transitionState1.getKey()));
    assertTrue(result.containsKey(transitionState2.getKey()));
    assertTrue(result.containsKey(transitionState3.getKey()));
    assertFalse(result.containsKey(transitionState0.getKey()));
    assertEquals(transitionState1, result.get(transitionState1.getKey()));
    assertEquals(transitionState2, result.get(transitionState2.getKey()));
    assertEquals(transitionState3, result.get(transitionState3.getKey()));
  }
}