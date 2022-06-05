package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEventDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObjectSequenceDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenario;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionStateDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.repositories.DescriptorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
      public OmgTransitionStateDescriptor findTransitionStateDescriptor(String transitionStateDescriptorKey) {
        throw new UnsupportedOperationException();
      }

      @Override
      public Set<OmgScenarioDescriptor> findScenarioDescriptors() {
        Set<OmgScenarioDescriptor> result = new HashSet<>();
        result.add(new OmgScenarioDescriptor("scenario1", "Scenario 01", new HashSet<>()));
        result.add(new OmgScenarioDescriptor("scenario2", "Scenario 02", new HashSet<>(Arrays.asList("scenario1"))));
        result.add(new OmgScenarioDescriptor("scenario3", "Scenario 03", new HashSet<>(Arrays.asList("scenario1", "scenario2"))));
        result.add(
                new OmgScenarioDescriptor("scenario4", "Scenario 04", new HashSet<>(Arrays.asList("scenario1", "scenario2", "scenario3"))));
        result.add(new OmgScenarioDescriptor("scenario5", "Scenario 05", new HashSet<>(Arrays.asList("scenario3", "scenario4"))));
        result.add(new OmgScenarioDescriptor("scenario6", "Scenario 06", new HashSet<>()));
        result.add(
                new OmgScenarioDescriptor("scenario7", "Scenario 07", new HashSet<>(Arrays.asList("scenario1", "scenario3", "scenario5"))));
        result.add(
                new OmgScenarioDescriptor("scenario8", "Scenario 08", new HashSet<>(Arrays.asList("scenario5", "scenario6", "scenario7"))));
        result.add(new OmgScenarioDescriptor("scenario9", "Scenario 09", new HashSet<>(
                Arrays.asList("scenario1", "scenario2", "scenario3", "scenario4", "scenario5", "scenario6", "scenario7", "scenario8"))));
        return result;
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
  void findScenarioMap() {
    // Assign
    OmgScenario scenario1 = new OmgScenario("scenario1", "Scenario 01", new HashSet<>());
    OmgScenario scenario2 = new OmgScenario("scenario2", "Scenario 02", new HashSet<>(Arrays.asList(scenario1)));
    OmgScenario scenario3 = new OmgScenario("scenario3", "Scenario 03", new HashSet<>(Arrays.asList(scenario1, scenario2)));
    OmgScenario scenario4 = new OmgScenario("scenario4", "Scenario 04", new HashSet<>(Arrays.asList(scenario1, scenario2, scenario3)));
    OmgScenario scenario5 = new OmgScenario("scenario5", "Scenario 05", new HashSet<>(Arrays.asList(scenario3, scenario4)));
    OmgScenario scenario6 = new OmgScenario("scenario6", "Scenario 06", new HashSet<>());
    OmgScenario scenario7 = new OmgScenario("scenario7", "Scenario 07", new HashSet<>(Arrays.asList(scenario1, scenario3, scenario5)));
    OmgScenario scenario8 = new OmgScenario("scenario8", "Scenario 08", new HashSet<>(Arrays.asList(scenario5, scenario6, scenario7)));
    OmgScenario scenario9 = new OmgScenario("scenario9", "Scenario 09",
            new HashSet<>(Arrays.asList(scenario1, scenario2, scenario3, scenario4, scenario5, scenario6, scenario7, scenario8)));

    // Act
    ScenarioService cut = new ScenarioService(descriptorRepository);
    Map<String, OmgScenario> result = cut.findScenarioMap();

    // Assert
    assertEquals(9, result.size());
    assertTrue(result.containsKey(scenario1.getKey()));
    assertTrue(result.containsKey(scenario2.getKey()));
    assertTrue(result.containsKey(scenario3.getKey()));
    assertTrue(result.containsKey(scenario4.getKey()));
    assertTrue(result.containsKey(scenario5.getKey()));
    assertTrue(result.containsKey(scenario6.getKey()));
    assertTrue(result.containsKey(scenario7.getKey()));
    assertTrue(result.containsKey(scenario8.getKey()));
    assertTrue(result.containsKey(scenario9.getKey()));
    assertEquals(scenario1, result.get(scenario1.getKey()));
    assertEquals(scenario2, result.get(scenario2.getKey()));
    assertEquals(scenario3, result.get(scenario3.getKey()));
    assertEquals(scenario4, result.get(scenario4.getKey()));
    assertEquals(scenario5, result.get(scenario5.getKey()));
    assertEquals(scenario6, result.get(scenario6.getKey()));
    assertEquals(scenario7, result.get(scenario7.getKey()));
    assertEquals(scenario8, result.get(scenario8.getKey()));
    assertEquals(scenario9, result.get(scenario9.getKey()));
  }
}