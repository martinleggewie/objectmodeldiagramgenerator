package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import org.codemaker.objectmodeldiagramgenerator.domain.valueobjects.IndexOutOfMaxIndex;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class OmgScenarioSequenceStep {

  private final OmgBusinessEvent businessEvent;
  private final IndexOutOfMaxIndex scenarioStepIndex;
  private final IndexOutOfMaxIndex businessEventStepIndex;
  private final Set<OmgObject> objects;

  public OmgScenarioSequenceStep(OmgBusinessEvent businessEvent, IndexOutOfMaxIndex scenarioStepIndex,
                                 IndexOutOfMaxIndex businessEventStepIndex) {
    this.businessEvent = businessEvent;
    this.scenarioStepIndex = scenarioStepIndex;
    this.businessEventStepIndex = businessEventStepIndex;
    this.objects = new HashSet<>();
  }

  public OmgBusinessEvent getBusinessEvent() {
    return businessEvent;
  }

  public IndexOutOfMaxIndex getScenarioStepIndex() {
    return scenarioStepIndex;
  }

  public IndexOutOfMaxIndex getBusinessEventStepIndex() {
    return businessEventStepIndex;
  }

  public Set<OmgObject> getObjects() {
    return objects;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof OmgScenarioSequenceStep))
      return false;
    OmgScenarioSequenceStep that = (OmgScenarioSequenceStep) o;
    return getBusinessEvent().equals(that.getBusinessEvent()) && getScenarioStepIndex().equals(
            that.getScenarioStepIndex()) && getBusinessEventStepIndex().equals(that.getBusinessEventStepIndex()) && getObjects().equals(
            that.getObjects());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getBusinessEvent(), getScenarioStepIndex(), getBusinessEventStepIndex(), getObjects());
  }

  @Override
  public String toString() {
    return "OmgScenarioSequenceStep{" + "businessEvent=" + businessEvent + ", scenarioStepIndex=" + scenarioStepIndex + ", " +
            "businessEventStepIndex=" + businessEventStepIndex + ", objects=" + objects + '}';
  }
}
