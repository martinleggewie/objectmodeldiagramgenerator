package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import java.util.Objects;

public class OmgTransitionStateDescriptor {
  private final String key;
  private final String description;
  private final String predecessorKey;

  public OmgTransitionStateDescriptor(String key, String description, String predecessorKey) {
    this.key = key;
    this.description = description;
    this.predecessorKey = predecessorKey;
  }

  public String getKey() {
    return key;
  }

  public String getDescription() {
    return description;
  }

  public String getPredecessorKey() {
    return predecessorKey;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof OmgTransitionStateDescriptor))
      return false;
    OmgTransitionStateDescriptor that = (OmgTransitionStateDescriptor) o;
    return getKey().equals(that.getKey()) && getDescription().equals(that.getDescription()) && Objects.equals(getPredecessorKey(),
            that.getPredecessorKey());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getKey(), getDescription(), getPredecessorKey());
  }

  @Override
  public String toString() {
    return "OmgTransitionStateDescriptor{" + "key='" + key + '\'' + ", description='" + description + '\'' + ", predecessorKey='" + predecessorKey + '\'' + '}';
  }
}
