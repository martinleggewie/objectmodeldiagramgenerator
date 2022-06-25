package org.codemaker.objectmodeldiagramgenerator.testutil;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionState;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionStateDescriptor;

import java.util.HashMap;
import java.util.Map;

import static org.codemaker.objectmodeldiagramgenerator.domain.repositories.DescriptorRepository.PROPERTYVALUE_NOTSET;

public class TransitionStateTestDataCreator {
  private static final String DESCRIPTION1 = "The first transition state. Let's add some more text to force line breaks in diagrams.";
  private static final String DESCRIPTION2 = "The second transition state. Here some more text.";
  private static final String DESCRIPTION3 = "The third transition state. And here is some another text.";

  public static Map<String, OmgTransitionStateDescriptor> createTransitionStateDescriptorMap() {
    OmgTransitionStateDescriptor descriptor1 = new OmgTransitionStateDescriptor("state1", DESCRIPTION1, PROPERTYVALUE_NOTSET);
    OmgTransitionStateDescriptor descriptor2 = new OmgTransitionStateDescriptor("state2", DESCRIPTION2, "state1");
    OmgTransitionStateDescriptor descriptor3 = new OmgTransitionStateDescriptor("state3", DESCRIPTION3, "state1");
    Map<String, OmgTransitionStateDescriptor> transitionStateDescriptorMap = new HashMap<>();
    transitionStateDescriptorMap.put(descriptor1.getKey(), descriptor1);
    transitionStateDescriptorMap.put(descriptor2.getKey(), descriptor2);
    transitionStateDescriptorMap.put(descriptor3.getKey(), descriptor3);
    return transitionStateDescriptorMap;
  }

  public static Map<String, OmgTransitionState> createTransitionStateMap() {
    OmgTransitionState transitionState1 = new OmgTransitionState("state1", DESCRIPTION1, null);
    OmgTransitionState transitionState2 = new OmgTransitionState("state2", DESCRIPTION2, transitionState1);
    OmgTransitionState transitionState3 = new OmgTransitionState("state3", DESCRIPTION3, transitionState1);

    Map<String, OmgTransitionState> result = new HashMap<>();
    result.put(transitionState1.getKey(), transitionState1);
    result.put(transitionState2.getKey(), transitionState2);
    result.put(transitionState3.getKey(), transitionState3);
    return result;
  }
}




