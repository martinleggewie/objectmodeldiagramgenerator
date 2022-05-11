package org.codemaker.objectmodeldiagramgenerator.domain.entities;

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
}
