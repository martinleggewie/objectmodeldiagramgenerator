package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionState;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionStateDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.repositories.DescriptorRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.codemaker.objectmodeldiagramgenerator.domain.repositories.DescriptorRepository.PROPERTYVALUE_NOTSET;

public class TransitionStateService {

  private final DescriptorRepository descriptorRepository;

  public TransitionStateService(DescriptorRepository descriptorRepository) {
    this.descriptorRepository = descriptorRepository;
  }

  public Map<String, OmgTransitionState> findTransitionStateMap() {
    List<OmgTransitionStateDescriptor> transitionStateDescriptors = new ArrayList<>(descriptorRepository.findTransitionStateDescriptors());
    return transitionStateMap(transitionStateDescriptors, new HashMap<>());
  }

  /**
   * Beware - recursion! As we don't know the order in which we need to create the transition states because of the predecessors, we do a
   * recursive but still linear search over all the transition state descriptors. Each time when we either find a descriptor without a
   * predecessor or with one which we had already found before we remove the descriptor and add a new transition state to the result.
   */
  private Map<String, OmgTransitionState> transitionStateMap(List<OmgTransitionStateDescriptor> transitionStateDescriptors,
                                                             Map<String, OmgTransitionState> result) {
    if (transitionStateDescriptors.size() == 0) {
      return result;
    } else {
      OmgTransitionStateDescriptor transitionStateDescriptor = transitionStateDescriptors.get(0);
      if (transitionStateDescriptor.getPredecessorKey().equals(PROPERTYVALUE_NOTSET)) {
        OmgTransitionState transitionState = new OmgTransitionState(transitionStateDescriptor.getKey(),
                transitionStateDescriptor.getDescription(), null);
        result.put(transitionState.getKey(), transitionState);
        transitionStateDescriptors.remove(transitionStateDescriptor);
      } else {
        if (result.containsKey(transitionStateDescriptor.getPredecessorKey())) {
          OmgTransitionState predecessorTransitionState = result.get(transitionStateDescriptor.getPredecessorKey());
          OmgTransitionState transitionState = new OmgTransitionState(transitionStateDescriptor.getKey(),
                  transitionStateDescriptor.getDescription(), predecessorTransitionState);
          result.put(transitionState.getKey(), transitionState);
          transitionStateDescriptors.remove(transitionStateDescriptor);
        } else {
          transitionStateDescriptors.remove(transitionStateDescriptor);
          transitionStateDescriptors.add(transitionStateDescriptor);
        }
      }
      return transitionStateMap(transitionStateDescriptors, result);
    }
  }
}
