package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import org.apache.commons.collections4.CollectionUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class OmgObject {

  private final String key;
  private final OmgClass clazz;
  private final OmgAction action;
  private final OmgEra era;
  private final OmgOrigin origin;
  private final Map<String, String> propertyMap;
  private final Set<OmgObject> dependeeObjects;

  public OmgObject(String key, OmgClass clazz, OmgAction action, OmgEra era, OmgOrigin origin) {
    this.key = key;
    this.clazz = clazz;
    this.action = action;
    this.era = era;
    this.origin = origin;
    this.propertyMap = new HashMap<>();
    this.dependeeObjects = new HashSet<>();
  }

  public String getKey() {
    return key;
  }

  public OmgClass getClazz() {
    return clazz;
  }

  public OmgAction getAction() {
    return action;
  }

  public OmgEra getEra() {
    return era;
  }

  public OmgOrigin getOrigin() {
    return origin;
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
    return getKey().equals(object.getKey()) && getClazz().equals(
            object.getClazz()) && getAction() == object.getAction() && getEra() == object.getEra() && getOrigin() == object.getOrigin() && getPropertyMap().equals(
            object.getPropertyMap()) && CollectionUtils.isEqualCollection(getDependeeObjects(), object.getDependeeObjects());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getKey(), getClazz(), getAction(), getEra(), getOrigin(), getPropertyMap(), getDependeeObjects());
  }

  @Override
  public String toString() {
    return "OmgObject{" + "key='" + key + '\'' + ", clazz=" + clazz + ", action=" + action + ", era=" + era + ", origin=" + origin + ", " + "propertyMap=" + propertyMap + ", dependeeObjects=" + dependeeObjects + '}';
  }
}
