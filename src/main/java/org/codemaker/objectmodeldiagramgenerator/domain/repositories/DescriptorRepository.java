package org.codemaker.objectmodeldiagramgenerator.domain.repositories;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEventDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionStateDescriptor;

import java.util.Set;

public interface DescriptorRepository {

  public Set<OmgTransitionStateDescriptor> findAllTransitionStateDescriptors();

  public OmgTransitionStateDescriptor findTransitionStateDescriptor(String transitionStateDescriptorKey);

  public Set<OmgScenarioDescriptor> findAllScenarioDescriptors();

  public OmgScenarioDescriptor findScenarioDescriptor(String scenarioDescriptorKey);

  public Set<OmgBusinessEventDescriptor> findAllBusinessEventDescriptors();

  public OmgBusinessEventDescriptor findBusinessEventDescriptor(String businessDescriptorKey);
}
