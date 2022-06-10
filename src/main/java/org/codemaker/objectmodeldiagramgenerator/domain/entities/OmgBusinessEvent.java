package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import java.util.Objects;

public class OmgBusinessEvent {

  public static final OmgBusinessEvent INITIAL = new OmgBusinessEvent("EVENT_INITIAL", "Initial", OmgScenario.IRRELEVANT);
  public static final OmgBusinessEvent FINAL = new OmgBusinessEvent("EVENT_FINAL", "Final", OmgScenario.IRRELEVANT);

  private final String key;
  private final String description;
  private final OmgScenario scenario;

  public OmgBusinessEvent(String key, String description, OmgScenario scenario) {
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

  public OmgScenario getScenario() {
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

  @Override
  public String toString() {
    return "OmgBusinessEvent{" + "key='" + key + '\'' + ", description='" + description + '\'' + ", scenario=" + scenario + '}';
  }
}
