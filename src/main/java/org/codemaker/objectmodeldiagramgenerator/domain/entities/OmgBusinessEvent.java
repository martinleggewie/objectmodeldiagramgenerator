package org.codemaker.objectmodeldiagramgenerator.domain.entities;

public class OmgBusinessEvent {
  private String key;
  private String description;
  private String scenario;

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
}
