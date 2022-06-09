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
  private static final String DESCRIPTION0 = "This is no transition state.";

  public static OmgTransitionStateDescriptor createDescriptor(String transitionStateDescriptorKey) {
    OmgTransitionStateDescriptor descriptor1 = new OmgTransitionStateDescriptor("state1", DESCRIPTION1, PROPERTYVALUE_NOTSET);
    OmgTransitionStateDescriptor descriptor2 = new OmgTransitionStateDescriptor("state2", DESCRIPTION2, "state1");
    OmgTransitionStateDescriptor descriptor3 = new OmgTransitionStateDescriptor("state3", DESCRIPTION3, "state1");
    OmgTransitionStateDescriptor descriptor0 = new OmgTransitionStateDescriptor("state0", DESCRIPTION0, "state1");
    Map<String, OmgTransitionStateDescriptor> transitionStateDescriptorMap = new HashMap<>();
    transitionStateDescriptorMap.put(descriptor1.getKey(), descriptor1);
    transitionStateDescriptorMap.put(descriptor2.getKey(), descriptor2);
    transitionStateDescriptorMap.put(descriptor3.getKey(), descriptor3);
    transitionStateDescriptorMap.put(descriptor0.getKey(), descriptor0);
    return transitionStateDescriptorMap.get(transitionStateDescriptorKey);
  }

  public static OmgTransitionState create(String transitionStateKey) {
    OmgTransitionState transitionState1 = new OmgTransitionState("state1", DESCRIPTION1, null);
    OmgTransitionState transitionState2 = new OmgTransitionState("state2", DESCRIPTION2, transitionState1);
    OmgTransitionState transitionState3 = new OmgTransitionState("state3", DESCRIPTION3, transitionState1);
    OmgTransitionState transitionState0 = new OmgTransitionState("state0", DESCRIPTION0, transitionState1);
    Map<String, OmgTransitionState> transitionStateMap = new HashMap<>();
    transitionStateMap.put(transitionState1.getKey(), transitionState1);
    transitionStateMap.put(transitionState2.getKey(), transitionState2);
    transitionStateMap.put(transitionState3.getKey(), transitionState3);
    transitionStateMap.put(transitionState0.getKey(), transitionState0);
    return transitionStateMap.get(transitionStateKey);
  }

  public static Map<String, OmgTransitionState> createTransitionStateMap() {
    OmgTransitionState transitionState1 = create("state1");
    OmgTransitionState transitionState2 = create("state2");
    OmgTransitionState transitionState3 = create("state3");

    Map<String, OmgTransitionState> result = new HashMap<>();
    result.put(transitionState1.getKey(), transitionState1);
    result.put(transitionState2.getKey(), transitionState2);
    result.put(transitionState3.getKey(), transitionState3);
    return result;
  }

}




