package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import org.apache.commons.collections4.CollectionUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class OmgObject {
  public enum Phase {
    pastCreate,    // object currently exists in the current sequence step, and will also exist in the future
    pastDelete,    // object currently does not exist anymore in the current sequence step, but it did in a past step
    currentCreate, // object is being created in the current step
    currentDelete, // object is being deleted in the current step
    futureCreate,  // object does not exist in the current step, but will be created in the future
    futureDelete,  // object exists in the current step, but it will be deleted in the future
    outside        // object exists outside the current scenario, and therefore we don't care if it will be deleted in the future
  }

  private final String key;
  private final Phase phase;
  private final OmgClass clazz;
  private final Map<String, String> propertyMap;
  private final Set<OmgObject> dependeeObjects;

  public OmgObject(String key, Phase phase, OmgClass clazz) {
    this.key = key;
    this.phase = phase;
    this.clazz = clazz;
    this.propertyMap = new HashMap<>();
    this.dependeeObjects = new HashSet<>();
  }

  public String getKey() {
    return key;
  }

  public Phase getPhase() {
    return phase;
  }

  public OmgClass getClazz() {
    return clazz;
  }

  public Map<String, String> getPropertyMap() {
    return propertyMap;
  }

  public Set<OmgObject> getDependeeObjects() {
    return dependeeObjects;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof OmgObject))
      return false;
    OmgObject object = (OmgObject) o;
    return getKey().equals(object.getKey()) && getPhase() == object.getPhase() && getClazz().equals(
            object.getClazz()) && getPropertyMap().equals(object.getPropertyMap()) && CollectionUtils.isEqualCollection(
            getDependeeObjects(), object.getDependeeObjects());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getKey(), getPhase(), getClazz(), getPropertyMap(), getDependeeObjects());
  }

  @Override
  public String toString() {
    return "OmgObject{" + "key='" + key + '\'' + ", phase=" + phase + ", clazz=" + clazz + ", propertyMap=" + propertyMap + ", " +
            "dependeeObjects=" + dependeeObjects + '}';
  }
}
