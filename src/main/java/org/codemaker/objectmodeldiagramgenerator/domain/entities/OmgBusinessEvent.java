package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import java.util.Objects;

public class OmgBusinessEvent {
  private final String key;
  private final String description;
  private final String scenario;

  public OmgBusinessEvent(String key, String description, String scenario) {
    this.key = key;
    this.description = description;
    this.scenario = scenario;
  }

  public String getKey() {
    return key;
  }

  public String getDescription() {
    return description;
  }

  public String getScenario() {
    return scenario;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof OmgBusinessEvent))
      return false;
    OmgBusinessEvent that = (OmgBusinessEvent) o;
    return key.equals(that.key) && description.equals(that.description) && scenario.equals(that.scenario);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, description, scenario);
  }
}
