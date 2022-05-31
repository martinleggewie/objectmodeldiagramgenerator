package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import java.util.Objects;

public class OmgClassDescriptor {
  private final String key;
  private final String displayName;
  private final String domainKey;

  public OmgClassDescriptor(String key, String displayName, String domainKey) {
    this.key = key;
    this.displayName = displayName;
    this.domainKey = domainKey;
  }

  public String getKey() {
    return key;
  }

  public String getDisplayName() {
    return displayName;
  }

  public String getDomainKey() {
    return domainKey;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof OmgClassDescriptor))
      return false;
    OmgClassDescriptor that = (OmgClassDescriptor) o;
    return getKey().equals(that.getKey()) && getDisplayName().equals(that.getDisplayName()) && getDomainKey().equals(that.getDomainKey());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getKey(), getDisplayName(), getDomainKey());
  }

  @Override
  public String toString() {
    return "OmgClassDescriptor{" + "key='" + key + '\'' + ", displayName='" + displayName + '\'' + ", domainKey='" + domainKey + '\'' + '}';
  }
}
