package org.codemaker.objectmodeldiagramgenerator.domain.aggregates;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObject;

public class ObjectDescriptor {
  public static enum LifecycleState {
    created, removed
  }

  private final OmgObject object;
  private final LifecycleState lifecycleState;

  public ObjectDescriptor(OmgObject object, LifecycleState lifecycleState) {
    this.object = object;
    this.lifecycleState = lifecycleState;
  }

  public OmgObject getObject() {
    return object;
  }

  public LifecycleState getLifecycleState() {
    return lifecycleState;
  }

  public String key() {
    return getObject().getKey();
  }
}
