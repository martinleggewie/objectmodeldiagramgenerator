package org.codemaker.objectmodeldiagramgenerator.domain.repositories;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEventDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioSequenceDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionStateDescriptor;

import java.util.Set;

public interface DescriptorRepository {

  /**
   * Defines a property value which is not set. Can be used to explicitly indicate that a String value is actually - well - not set. Using
   * this special value is better than setting an unset value to "" or null.
   */
  String PROPERTYVALUE_NOTSET = "NA";

  Set<OmgTransitionStateDescriptor> findTransitionStateDescriptors();

  Set<OmgScenarioDescriptor> findScenarioDescriptors();

  Set<OmgBusinessEventDescriptor> findBusinessEventDescriptors();

  Set<OmgScenarioSequenceDescriptor> findScenarioSequenceDescriptors();
}
