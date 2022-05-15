package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import java.util.List;

public class OmgObjectModelSequence {
  private final String name;
  private final List<OmgObjectModel> objectModels;

  public OmgObjectModelSequence(String name, List<OmgObjectModel> objectModels) {
    this.name = name;
    this.objectModels = objectModels;
  }

  public String getName() {
    return name;
  }

  public List<OmgObjectModel> getObjectModels() {
    return objectModels;
  }
}
