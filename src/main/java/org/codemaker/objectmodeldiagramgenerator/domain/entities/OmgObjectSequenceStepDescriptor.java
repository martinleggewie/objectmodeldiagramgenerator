package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class OmgObjectSequenceStepDescriptor {
  private final String businessEventDescriptorKey;
  private final String actionKey;
  private final Set<OmgObjectDescriptor> objectDescriptors;

  public OmgObjectSequenceStepDescriptor(String businessEventDescriptorKey, String actionKey) {
    this.businessEventDescriptorKey = businessEventDescriptorKey;
    this.actionKey = actionKey;
    this.objectDescriptors = new HashSet<>();
  }

  public String getBusinessEventDescriptorKey() {
    return businessEventDescriptorKey;
  }

  public String getActionKey() {
    return actionKey;
  }

  public Set<OmgObjectDescriptor> getObjectDescriptors() {
    return objectDescriptors;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof OmgObjectSequenceStepDescriptor))
      return false;
    OmgObjectSequenceStepDescriptor that = (OmgObjectSequenceStepDescriptor) o;
    return getBusinessEventDescriptorKey().equals(that.getBusinessEventDescriptorKey()) && getActionKey().equals(
            that.getActionKey()) && getObjectDescriptors().equals(that.getObjectDescriptors());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getBusinessEventDescriptorKey(), getActionKey(), getObjectDescriptors());
  }

  @Override
  public String toString() {
    return "OmgObjectSequenceStepDescriptor{" + "businessEventDescriptorKey='" + businessEventDescriptorKey + '\'' + ", actionKey='" + actionKey + '\'' + ", objectDescriptors=" + objectDescriptors + '}';
  }
}
