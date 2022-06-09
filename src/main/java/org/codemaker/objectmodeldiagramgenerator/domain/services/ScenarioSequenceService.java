package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioSequence;
import org.codemaker.objectmodeldiagramgenerator.domain.repositories.DescriptorRepository;

import java.util.HashSet;
import java.util.Set;

public class ScenarioSequenceService {

  private final BusinessEventService businessEventService;
  private final ScenarioService scenarioService;
  private final TransitionStateService transitionStateService;
  private final DescriptorRepository descriptorRepository;

  public ScenarioSequenceService(BusinessEventService businessEventService, ScenarioService scenarioService,
                                 TransitionStateService transitionStateService, DescriptorRepository descriptorRepository) {
    this.businessEventService = businessEventService;
    this.scenarioService = scenarioService;
    this.transitionStateService = transitionStateService;
    this.descriptorRepository = descriptorRepository;
  }

  public Set<OmgScenarioSequence> findScenarioSequences() {
    Set<OmgScenarioSequence> result = new HashSet<>();

    return result;
  }
}
