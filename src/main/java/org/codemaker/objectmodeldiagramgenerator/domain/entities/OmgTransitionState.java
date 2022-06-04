package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof OmgTransitionState))
      return false;
    OmgTransitionState that = (OmgTransitionState) o;
    return getKey().equals(that.getKey()) && getDescription().equals(that.getDescription()) && Objects.equals(getPredecessor(),
            that.getPredecessor());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getKey(), getDescription(), getPredecessor());
  }

  @Override
  public String toString() {
    return "OmgTransitionState{" + "key='" + key + '\'' + ", description='" + description + '\'' + ", predecessor=" + predecessor + '}';
  }
}
