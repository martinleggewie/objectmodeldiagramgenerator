package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEvent;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEventDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObjectSequenceDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenario;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionStateDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.repositories.DescriptorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        Set<OmgBusinessEventDescriptor> result = new HashSet<>();
        result.add(new OmgBusinessEventDescriptor("businessEvent1", "Business Event 1", "scenario1"));
        result.add(new OmgBusinessEventDescriptor("businessEvent2", "Business Event 2", "scenario1"));
        result.add(new OmgBusinessEventDescriptor("businessEvent3", "Business Event 3", "scenario1"));
        result.add(new OmgBusinessEventDescriptor("businessEvent4", "Business Event 4", "scenario2"));
        result.add(new OmgBusinessEventDescriptor("businessEvent5", "Business Event 5", "scenario2"));
        result.add(new OmgBusinessEventDescriptor("businessEvent6", "Business Event 6", "scenario3"));
        result.add(new OmgBusinessEventDescriptor("businessEvent7", "Business Event 7", "scenario4"));
        result.add(new OmgBusinessEventDescriptor("businessEvent8", "Business Event 8", "scenario4"));
        result.add(new OmgBusinessEventDescriptor("businessEvent9", "Business Event 9", "scenario4"));
        return result;
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
    scenarioService = new ScenarioService(null) {
      @Override
      public Map<String, OmgScenario> findScenarioMap() {
        Map<String, OmgScenario> result = new HashMap<>();
        result.put("scenario1", new OmgScenario("scenario1", "Scenario 1", new HashSet<>()));
        result.put("scenario2", new OmgScenario("scenario2", "Scenario 2", new HashSet<>()));
        result.put("scenario3", new OmgScenario("scenario3", "Scenario 3", new HashSet<>()));
        result.put("scenario4", new OmgScenario("scenario4", "Scenario 4", new HashSet<>()));
        return result;
      }
    };
  }

  @Test
  void findBusinessEventMap() {
    // Arrange
    OmgBusinessEvent businessEvent1 = new OmgBusinessEvent("businessEvent1", "Business Event 1",
            new OmgScenario("scenario1", "Scenario 1", new HashSet<>()));
    OmgBusinessEvent businessEvent2 = new OmgBusinessEvent("businessEvent2", "Business Event 2",
            new OmgScenario("scenario1", "Scenario 1", new HashSet<>()));
    OmgBusinessEvent businessEvent3 = new OmgBusinessEvent("businessEvent3", "Business Event 3",
            new OmgScenario("scenario1", "Scenario 1", new HashSet<>()));
    OmgBusinessEvent businessEvent4 = new OmgBusinessEvent("businessEvent4", "Business Event 4",
            new OmgScenario("scenario2", "Scenario 2", new HashSet<>()));
    OmgBusinessEvent businessEvent5 = new OmgBusinessEvent("businessEvent5", "Business Event 5",
            new OmgScenario("scenario2", "Scenario 2", new HashSet<>()));
    OmgBusinessEvent businessEvent6 = new OmgBusinessEvent("businessEvent6", "Business Event 6",
            new OmgScenario("scenario3", "Scenario 3", new HashSet<>()));
    OmgBusinessEvent businessEvent7 = new OmgBusinessEvent("businessEvent7", "Business Event 7",
            new OmgScenario("scenario4", "Scenario 4", new HashSet<>()));
    OmgBusinessEvent businessEvent8 = new OmgBusinessEvent("businessEvent8", "Business Event 8",
            new OmgScenario("scenario4", "Scenario 4", new HashSet<>()));
    OmgBusinessEvent businessEvent9 = new OmgBusinessEvent("businessEvent9", "Business Event 9",
            new OmgScenario("scenario4", "Scenario 4", new HashSet<>()));

    // Act
    BusinessEventService cut = new BusinessEventService(descriptorRepository, scenarioService);
    Map<String, OmgBusinessEvent> result = cut.findBusinessEventMap();

    // Assert
    assertEquals(9, result.size());
    assertTrue(result.containsKey(businessEvent1.getKey()));
    assertTrue(result.containsKey(businessEvent2.getKey()));
    assertTrue(result.containsKey(businessEvent3.getKey()));
    assertTrue(result.containsKey(businessEvent4.getKey()));
    assertTrue(result.containsKey(businessEvent5.getKey()));
    assertTrue(result.containsKey(businessEvent6.getKey()));
    assertTrue(result.containsKey(businessEvent7.getKey()));
    assertTrue(result.containsKey(businessEvent8.getKey()));
    assertTrue(result.containsKey(businessEvent9.getKey()));
    assertEquals(businessEvent1, result.get(businessEvent1.getKey()));
    assertEquals(businessEvent2, result.get(businessEvent2.getKey()));
    assertEquals(businessEvent3, result.get(businessEvent3.getKey()));
    assertEquals(businessEvent4, result.get(businessEvent4.getKey()));
    assertEquals(businessEvent5, result.get(businessEvent5.getKey()));
    assertEquals(businessEvent6, result.get(businessEvent6.getKey()));
    assertEquals(businessEvent7, result.get(businessEvent7.getKey()));
    assertEquals(businessEvent8, result.get(businessEvent8.getKey()));
    assertEquals(businessEvent9, result.get(businessEvent9.getKey()));
  }
}