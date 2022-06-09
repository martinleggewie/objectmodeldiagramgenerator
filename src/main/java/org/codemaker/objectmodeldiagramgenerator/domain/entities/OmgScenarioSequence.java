package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OmgScenarioSequence {

  private final String title;
  private final OmgScenario scenario;
  private final OmgTransitionState transitionState;
  private final List<OmgScenarioSequenceStep> scenarioSequenceSteps;

  public OmgScenarioSequence(String title, OmgScenario scenario, OmgTransitionState transitionState) {
    this.title = title;
    this.scenario = scenario;
    this.transitionState = transitionState;
    this.scenarioSequenceSteps = new ArrayList<>();
  }

  public String getTitle() {
    return title;
  }

  public OmgScenario getScenario() {
    return scenario;
  }

  public OmgTransitionState getTransitionState() {
    return transitionState;
  }

  public List<OmgScenarioSequenceStep> getScenarioSequenceSteps() {
    return scenarioSequenceSteps;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof OmgScenarioSequence))
      return false;
    OmgScenarioSequence that = (OmgScenarioSequence) o;
    return getTitle().equals(that.getTitle()) && getScenario().equals(that.getScenario()) && getTransitionState().equals(
            that.getTransitionState()) && getScenarioSequenceSteps().equals(that.getScenarioSequenceSteps());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getTitle(), getScenario(), getTransitionState(), getScenarioSequenceSteps());
  }

  @Override
  public String toString() {
    return "OmgScenarioSequence{" + "title='" + title + '\'' + ", scenario=" + scenario + ", transitionState=" + transitionState + ", " + "scenarioSequenceSteps=" + scenarioSequenceSteps + '}';
  }
}
