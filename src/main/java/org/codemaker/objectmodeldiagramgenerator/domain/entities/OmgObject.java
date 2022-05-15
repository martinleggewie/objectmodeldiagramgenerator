package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import java.util.Map;
import java.util.Set;

public class OmgObject {
  private final String key;
  private final OmgClass clazz;
  private final Map<String, String> propertyMap;
  private final Set<OmgObject> dependeeObjects;

  public OmgObject(String key, OmgClass clazz, Map<String, String> propertyMap, Set<OmgObject> dependeeObjects) {
    this.key = key;
    this.clazz = clazz;
    this.propertyMap = propertyMap;
    this.dependeeObjects = dependeeObjects;
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
