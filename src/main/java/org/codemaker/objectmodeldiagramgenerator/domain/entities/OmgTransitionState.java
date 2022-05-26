package org.codemaker.objectmodeldiagramgenerator.domain.entities;

public class OmgTransitionState {
  private final String key;
  private final String description;
  private final OmgTransitionState predecessor;

  public OmgTransitionState(String key, String description, OmgTransitionState predecessor) {
    this.key = key;
    this.description = description;
    this.predecessor = predecessor;
  }

  public String getKey() {
    return key;
  }

  public String getDescription() {
    return description;
  }

  public OmgTransitionState getPredecessor() {
    return predecessor;
  }
}
