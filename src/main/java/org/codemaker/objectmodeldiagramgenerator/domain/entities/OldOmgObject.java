package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import java.util.Map;
import java.util.Set;

public class OldOmgObject {
  private final String key;
  private final OmgClass clazz;
  private final Map<String, String> propertyMap;
  private final Set<OldOmgObject> dependeeObjects;

  public OldOmgObject(String key, OmgClass clazz, Map<String, String> propertyMap, Set<OldOmgObject> dependeeObjects) {
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

  public Set<OldOmgObject> getDependeeObjects() {
    return dependeeObjects;
  }

  public String displayName() {
    return key;
  }
}
