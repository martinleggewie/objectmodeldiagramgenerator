package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import java.util.Objects;

public class OmgDomain {
  private final String key;
  private final String displayName;

  public OmgDomain(String key, String displayName) {
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
    if (!(o instanceof OmgDomain))
      return false;
    OmgDomain domain = (OmgDomain) o;
    return getKey().equals(domain.getKey()) && getDisplayName().equals(domain.getDisplayName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getKey(), getDisplayName());
  }

  @Override
  public String toString() {
    return "OmgDomain{" + "key='" + key + '\'' + ", displayName='" + displayName + '\'' + '}';
  }
}
