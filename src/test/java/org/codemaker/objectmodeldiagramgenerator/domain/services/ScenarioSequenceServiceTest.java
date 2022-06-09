package org.codemaker.objectmodeldiagramgenerator.domain.services;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEvent;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEventDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgClassDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgDomainDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObjectDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenario;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioSequence;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioSequenceDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioSequenceStepDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionState;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionStateDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.repositories.DescriptorRepository;
import org.codemaker.objectmodeldiagramgenerator.testutil.ScenarioTestDataCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class ScenarioSequenceServiceTest {

  private BusinessEventService businessEventService;
  private ScenarioService scenarioService;
  private TransitionStateService transitionStateService;
  private DescriptorRepository descriptorRepository;

  @BeforeEach
  void setUp() {
    // Set up the TransitionStateService
    transitionStateService = new TransitionStateService(null) {
      @Override
      public Map<String, OmgTransitionState> findTransitionStateMap() {
        Map<String, OmgTransitionState> result = new HashMap<>();
        OmgTransitionState state1 = new OmgTransitionState("state1", "This is transition state 1.", null);
        result.put(state1.getKey(), state1);
        return result;
      }
    };

    // Set up the ScenarioService
    scenarioService = new ScenarioService(null) {
      @Override
      public Map<String, OmgScenario> findScenarioMap() {
        return ScenarioTestDataCreator.createScenarioMap();
      }
    };

    // Set up the BusinessEventService, and directly make use of the just previously created ScenarioService
    businessEventService = new BusinessEventService(null, scenarioService) {
      @Override
      public Map<String, OmgBusinessEvent> findBusinessEventMap() {
        Map<String, OmgBusinessEvent> result = new HashMap<>();
        Map<String, OmgScenario> scenarioMap = scenarioService.findScenarioMap();
        OmgBusinessEvent event1 = new OmgBusinessEvent("event1", "This is business event 1.", scenarioMap.get("scenario1"));
        OmgBusinessEvent event2 = new OmgBusinessEvent("event2", "This is business event 2.", scenarioMap.get("scenario1"));
        OmgBusinessEvent event3 = new OmgBusinessEvent("event3", "This is business event 3.", scenarioMap.get("scenario1"));
        OmgBusinessEvent event4 = new OmgBusinessEvent("event4", "This is business event 4.", scenarioMap.get("scenario2"));
        result.put(event1.getKey(), event1);
        result.put(event2.getKey(), event2);
        result.put(event3.getKey(), event3);
        result.put(event4.getKey(), event4);
        return result;
      }
    };

    // Set up the ObjectSequenceDescriptor part of the DescriptorRepository
    descriptorRepository = new DescriptorRepository() {
      @Override
      public Set<OmgTransitionStateDescriptor> findTransitionStateDescriptors() {
        throw new UnsupportedOperationException();
      }

      @Override
      public Set<OmgScenarioDescriptor> findScenarioDescriptors() {
        throw new UnsupportedOperationException();
      }

      @Override
      public Set<OmgBusinessEventDescriptor> findBusinessEventDescriptors() {
        throw new UnsupportedOperationException();
      }

      @Override
      public Set<OmgScenarioSequenceDescriptor> findScenarioSequenceDescriptors() {
        Set<OmgScenarioSequenceDescriptor> result = new HashSet<>();

        // Prepare the domain descriptors
        OmgDomainDescriptor domainDescriptorD1 = new OmgDomainDescriptor("domain1", "My First Domain");
        OmgDomainDescriptor domainDescriptorD2 = new OmgDomainDescriptor("domain2", "My Second Domain");
        OmgDomainDescriptor domainDescriptorD3 = new OmgDomainDescriptor("domain3", "My Third Domain");

        // Prepare the class descriptors
        OmgClassDescriptor classDescriptorD1C1 = new OmgClassDescriptor("class1", "My First Class", "domain1");
        OmgClassDescriptor classDescriptorD1C2 = new OmgClassDescriptor("class2", "My Second Class", "domain1");
        OmgClassDescriptor classDescriptorD1C3 = new OmgClassDescriptor("class3", "My Third Class", "domain1");
        OmgClassDescriptor classDescriptorD2C1 = new OmgClassDescriptor("class1", "My First Class", "domain2");
        OmgClassDescriptor classDescriptorD2C2 = new OmgClassDescriptor("class2", "My Second Class", "domain2");
        OmgClassDescriptor classDescriptorD3C1 = new OmgClassDescriptor("class1", "My First Class", "domain3");

        // Prepare the object descriptors
        OmgObjectDescriptor objectDescriptorD1C1_1_create = new OmgObjectDescriptor("domain1", "class1", "d1c1-1");
        objectDescriptorD1C1_1_create.getPropertyMap().put("name1", "value001");
        objectDescriptorD1C1_1_create.getPropertyMap().put("name2", "value002");
        objectDescriptorD1C1_1_create.getPropertyMap().put("name3", "value003");
        OmgObjectDescriptor objectDescriptorD1C2_1_create = new OmgObjectDescriptor("domain1", "class2", "d1c2-1");
        objectDescriptorD1C2_1_create.getPropertyMap().put("name1", "value004");
        objectDescriptorD1C2_1_create.getPropertyMap().put("name2", "value005");
        OmgObjectDescriptor objectDescriptorD1C2_2_create = new OmgObjectDescriptor("domain1", "class2", "d1c2-2");
        objectDescriptorD1C2_2_create.getPropertyMap().put("name1", "value006");
        objectDescriptorD1C2_2_create.getPropertyMap().put("name2", "value007");
        OmgObjectDescriptor objectDescriptorD1C3_1_create = new OmgObjectDescriptor("domain1", "class3", "d1c3-1");
        objectDescriptorD1C3_1_create.getPropertyMap().put("name1", "value008");
        objectDescriptorD1C3_1_create.getPropertyMap().put("name2", "value009");
        objectDescriptorD1C3_1_create.getPropertyMap().put("d1c1_fk", "d1c1-1");
        objectDescriptorD1C3_1_create.getDependeeKeys().add("d1c1-1");
        OmgObjectDescriptor objectDescriptorD2C1_1_create = new OmgObjectDescriptor("domain2", "class1", "d2c1-1");
        objectDescriptorD2C1_1_create.getPropertyMap().put("name1", "value010");
        objectDescriptorD2C1_1_create.getPropertyMap().put("d1c2_fk", "d1c2-1");
        objectDescriptorD2C1_1_create.getPropertyMap().put("d1c3_fk", "d1c3-1");
        objectDescriptorD2C1_1_create.getDependeeKeys().add("d1c2-1");
        objectDescriptorD2C1_1_create.getDependeeKeys().add("d1c3-1");
        OmgObjectDescriptor objectDescriptorD2C2_1_create = new OmgObjectDescriptor("domain2", "class2", "d2c2-1");
        objectDescriptorD2C2_1_create.getPropertyMap().put("name1", "value011");
        objectDescriptorD2C2_1_create.getPropertyMap().put("name2", "value012");
        objectDescriptorD2C2_1_create.getPropertyMap().put("d1c1_fk", "d1c1-1");
        objectDescriptorD2C2_1_create.getPropertyMap().put("d1c2_fk", "d1c2-1");
        objectDescriptorD2C2_1_create.getPropertyMap().put("d1c3_fk", "d1c3-1");
        objectDescriptorD2C2_1_create.getPropertyMap().put("d2c1_fk", "d2c1-1");
        objectDescriptorD2C2_1_create.getDependeeKeys().add("d1c1-1");
        objectDescriptorD2C2_1_create.getDependeeKeys().add("d1c2-1");
        objectDescriptorD2C2_1_create.getDependeeKeys().add("d1c3-1");
        objectDescriptorD2C2_1_create.getDependeeKeys().add("d2c1-1");
        OmgObjectDescriptor objectDescriptorD3C1_1_create = new OmgObjectDescriptor("domain3", "class1", "d3c1-1");
        objectDescriptorD3C1_1_create.getPropertyMap().put("d1c1_fk", "d1c1-1");
        objectDescriptorD3C1_1_create.getPropertyMap().put("d1c2_fk", "(d1c2-1,d1c2-2)");
        objectDescriptorD3C1_1_create.getPropertyMap().put("d2c1_fk", "d2c1-1");
        objectDescriptorD3C1_1_create.getDependeeKeys().add("d1c1-1");
        objectDescriptorD3C1_1_create.getDependeeKeys().add("d1c2-1");
        objectDescriptorD3C1_1_create.getDependeeKeys().add("d1c2-2");
        objectDescriptorD3C1_1_create.getDependeeKeys().add("d2c1-1");
        OmgObjectDescriptor objectDescriptorD3C1_1_remove = new OmgObjectDescriptor("domain3", "class1", "d3c1-1");
        objectDescriptorD3C1_1_remove.getPropertyMap().put("d1c1_fk", PROPERTYVALUE_NOTSET);
        objectDescriptorD3C1_1_remove.getPropertyMap().put("d1c2_fk", PROPERTYVALUE_NOTSET);
        objectDescriptorD3C1_1_remove.getPropertyMap().put("d2c1_fk", PROPERTYVALUE_NOTSET);
        OmgObjectDescriptor objectDescriptorD2C2_1_remove = new OmgObjectDescriptor("domain2", "class2", "d2c2-1");
        objectDescriptorD2C2_1_remove.getPropertyMap().put("name1", PROPERTYVALUE_NOTSET);
        objectDescriptorD2C2_1_remove.getPropertyMap().put("name2", PROPERTYVALUE_NOTSET);
        objectDescriptorD2C2_1_remove.getPropertyMap().put("d1c1_fk", PROPERTYVALUE_NOTSET);
        objectDescriptorD2C2_1_remove.getPropertyMap().put("d1c2_fk", PROPERTYVALUE_NOTSET);
        objectDescriptorD2C2_1_remove.getPropertyMap().put("d1c3_fk", PROPERTYVALUE_NOTSET);
        objectDescriptorD2C2_1_remove.getPropertyMap().put("d2c1_fk", PROPERTYVALUE_NOTSET);
        OmgObjectDescriptor objectDescriptorD2C1_1_remove = new OmgObjectDescriptor("domain2", "class1", "d2c1-1");
        objectDescriptorD2C1_1_remove.getPropertyMap().put("name1", PROPERTYVALUE_NOTSET);
        objectDescriptorD2C1_1_remove.getPropertyMap().put("d1c2_fk", PROPERTYVALUE_NOTSET);
        objectDescriptorD2C1_1_remove.getPropertyMap().put("d1c3_fk", PROPERTYVALUE_NOTSET);
        OmgObjectDescriptor objectDescriptorD1C3_1_remove = new OmgObjectDescriptor("domain1", "class3", "d1c3-1");
        objectDescriptorD1C3_1_remove.getPropertyMap().put("name1", PROPERTYVALUE_NOTSET);
        objectDescriptorD1C3_1_remove.getPropertyMap().put("name2", PROPERTYVALUE_NOTSET);
        objectDescriptorD1C3_1_remove.getPropertyMap().put("d1c1_fk", PROPERTYVALUE_NOTSET);
        OmgObjectDescriptor objectDescriptorD1C2_2_remove = new OmgObjectDescriptor("domain1", "class2", "d1c2-2");
        objectDescriptorD1C2_2_remove.getPropertyMap().put("name1", PROPERTYVALUE_NOTSET);
        objectDescriptorD1C2_2_remove.getPropertyMap().put("name2", PROPERTYVALUE_NOTSET);
        OmgObjectDescriptor objectDescriptorD1C2_1_remove = new OmgObjectDescriptor("domain1", "class2", "d1c2-1");
        objectDescriptorD1C2_1_remove.getPropertyMap().put("name1", PROPERTYVALUE_NOTSET);
        objectDescriptorD1C2_1_remove.getPropertyMap().put("name2", PROPERTYVALUE_NOTSET);
        OmgObjectDescriptor objectDescriptorD1C1_1_remove = new OmgObjectDescriptor("domain1", "class1", "d1c1-1");
        objectDescriptorD1C1_1_remove.getPropertyMap().put("name1", PROPERTYVALUE_NOTSET);
        objectDescriptorD1C1_1_remove.getPropertyMap().put("name2", PROPERTYVALUE_NOTSET);
        objectDescriptorD1C1_1_remove.getPropertyMap().put("name3", PROPERTYVALUE_NOTSET);

        // Prepare the scenario sequence steps
        OmgScenarioSequenceStepDescriptor scenarioSequenceStepDescriptor1 = new OmgScenarioSequenceStepDescriptor("event1", "create");
        scenarioSequenceStepDescriptor1.getObjectDescriptors().add(objectDescriptorD1C1_1_create);
        OmgScenarioSequenceStepDescriptor scenarioSequenceStepDescriptor2 = new OmgScenarioSequenceStepDescriptor("event1", "create");
        scenarioSequenceStepDescriptor2.getObjectDescriptors().add(objectDescriptorD1C2_1_create);
        OmgScenarioSequenceStepDescriptor scenarioSequenceStepDescriptor3 = new OmgScenarioSequenceStepDescriptor("event2", "create");
        scenarioSequenceStepDescriptor3.getObjectDescriptors().add(objectDescriptorD1C2_2_create);
        scenarioSequenceStepDescriptor3.getObjectDescriptors().add(objectDescriptorD1C3_1_create);
        OmgScenarioSequenceStepDescriptor scenarioSequenceStepDescriptor4 = new OmgScenarioSequenceStepDescriptor("event2", "create");
        scenarioSequenceStepDescriptor4.getObjectDescriptors().add(objectDescriptorD2C1_1_create);
        OmgScenarioSequenceStepDescriptor scenarioSequenceStepDescriptor5 = new OmgScenarioSequenceStepDescriptor("event2", "create");
        scenarioSequenceStepDescriptor5.getObjectDescriptors().add(objectDescriptorD2C2_1_create);
        OmgScenarioSequenceStepDescriptor scenarioSequenceStepDescriptor6 = new OmgScenarioSequenceStepDescriptor("event3", "create");
        scenarioSequenceStepDescriptor6.getObjectDescriptors().add(objectDescriptorD3C1_1_create);
        OmgScenarioSequenceStepDescriptor scenarioSequenceStepDescriptor7 = new OmgScenarioSequenceStepDescriptor("event4", "delete");
        scenarioSequenceStepDescriptor7.getObjectDescriptors().add(objectDescriptorD3C1_1_remove);
        OmgScenarioSequenceStepDescriptor scenarioSequenceStepDescriptor8 = new OmgScenarioSequenceStepDescriptor("event4", "delete");
        scenarioSequenceStepDescriptor8.getObjectDescriptors().add(objectDescriptorD2C2_1_remove);


        // TODO

        return result;
      }
    };
  }

  @Test
  void findScenarioSequences() {
    // Arrange

    // Act
    ScenarioSequenceService cut = new ScenarioSequenceService(businessEventService, scenarioService, transitionStateService,
            descriptorRepository);
    Set<OmgScenarioSequence> result = cut.findScenarioSequences();

    // Assert
    //    assertEquals(2, result.size());
  }
}