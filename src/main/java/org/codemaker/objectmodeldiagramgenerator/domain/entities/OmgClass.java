package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import java.util.Objects;

public class OmgClass {
  private final String key;
  private final String displayName;
  private final OmgDomain domain;

  public OmgClass(String key, String displayName, OmgDomain domain) {
    this.key = key;
    this.displayName = displayName;
    this.domain = domain;
  }

  public String getKey() {
    return key;
  }

  public String getDisplayName() {
    return displayName;
  }

  public OmgDomain getDomain() {
    return domain;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof OmgClass))
      return false;
    OmgClass omgClass = (OmgClass) o;
    return getKey().equals(omgClass.getKey()) && getDisplayName().equals(omgClass.getDisplayName()) && getDomain().equals(
            omgClass.getDomain());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getKey(), getDisplayName(), getDomain());
  }

  @Override
  public String toString() {
    return "OmgClass{" + "key='" + key + '\'' + ", displayName='" + displayName + '\'' + ", domain=" + domain + '}';
  }
}
