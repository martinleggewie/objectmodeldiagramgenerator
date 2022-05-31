package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class OmgObjectDescriptor {
  private final String domainKey;
  private final String classKey;
  private final String key;
  private final Set<String> dependeeKeys;
  private final Map<String, String> propertyMap;

  public OmgObjectDescriptor(String domainKey, String classKey, String key) {
    this.domainKey = domainKey;
    this.classKey = classKey;
    this.key = key;

    dependeeKeys = new HashSet<>();
    propertyMap = new HashMap<>();
  }

  public String getDomainKey() {
    return domainKey;
  }

  public String getClassKey() {
    return classKey;
  }

  public String getKey() {
    return key;
  }

  public Set<String> getDependeeKeys() {
    return dependeeKeys;
  }

  public Map<String, String> getPropertyMap() {
    return propertyMap;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof OmgObjectDescriptor))
      return false;
    OmgObjectDescriptor that = (OmgObjectDescriptor) o;
    return getDomainKey().equals(that.getDomainKey()) && getClassKey().equals(that.getClassKey()) && getKey().equals(
            that.getKey()) && getDependeeKeys().equals(that.getDependeeKeys()) && getPropertyMap().equals(that.getPropertyMap());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getDomainKey(), getClassKey(), getKey(), getDependeeKeys(), getPropertyMap());
  }

  @Override
  public String toString() {
    return "OmgObjectDescriptor{" + "domainKey='" + domainKey + '\'' + ", classKey='" + classKey + '\'' + ", key='" + key + '\'' + ", " + "dependeeKeys=" + dependeeKeys + ", propertyMap=" + propertyMap + '}';
  }
}
