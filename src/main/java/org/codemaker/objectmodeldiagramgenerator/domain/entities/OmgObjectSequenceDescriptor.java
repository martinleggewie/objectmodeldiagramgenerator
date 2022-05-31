package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class OmgObjectSequenceDescriptor {

  private final String transitionStateDescriptorKey;
  private final String title;
  private final Set<OmgDomainDescriptor> domainDescriptors;
  private final Set<OmgClassDescriptor> classDescriptors;
  private final List<OmgObjectSequenceStepDescriptor> objectSequenceStepDescriptors;

  public OmgObjectSequenceDescriptor(String transitionStateDescriptorKey, String title) {
    this.transitionStateDescriptorKey = transitionStateDescriptorKey;
    this.title = title;
    this.domainDescriptors = new HashSet<>();
    this.classDescriptors = new HashSet<>();
    this.objectSequenceStepDescriptors = new ArrayList<>();
  }

  public String getTransitionStateDescriptorKey() {
    return transitionStateDescriptorKey;
  }

  public String getTitle() {
    return title;
  }

  public Set<OmgDomainDescriptor> getDomainDescriptors() {
    return domainDescriptors;
  }

  public Set<OmgClassDescriptor> getClassDescriptors() {
    return classDescriptors;
  }

  public List<OmgObjectSequenceStepDescriptor> getObjectSequenceStepDescriptors() {
    return objectSequenceStepDescriptors;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof OmgObjectSequenceDescriptor))
      return false;
    OmgObjectSequenceDescriptor that = (OmgObjectSequenceDescriptor) o;
    return getTransitionStateDescriptorKey().equals(that.getTransitionStateDescriptorKey()) && getTitle().equals(
            that.getTitle()) && getDomainDescriptors().equals(that.getDomainDescriptors()) && getClassDescriptors().equals(
            that.getClassDescriptors()) && getObjectSequenceStepDescriptors().equals(that.getObjectSequenceStepDescriptors());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getTransitionStateDescriptorKey(), getTitle(), getDomainDescriptors(), getClassDescriptors(),
            getObjectSequenceStepDescriptors());
  }

  @Override
  public String toString() {
    return "OmgObjectSequenceDescriptor{" + "transitionStateDescriptorKey='" + transitionStateDescriptorKey + '\'' + ", title='" + title + '\'' + ", domainDescriptors=" + domainDescriptors + ", classDescriptors=" + classDescriptors + ", objectSequenceStepDescriptors=" + objectSequenceStepDescriptors + '}';
  }
}
