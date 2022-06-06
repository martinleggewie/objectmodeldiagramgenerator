package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEventDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObjectSequenceDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionState;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionStateDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.repositories.DescriptorRepository;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TransitionStateServiceTest {

  private DescriptorRepository descriptorRepository;

  @org.junit.jupiter.api.BeforeEach
  void setUp() throws IOException {
    descriptorRepository = new DescriptorRepository() {
      @Override
      public Set<OmgTransitionStateDescriptor> findTransitionStateDescriptors() {
        Set<OmgTransitionStateDescriptor> result = new HashSet<>();
        result.add(new OmgTransitionStateDescriptor("state1", "The first state", PROPERTYVALUE_NOTSET));
        result.add(new OmgTransitionStateDescriptor("state2", "The second state", "state1"));
        result.add(new OmgTransitionStateDescriptor("state3", "The third state", "state2"));
        result.add(new OmgTransitionStateDescriptor("state4", "The fourth state", "state3"));
        result.add(new OmgTransitionStateDescriptor("state5", "The fifth state", PROPERTYVALUE_NOTSET));
        return result;
      }

      @Override
      public OmgTransitionStateDescriptor findTransitionStateDescriptor(String transitionStateDescriptorKey) {
        throw new UnsupportedOperationException();
      }

      @Override
      public Set<OmgScenarioDescriptor> findScenarioDescriptors() {
        throw new UnsupportedOperationException();
      }

      @Override
      public OmgScenarioDescriptor findScenarioDescriptor(String scenarioDescriptorKey) {
        throw new UnsupportedOperationException();
      }

      @Override
      public Set<OmgBusinessEventDescriptor> findBusinessEventDescriptors() {
        throw new UnsupportedOperationException();
      }

      @Override
      public OmgBusinessEventDescriptor findBusinessEventDescriptor(String businessDescriptorKey) {
        throw new UnsupportedOperationException();
      }

      @Override
      public Set<OmgObjectSequenceDescriptor> findObjectSequenceDescriptors() {
        throw new UnsupportedOperationException();
      }
    };
  }

  @Test
  void findTransitionStateMap() {
    // Arrange
    OmgTransitionState transitionState1 = new OmgTransitionState("state1", "The first state", null);
    OmgTransitionState transitionState2 = new OmgTransitionState("state2", "The second state", transitionState1);
    OmgTransitionState transitionState3 = new OmgTransitionState("state3", "The third state", transitionState2);
    OmgTransitionState transitionState4 = new OmgTransitionState("state4", "The fourth state", transitionState3);
    OmgTransitionState transitionState5 = new OmgTransitionState("state5", "The fifth state", null);

    // Act
    TransitionStateService cut = new TransitionStateService(descriptorRepository);
    Map<String, OmgTransitionState> result = cut.findTransitionStateMap();

    // Assert
    assertEquals(5, result.size());
    assertTrue(result.containsKey(transitionState1.getKey()));
    assertTrue(result.containsKey(transitionState2.getKey()));
    assertTrue(result.containsKey(transitionState3.getKey()));
    assertTrue(result.containsKey(transitionState4.getKey()));
    assertTrue(result.containsKey(transitionState5.getKey()));
    assertEquals(transitionState1, result.get(transitionState1.getKey()));
    assertEquals(transitionState2, result.get(transitionState2.getKey()));
    assertEquals(transitionState3, result.get(transitionState3.getKey()));
    assertEquals(transitionState4, result.get(transitionState4.getKey()));
    assertEquals(transitionState5, result.get(transitionState5.getKey()));
  }
}