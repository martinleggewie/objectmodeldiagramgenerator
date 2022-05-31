package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import java.util.Objects;

public class OmgDomainDescriptor {
  private final String key;
  private final String displayName;

  public OmgDomainDescriptor(String key, String displayName) {
    this.key = key;
    this.displayName = displayName;
  }

  public String getKey() {
    return key;
  }

  public String getDisplayName() {
    return displayName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof OmgDomainDescriptor))
      return false;
    OmgDomainDescriptor that = (OmgDomainDescriptor) o;
    return getKey().equals(that.getKey()) && getDisplayName().equals(that.getDisplayName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getKey(), getDisplayName());
  }

  @Override
  public String toString() {
    return "OmgDomainDescriptor{" + "key='" + key + '\'' + ", displayName='" + displayName + '\'' + '}';
  }
}
