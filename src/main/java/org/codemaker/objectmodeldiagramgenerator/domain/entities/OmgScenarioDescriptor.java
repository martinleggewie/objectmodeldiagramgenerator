package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import java.util.List;
import java.util.Objects;

public class OmgScenarioDescriptor {
  private final String key;
  private final String description;
  private final List<String> predecessorKeys;

  public OmgScenarioDescriptor(String key, String description, List<String> predecessorKeys) {
    this.key = key;
    this.description = description;
    this.predecessorKeys = predecessorKeys;
  }

  public String getKey() {
    return key;
  }

  public String getDescription() {
    return description;
  }

  public List<String> getPredecessorKeys() {
    return predecessorKeys;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof OmgScenarioDescriptor))
      return false;
    OmgScenarioDescriptor that = (OmgScenarioDescriptor) o;
    return getKey().equals(that.getKey()) && getDescription().equals(that.getDescription()) && getPredecessorKeys().equals(
            that.getPredecessorKeys());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getKey(), getDescription(), getPredecessorKeys());
  }

  @Override
  public String toString() {
    return "OmgScenarioDescriptor{" + "key='" + key + '\'' + ", description='" + description + '\'' + ", predecessorKeys=" + predecessorKeys + '}';
  }
}
