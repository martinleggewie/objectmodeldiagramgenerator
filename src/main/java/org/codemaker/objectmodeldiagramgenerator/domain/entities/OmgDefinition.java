package org.codemaker.objectmodeldiagramgenerator.domain.entities;

import java.util.List;
import java.util.Map;

public class OmgDefinition {
  private final Map<String, OmgBusinessEvent> businessEventMap;
  private final List<OmgObjectModelSequence> objectModelSequences;

  public OmgDefinition(Map<String, OmgBusinessEvent> businessEventMap, List<OmgObjectModelSequence> objectModelSequences) {
    this.businessEventMap = businessEventMap;
    this.objectModelSequences = objectModelSequences;
  }

  public Map<String, OmgBusinessEvent> getBusinessEventMap() {
    return businessEventMap;
  }

  public List<OmgObjectModelSequence> getObjectModelSequences() {
    return objectModelSequences;
  }
}
