package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import java.util.Set;

public class OmgObjectModel {

  public static enum Action {
    create,
    delete
  }

  private final OmgBusinessEvent businessEvent;
  private final Set<OmgObject> objects;
  private final Action action;

  public OmgObjectModel(OmgBusinessEvent businessEvent, Set<OmgObject> objects, Action action) {
    this.businessEvent = businessEvent;
    this.objects = objects;
    this.action = action;
  }

  public OmgBusinessEvent getBusinessEvent() {
    return businessEvent;
  }

  public Set<OmgObject> getObjects() {
    return objects;
  }

  public Action getAction() {
    return action;
  }
}
