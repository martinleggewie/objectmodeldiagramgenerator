package org.codemaker.objectmodeldiagramgenerator.domain.aggregates;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObjectModel;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenario;

import java.util.List;

public class ScenarioDescriptor {
  private final OmgScenario scenario;
  private final List<OmgObjectModel> objectModels;

  public ScenarioDescriptor(OmgScenario scenario, List<OmgObjectModel> objectModels) {
    this.scenario = scenario;
    this.objectModels = objectModels;
  }

  public OmgScenario getScenario() {
    return scenario;
  }

  public List<OmgObjectModel> getObjectModels() {
    return objectModels;
  }

  public String key() {
    return getScenario().getKey();
  }

}
