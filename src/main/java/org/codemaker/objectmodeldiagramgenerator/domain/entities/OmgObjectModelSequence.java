package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import java.util.List;

public class OmgObjectModelSequence {
  private final String name;
  private final OmgTransitionState transitionState;
  private final List<OmgObjectModel> objectModels;

  public OmgObjectModelSequence(String name, OmgTransitionState transitionState, List<OmgObjectModel> objectModels) {
    this.name = name;
    this.transitionState = transitionState;
    this.objectModels = objectModels;
  }

  public String getName() {
    return name;
  }

  public OmgTransitionState getTransitionState() {
    return transitionState;
  }

  public List<OmgObjectModel> getObjectModels() {
    return objectModels;
  }
}
