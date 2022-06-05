package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenario;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.repositories.DescriptorRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class ScenarioService {

  private final DescriptorRepository descriptorRepository;

  public ScenarioService(DescriptorRepository descriptorRepository) {
    this.descriptorRepository = descriptorRepository;
  }

  public Map<String, OmgScenario> findScenarioMap() {
    Map<String, OmgScenario> result = new HashMap<>();

    List<OmgScenarioDescriptor> scenarioDescriptors = new ArrayList<>(descriptorRepository.findScenarioDescriptors());

    // Pass 1: Create all OmgScenario instances and store them in the result, but leave the predecessor references empty.
    for (OmgScenarioDescriptor scenarioDescriptor : scenarioDescriptors) {
      OmgScenario scenario = new OmgScenario(scenarioDescriptor.getKey(), scenarioDescriptor.getDescription(), new HashSet<>());
      result.put(scenario.getKey(), scenario);
    }

    // Pass 2: Now that we have already created all OmgScenarios, we can now search for all the predecessors in the result.
    for (OmgScenarioDescriptor scenarioDescriptor : scenarioDescriptors) {
      OmgScenario scenario = result.get(scenarioDescriptor.getKey());
      for (String predecessorScenarioKey : scenarioDescriptor.getPredecessorKeys()) {
        OmgScenario predecessorScenario = result.get(predecessorScenarioKey);
        scenario.getPredecessors().add(predecessorScenario);
      }
    }

    return result;
  }
}
