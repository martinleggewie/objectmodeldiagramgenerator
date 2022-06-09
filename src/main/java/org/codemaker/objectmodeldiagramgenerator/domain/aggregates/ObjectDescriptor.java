package org.codemaker.objectmodeldiagramgenerator.domain.aggregates;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OldOmgObject;

public class ObjectDescriptor {
  public enum LifecycleState {
    created,
    removed
  }

  private final OldOmgObject object;
  private final LifecycleState lifecycleState;

  public ObjectDescriptor(OldOmgObject object, LifecycleState lifecycleState) {
    this.object = object;
    this.lifecycleState = lifecycleState;
  }

  public OldOmgObject getObject() {
    return object;
  }

  public LifecycleState getLifecycleState() {
    return lifecycleState;
  }

  public String key() {
    return getObject().getKey();
  }
}
