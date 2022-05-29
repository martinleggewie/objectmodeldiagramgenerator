package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.aggregates.ObjectDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgDefinition;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObjectModel;

public class ObjectFinderService {

  private final OmgDefinition definition;

  public ObjectFinderService(OmgDefinition definition) {
    this.definition = definition;
  }

  // Finds a description of the object for the point in time defined by the object model
  public ObjectDescriptor findObjectDescriptor(String objectKey, OmgObjectModel objectModel) {
    // Find all additional information about the object model: transition state and scenario
    // For transition state: Find the object model sequence to which the object model belongs to
    // For scenario: just hop from object model to business event and from there to the scenario




    return null;
  }
}
