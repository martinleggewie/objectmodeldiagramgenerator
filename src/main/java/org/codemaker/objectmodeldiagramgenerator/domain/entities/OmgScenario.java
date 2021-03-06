package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import org.apache.commons.collections4.CollectionUtils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class OmgScenario {

  public static final OmgScenario IRRELEVANT = new OmgScenario("SCENARIO_IRRELEVANT", "(The scenario is irrelevant");

  private final String key;
  private final String description;
  private final Set<OmgScenario> predecessors;

  public OmgScenario(String key, String description) {
    this.key = key;
    this.description = description;
    this.predecessors = new HashSet<>();
  }

  public String getKey() {
    return key;
  }

  public String getDescription() {
    return description;
  }

  public Set<OmgScenario> getPredecessors() {
    return predecessors;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof OmgScenario))
      return false;
    OmgScenario scenario = (OmgScenario) o;

    return getKey().equals(scenario.getKey()) && getDescription().equals(scenario.getDescription()) && CollectionUtils.isEqualCollection(
            getPredecessors(), scenario.getPredecessors());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getKey(), getDescription(), getPredecessors());
  }

  @Override
  public String toString() {
    return "OmgScenario{" + "key='" + key + '\'' + ", description='" + description + '\'' + ", predecessors=" + predecessors + '}';
  }
}
