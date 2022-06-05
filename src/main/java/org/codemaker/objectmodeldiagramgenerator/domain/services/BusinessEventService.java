package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEvent;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEventDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenario;
import org.codemaker.objectmodeldiagramgenerator.domain.repositories.DescriptorRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BusinessEventService {

  private final DescriptorRepository descriptorRepository;
  private final ScenarioService scenarioService;

  public BusinessEventService(DescriptorRepository descriptorRepository, ScenarioService scenarioService) {
    this.descriptorRepository = descriptorRepository;
    this.scenarioService = scenarioService;
  }

  public Map<String, OmgBusinessEvent> findBusinessEventMap() {
    Map<String, OmgBusinessEvent> result = new HashMap<>();
    Set<OmgBusinessEventDescriptor> businessEventDescriptors = descriptorRepository.findBusinessEventDescriptors();
    Map<String, OmgScenario> scenarioMap = scenarioService.findScenarioMap();

    for (OmgBusinessEventDescriptor businessEventDescriptor : businessEventDescriptors) {
      OmgScenario scenario = scenarioMap.get(businessEventDescriptor.getScenarioKey());
      OmgBusinessEvent businessEvent = new OmgBusinessEvent(businessEventDescriptor.getKey(), businessEventDescriptor.getDescription(),
              scenario);
      result.put(businessEvent.getKey(), businessEvent);
    }
    return result;
  }
}
