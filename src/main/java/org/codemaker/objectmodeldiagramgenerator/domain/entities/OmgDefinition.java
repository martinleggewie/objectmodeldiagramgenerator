package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import java.util.HashMap;
import java.util.Map;

public class OmgDefinition {
  private final Map<String, OmgBusinessEvent> businessEventMap = new HashMap<>();

  public Map<String, OmgBusinessEvent> getBusinessEventMap() {
    return businessEventMap;
  }
}
