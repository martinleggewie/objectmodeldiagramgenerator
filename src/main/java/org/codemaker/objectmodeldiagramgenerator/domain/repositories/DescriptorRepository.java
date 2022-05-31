package org.codemaker.objectmodeldiagramgenerator.domain.repositories;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEventDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObjectSequenceDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionStateDescriptor;

import java.util.Set;

public interface DescriptorRepository {

  public Set<OmgTransitionStateDescriptor> findTransitionStateDescriptors();

  public OmgTransitionStateDescriptor findTransitionStateDescriptor(String transitionStateDescriptorKey);

  public Set<OmgScenarioDescriptor> findScenarioDescriptors();

  public OmgScenarioDescriptor findScenarioDescriptor(String scenarioDescriptorKey);

  public Set<OmgBusinessEventDescriptor> findBusinessEventDescriptors();

  public OmgBusinessEventDescriptor findBusinessEventDescriptor(String businessDescriptorKey);

  public Set<OmgObjectSequenceDescriptor> findObjectSequenceDescriptors();
}
