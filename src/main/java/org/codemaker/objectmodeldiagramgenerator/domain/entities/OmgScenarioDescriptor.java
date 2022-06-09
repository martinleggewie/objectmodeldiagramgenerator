package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class OmgScenarioDescriptor {
  private final String key;
  private final String description;
  private final Set<String> predecessorKeys;

  public OmgScenarioDescriptor(String key, String description) {
    this.key = key;
    this.description = description;
    this.predecessorKeys = new HashSet<>();
  }

  public String getKey() {
    return key;
  }

  public String getDescription() {
    return description;
  }

  public Set<String> getPredecessorKeys() {
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
