package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import java.util.HashMap;
import java.util.Map;

public class OmgObject {
  private final String key;
  private final OmgClass clazz;
  private final Map<String, String> propertyMap;

  public OmgObject(String key, OmgClass clazz, Map<String, String> propertyMap) {
    this.key = key;
    this.clazz = clazz;
    this.propertyMap = propertyMap;
  }

  public String getKey() {
    return key;
  }

  public OmgClass getClazz() {
    return clazz;
  }

  public Map<String, String> getPropertyMap() {
    return propertyMap;
  }

  public String displayName() {
    return key;
  }
}
