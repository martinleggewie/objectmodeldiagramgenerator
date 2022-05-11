package org.codemaker.objectmodeldiagramgenerator.domain.entities;

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
}
