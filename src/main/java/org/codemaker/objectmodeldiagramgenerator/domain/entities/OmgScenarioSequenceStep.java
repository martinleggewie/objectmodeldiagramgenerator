package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class OmgScenarioSequenceStep {
  public enum Action {
    create,
    delete
  }

  private final OmgBusinessEvent businessEvent;
  private final Action action;
  private final Set<OmgObject> objects;

  public OmgScenarioSequenceStep(OmgBusinessEvent businessEvent, Action action) {
    this.businessEvent = businessEvent;
    this.action = action;
    this.objects = new HashSet<>();
  }

  public OmgBusinessEvent getBusinessEvent() {
    return businessEvent;
  }

  public Action getAction() {
    return action;
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
    return getBusinessEvent().equals(that.getBusinessEvent()) && getAction() == that.getAction() && getObjects().equals(that.getObjects());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getBusinessEvent(), getAction(), getObjects());
  }

  @Override
  public String toString() {
    return "OmgScenarioSequenceStep{" + "businessEvent=" + businessEvent + ", action=" + action + ", objects=" + objects + '}';
  }
}
