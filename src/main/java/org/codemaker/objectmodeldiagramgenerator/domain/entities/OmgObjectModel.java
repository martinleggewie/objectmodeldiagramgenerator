package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import java.util.Set;

public class OmgObjectModel {
  private final OmgBusinessEvent businessEvent;
  private final Set<OmgObject> objects;

  public OmgObjectModel(OmgBusinessEvent businessEvent, Set<OmgObject> objects) {
    this.businessEvent = businessEvent;
    this.objects = objects;
  }

  public OmgBusinessEvent getBusinessEvent() {
    return businessEvent;
  }

  public Set<OmgObject> getObjects() {
    return objects;
  }
}
