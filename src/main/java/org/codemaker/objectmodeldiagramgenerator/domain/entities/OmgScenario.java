package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import java.util.List;

public class OmgScenario {
  private final String key;
  private final String description;
  private final List<OmgScenario> predecessors;

  public OmgScenario(String key, String description, List<OmgScenario> predecessors) {
    this.key = key;
    this.description = description;
    this.predecessors = predecessors;
  }

  public String getKey() {
    return key;
  }

  public String getDescription() {
    return description;
  }

  public List<OmgScenario> getPredecessors() {
    return predecessors;
  }
}
