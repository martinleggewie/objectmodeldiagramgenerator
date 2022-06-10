package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class OmgScenarioSequenceStepDescriptor {

  private final String businessEventDescriptorKey;
  private final String actionKey;
  private final Set<OmgObjectDescriptor> objectDescriptors;

  public OmgScenarioSequenceStepDescriptor(String businessEventDescriptorKey, String actionKey) {
    this.businessEventDescriptorKey = businessEventDescriptorKey;
    this.actionKey = actionKey;
    this.objectDescriptors = new HashSet<>();
  }

  public String getBusinessEventDescriptorKey() {
    return businessEventDescriptorKey;
  }

  private String getActionKey() {
    // This getter is private because the property actionKey is only supposed inside this class. This getter method only exists so that
    // implementations of hashCode and equals can make use of a method and thus be consistent with how the other properties are being
    // accessed.
    return actionKey;
  }

  public OmgAction action() {
    return OmgAction.valueOf(getActionKey());
  }

  public Set<OmgObjectDescriptor> getObjectDescriptors() {
    return objectDescriptors;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof OmgScenarioSequenceStepDescriptor))
      return false;
    OmgScenarioSequenceStepDescriptor that = (OmgScenarioSequenceStepDescriptor) o;
    return getBusinessEventDescriptorKey().equals(that.getBusinessEventDescriptorKey()) && getActionKey().equals(
            that.getActionKey()) && getObjectDescriptors().equals(that.getObjectDescriptors());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getBusinessEventDescriptorKey(), getActionKey(), getObjectDescriptors());
  }

  @Override
  public String toString() {
    return "OmgScenarioSequenceStepDescriptor{" + "businessEventDescriptorKey='" + businessEventDescriptorKey + '\'' + ", actionKey='" + actionKey + '\'' + ", objectDescriptors=" + objectDescriptors + '}';
  }

}
