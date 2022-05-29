package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import java.util.Objects;

public class OmgBusinessEventDescriptor {
  private final String key;
  private final String description;
  private final String scenarioKey;

  public OmgBusinessEventDescriptor(String key, String description, String scenarioKey) {
    this.key = key;
    this.description = description;
    this.scenarioKey = scenarioKey;
  }

  public String getKey() {
    return key;
  }

  public String getDescription() {
    return description;
  }

  public String getScenarioKey() {
    return scenarioKey;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof OmgBusinessEventDescriptor))
      return false;
    OmgBusinessEventDescriptor that = (OmgBusinessEventDescriptor) o;
    return getKey().equals(that.getKey()) && getDescription().equals(that.getDescription()) && getScenarioKey().equals(
            that.getScenarioKey());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getKey(), getDescription(), getScenarioKey());
  }

  @Override
  public String toString() {
    return "OmgBusinessEventDescriptor{" + "key='" + key + '\'' + ", description='" + description + '\'' + ", scenarioKey='" + scenarioKey + '\'' + '}';
  }
}
