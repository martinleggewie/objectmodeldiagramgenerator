package org.codemaker.objectmodeldiagramgenerator.testutil;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgAction;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEvent;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgClassDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgDomainDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObject;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenario;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioSequence;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioSequenceDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioSequenceStep;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioSequenceStepDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionState;
import org.codemaker.objectmodeldiagramgenerator.domain.valueobjects.IndexOutOfMaxIndex;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgAction.create;
import static org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgAction.delete;
import static org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgAction.update;
import static org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgEra.future;
import static org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgEra.past;
import static org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgEra.present;
import static org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgOrigin.inside;
import static org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgOrigin.outside;
import static org.codemaker.objectmodeldiagramgenerator.testutil.BusinessEventTestDataCreator.createBusinessEventMap;
import static org.codemaker.objectmodeldiagramgenerator.testutil.ClassTestDataCreator.createClassDescriptorMap;
import static org.codemaker.objectmodeldiagramgenerator.testutil.DomainTestDataCreator.createDomainDescriptorMap;
import static org.codemaker.objectmodeldiagramgenerator.testutil.ObjectTestDataCreator.createObj;
import static org.codemaker.objectmodeldiagramgenerator.testutil.ObjectTestDataCreator.createObjDesc_state1;
import static org.codemaker.objectmodeldiagramgenerator.testutil.ScenarioTestDataCreator.createScenarioMap;
import static org.codemaker.objectmodeldiagramgenerator.testutil.TransitionStateTestDataCreator.createTransitionStateMap;

public class ScenarioSequenceTestDataCreator {

  public static Set<OmgScenarioSequenceDescriptor> createScenarioSequenceDescriptors_state1() {
    Set<OmgScenarioSequenceDescriptor> result = new HashSet<>();

    // 1. Create the first scenario sequence descriptor
    {
      OmgScenarioSequenceDescriptor scenSeqDesc = new OmgScenarioSequenceDescriptor("state1", "PersonsAndHouses");
      scenSeqDesc.getDomainDescriptors().addAll(createDomainDescriptorMap().values());
      scenSeqDesc.getClassDescriptors().addAll(createClassDescriptorMap().values());
      OmgScenarioSequenceStepDescriptor scenSeqStepDesc1 = new OmgScenarioSequenceStepDescriptor("event0101", create.name());
      scenSeqStepDesc1.getObjectDescriptors().add(createObjDesc_state1("house01", create));
      OmgScenarioSequenceStepDescriptor scenSeqStepDesc2 = new OmgScenarioSequenceStepDescriptor("event0101", create.name());
      scenSeqStepDesc2.getObjectDescriptors().add(createObjDesc_state1("floor0101", create));
      OmgScenarioSequenceStepDescriptor scenSeqStepDesc3 = new OmgScenarioSequenceStepDescriptor("event0101", create.name());
      scenSeqStepDesc3.getObjectDescriptors().add(createObjDesc_state1("floor0102", create));
      OmgScenarioSequenceStepDescriptor scenSeqStepDesc4 = new OmgScenarioSequenceStepDescriptor("event0102", create.name());
      scenSeqStepDesc4.getObjectDescriptors().add(createObjDesc_state1("anton", create));
      OmgScenarioSequenceStepDescriptor scenSeqStepDesc5 = new OmgScenarioSequenceStepDescriptor("event0103", create.name());
      scenSeqStepDesc5.getObjectDescriptors().add(createObjDesc_state1("p2f01", create));
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc1);
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc2);
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc3);
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc4);
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc5);

      result.add(scenSeqDesc);
    }

    // 2. Create the second scenario sequence descriptor
    {
      OmgScenarioSequenceDescriptor scenSeqDesc = new OmgScenarioSequenceDescriptor("state1", "PersonsAndHouses");
      scenSeqDesc.getDomainDescriptors().addAll(createDomainDescriptorMap().values());
      scenSeqDesc.getClassDescriptors().addAll(createClassDescriptorMap().values());
      OmgScenarioSequenceStepDescriptor scenSeqStepDesc1 = new OmgScenarioSequenceStepDescriptor("event0201", create.name());
      scenSeqStepDesc1.getObjectDescriptors().add(createObjDesc_state1("berta", create));
      OmgScenarioSequenceStepDescriptor scenSeqStepDesc2 = new OmgScenarioSequenceStepDescriptor("event0202", create.name());
      scenSeqStepDesc2.getObjectDescriptors().add(createObjDesc_state1("p2p01", create));
      OmgScenarioSequenceStepDescriptor scenSeqStepDesc3 = new OmgScenarioSequenceStepDescriptor("event0203", create.name());
      scenSeqStepDesc3.getObjectDescriptors().add(createObjDesc_state1("p2f02", create));
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc1);
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc2);
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc3);

      result.add(scenSeqDesc);
    }

    // 3. Create the third scenario sequence descriptor
    {
      OmgScenarioSequenceDescriptor scenSeqDesc = new OmgScenarioSequenceDescriptor("state1", "PersonsAndHouses");
      scenSeqDesc.getDomainDescriptors().addAll(createDomainDescriptorMap().values());
      scenSeqDesc.getClassDescriptors().addAll(createClassDescriptorMap().values());
      OmgScenarioSequenceStepDescriptor scenSeqStepDesc1 = new OmgScenarioSequenceStepDescriptor("event0301", create.name());
      scenSeqStepDesc1.getObjectDescriptors().add(createObjDesc_state1("house02", create));
      OmgScenarioSequenceStepDescriptor scenSeqStepDesc2 = new OmgScenarioSequenceStepDescriptor("event0301", create.name());
      scenSeqStepDesc2.getObjectDescriptors().add(createObjDesc_state1("floor0201", create));
      OmgScenarioSequenceStepDescriptor scenSeqStepDesc3 = new OmgScenarioSequenceStepDescriptor("event0301", create.name());
      scenSeqStepDesc3.getObjectDescriptors().add(createObjDesc_state1("floor0202", create));
      OmgScenarioSequenceStepDescriptor scenSeqStepDesc4 = new OmgScenarioSequenceStepDescriptor("event0301", create.name());
      scenSeqStepDesc4.getObjectDescriptors().add(createObjDesc_state1("floor0203", create));
      OmgScenarioSequenceStepDescriptor scenSeqStepDesc5 = new OmgScenarioSequenceStepDescriptor("event0301", create.name());
      scenSeqStepDesc5.getObjectDescriptors().add(createObjDesc_state1("floor0204", create));
      OmgScenarioSequenceStepDescriptor scenSeqStepDesc6 = new OmgScenarioSequenceStepDescriptor("event0302", create.name());
      scenSeqStepDesc6.getObjectDescriptors().add(createObjDesc_state1("p2f03", create));
      OmgScenarioSequenceStepDescriptor scenSeqStepDesc7 = new OmgScenarioSequenceStepDescriptor("event0302", create.name());
      scenSeqStepDesc7.getObjectDescriptors().add(createObjDesc_state1("p2f04", create));
      OmgScenarioSequenceStepDescriptor scenSeqStepDesc8 = new OmgScenarioSequenceStepDescriptor("event0302", OmgAction.delete.name());
      scenSeqStepDesc8.getObjectDescriptors().add(createObjDesc_state1("p2f01", OmgAction.delete));
      OmgScenarioSequenceStepDescriptor scenSeqStepDesc9 = new OmgScenarioSequenceStepDescriptor("event0302", OmgAction.delete.name());
      scenSeqStepDesc9.getObjectDescriptors().add(createObjDesc_state1("p2f02", OmgAction.delete));
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc1);
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc2);
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc3);
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc4);
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc5);
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc6);
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc7);
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc8);
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc9);

      result.add(scenSeqDesc);
    }

    // 4. Create the fourth scenario sequence descriptor
    {
      OmgScenarioSequenceDescriptor scenSeqDesc = new OmgScenarioSequenceDescriptor("state1", "PersonsAndHouses");
      scenSeqDesc.getDomainDescriptors().addAll(createDomainDescriptorMap().values());
      scenSeqDesc.getClassDescriptors().addAll(createClassDescriptorMap().values());
      OmgScenarioSequenceStepDescriptor scenSeqStepDesc1 = new OmgScenarioSequenceStepDescriptor("event0401", create.name());
      scenSeqStepDesc1.getObjectDescriptors().add(createObjDesc_state1("charlie", create));
      OmgScenarioSequenceStepDescriptor scenSeqStepDesc2 = new OmgScenarioSequenceStepDescriptor("event0402", create.name());
      scenSeqStepDesc2.getObjectDescriptors().add(createObjDesc_state1("p2f05", create));
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc1);
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc2);

      result.add(scenSeqDesc);
    }

    // 5. Create the fifth scenario sequence descriptor
    {
      OmgScenarioSequenceDescriptor scenSeqDesc = new OmgScenarioSequenceDescriptor("state1", "PersonsAndHouses");
      scenSeqDesc.getDomainDescriptors().addAll(createDomainDescriptorMap().values());
      scenSeqDesc.getClassDescriptors().addAll(createClassDescriptorMap().values());
      OmgScenarioSequenceStepDescriptor scenSeqStepDesc1 = new OmgScenarioSequenceStepDescriptor("event0501", create.name());
      scenSeqStepDesc1.getObjectDescriptors().add(createObjDesc_state1("p2f06", create));
      OmgScenarioSequenceStepDescriptor scenSeqStepDesc2 = new OmgScenarioSequenceStepDescriptor("event0502", OmgAction.delete.name());
      scenSeqStepDesc2.getObjectDescriptors().add(createObjDesc_state1("p2f05", OmgAction.delete));
      OmgScenarioSequenceStepDescriptor scenSeqStepDesc3 = new OmgScenarioSequenceStepDescriptor("event0502", OmgAction.delete.name());
      scenSeqStepDesc3.getObjectDescriptors().add(createObjDesc_state1("floor0101", OmgAction.delete));
      OmgScenarioSequenceStepDescriptor scenSeqStepDesc4 = new OmgScenarioSequenceStepDescriptor("event0502", OmgAction.delete.name());
      scenSeqStepDesc4.getObjectDescriptors().add(createObjDesc_state1("floor0102", OmgAction.delete));
      OmgScenarioSequenceStepDescriptor scenSeqStepDesc5 = new OmgScenarioSequenceStepDescriptor("event0502", OmgAction.delete.name());
      scenSeqStepDesc5.getObjectDescriptors().add(createObjDesc_state1("house01", OmgAction.delete));
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc1);
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc2);
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc3);
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc4);
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc5);

      result.add(scenSeqDesc);
    }

    // 6. Create the sixth scenario sequence descriptor
    {
      OmgScenarioSequenceDescriptor scenSeqDesc = new OmgScenarioSequenceDescriptor("state1", "Persons");
      Map<String, OmgDomainDescriptor> domainDescriptorMap = createDomainDescriptorMap();
      domainDescriptorMap.remove("housedomain");
      scenSeqDesc.getDomainDescriptors().addAll(domainDescriptorMap.values());
      Map<String, OmgClassDescriptor> classDescriptorMap = createClassDescriptorMap();
      classDescriptorMap.remove("houseclass");
      classDescriptorMap.remove("floorclass");
      classDescriptorMap.remove("p2fclass");
      scenSeqDesc.getClassDescriptors().addAll(classDescriptorMap.values());

      OmgScenarioSequenceStepDescriptor scenSeqStepDesc1 = new OmgScenarioSequenceStepDescriptor("event0601", create.name());
      scenSeqStepDesc1.getObjectDescriptors().add(createObjDesc_state1("p2p02", create));
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc1);

      OmgScenarioSequenceStepDescriptor scenSeqStepDesc2 = new OmgScenarioSequenceStepDescriptor("event0602", update.name());
      scenSeqStepDesc2.getObjectDescriptors().add(createObjDesc_state1("anton", update));
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc2);

      OmgScenarioSequenceStepDescriptor scenSeqStepDesc3 = new OmgScenarioSequenceStepDescriptor("event0602", update.name());
      scenSeqStepDesc3.getObjectDescriptors().add(createObjDesc_state1("charlie", update));
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc3);

      OmgScenarioSequenceStepDescriptor scenSeqStepDesc4 = new OmgScenarioSequenceStepDescriptor("event0603", update.name());
      scenSeqStepDesc4.getObjectDescriptors().add(createObjDesc_state1("p2p02_01", update));
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc4);

      OmgScenarioSequenceStepDescriptor scenSeqStepDesc5 = new OmgScenarioSequenceStepDescriptor("event0604", update.name());
      scenSeqStepDesc5.getObjectDescriptors().add(createObjDesc_state1("p2p02_02", update));
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc5);

      OmgScenarioSequenceStepDescriptor scenSeqStepDesc6 = new OmgScenarioSequenceStepDescriptor("event0604", update.name());
      scenSeqStepDesc6.getObjectDescriptors().add(createObjDesc_state1("p2p01", update));
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc6);

      OmgScenarioSequenceStepDescriptor scenSeqStepDesc7 = new OmgScenarioSequenceStepDescriptor("event0605", delete.name());
      scenSeqStepDesc7.getObjectDescriptors().add(createObjDesc_state1("p2p01", delete));
      scenSeqDesc.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc7);

      result.add(scenSeqDesc);
    }

    return result;
  }

  public static Set<OmgScenarioSequenceDescriptor> createScenarioSequenceDescriptors_state2() {
    Set<OmgScenarioSequenceDescriptor> result = new HashSet<>();

    // 1. Create the first scenario sequence descriptor
    OmgScenarioSequenceDescriptor scenSeqDesc1 = new OmgScenarioSequenceDescriptor("state2", "PersonsAndHouses");
    scenSeqDesc1.getDomainDescriptors().addAll(createDomainDescriptorMap().values());
    scenSeqDesc1.getClassDescriptors().addAll(createClassDescriptorMap().values());
    OmgScenarioSequenceStepDescriptor scenSeqStepDesc11 = new OmgScenarioSequenceStepDescriptor("event0101", create.name());
    scenSeqStepDesc11.getObjectDescriptors().add(ObjectTestDataCreator.createObjDesc_state2("house01", create));
    scenSeqDesc1.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc11);

    // And now finish it: Add the scenario sequence descriptor to the result
    result.add(scenSeqDesc1);

    return result;
  }

  public static Set<OmgScenarioSequence> createScenarioSequences_state1() {
    Set<OmgScenarioSequence> result = new HashSet<>();

    result.add(createScenarioSequence_state1_scenario1());
    result.add(createScenarioSequence_state1_scenario2());
    result.add(createScenarioSequence_state1_scenario3());
    result.add(createScenarioSequence_state1_scenario4());
    result.add(createScenarioSequence_state1_scenario5());

    return result;
  }

  private static OmgScenarioSequence createScenarioSequence_state1_scenario1() {

    // 0. Get some more test data managed outside this test generator class
    Map<String, OmgTransitionState> transitionStateMap = createTransitionStateMap();
    Map<String, OmgBusinessEvent> businessEventMap = createBusinessEventMap();
    Map<String, OmgScenario> scenarioMap = createScenarioMap();

    // 1. Generate scenario sequence 1 ond its seven steps (initial, five real steps, final)
    OmgScenarioSequence result = new OmgScenarioSequence("PersonsAndHouses", scenarioMap.get("scenario1"),
            transitionStateMap.get("state1"));

    // 1.1. Generate initial step of scenario sequence 1
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(OmgBusinessEvent.INITIAL, new IndexOutOfMaxIndex(1, 7),
              new IndexOutOfMaxIndex(1, 1));
      OmgObject house01 = createObj(createObjDesc_state1("house01", create), create, future, inside);
      OmgObject floor0101 = createObj(createObjDesc_state1("floor0101", create), create, future, inside, house01);
      OmgObject floor0102 = createObj(createObjDesc_state1("floor0102", create), create, future, inside, house01);
      OmgObject anton = createObj(createObjDesc_state1("anton", create), create, future, inside);
      OmgObject p2f01 = createObj(createObjDesc_state1("p2f01", create), create, future, inside, anton, floor0101);
      step.getObjects().add(house01);
      step.getObjects().add(floor0101);
      step.getObjects().add(floor0102);
      step.getObjects().add(anton);
      step.getObjects().add(p2f01);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.2. Generate step 1 of scenario sequence 1
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(businessEventMap.get("event0101"), new IndexOutOfMaxIndex(2, 7),
              new IndexOutOfMaxIndex(1, 3));
      OmgObject house01 = createObj(createObjDesc_state1("house01", create), create, present, inside);
      OmgObject floor0101 = createObj(createObjDesc_state1("floor0101", create), create, future, inside, house01);
      OmgObject floor0102 = createObj(createObjDesc_state1("floor0102", create), create, future, inside, house01);
      OmgObject anton = createObj(createObjDesc_state1("anton", create), create, future, inside);
      OmgObject p2f01 = createObj(createObjDesc_state1("p2f01", create), create, future, inside, anton, floor0101);
      step.getObjects().add(house01);
      step.getObjects().add(floor0101);
      step.getObjects().add(floor0102);
      step.getObjects().add(anton);
      step.getObjects().add(p2f01);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.3. Generate step 2 of scenario sequence 1
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(businessEventMap.get("event0101"), new IndexOutOfMaxIndex(3, 7),
              new IndexOutOfMaxIndex(2, 3));
      OmgObject house01 = createObj(createObjDesc_state1("house01", create), create, past, inside);
      OmgObject floor0101 = createObj(createObjDesc_state1("floor0101", create), create, present, inside, house01);
      OmgObject floor0102 = createObj(createObjDesc_state1("floor0102", create), create, future, inside, house01);
      OmgObject anton = createObj(createObjDesc_state1("anton", create), create, future, inside);
      OmgObject p2f01 = createObj(createObjDesc_state1("p2f01", create), create, future, inside, anton, floor0101);
      step.getObjects().add(house01);
      step.getObjects().add(floor0101);
      step.getObjects().add(floor0102);
      step.getObjects().add(anton);
      step.getObjects().add(p2f01);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.4. Generate step 3 of scenario sequence 1
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(businessEventMap.get("event0101"), new IndexOutOfMaxIndex(4, 7),
              new IndexOutOfMaxIndex(3, 3));
      OmgObject house01 = createObj(createObjDesc_state1("house01", create), create, past, inside);
      OmgObject floor0101 = createObj(createObjDesc_state1("floor0101", create), create, past, inside, house01);
      OmgObject floor0102 = createObj(createObjDesc_state1("floor0102", create), create, present, inside, house01);
      OmgObject anton = createObj(createObjDesc_state1("anton", create), create, future, inside);
      OmgObject p2f01 = createObj(createObjDesc_state1("p2f01", create), create, future, inside, anton, floor0101);
      step.getObjects().add(house01);
      step.getObjects().add(floor0101);
      step.getObjects().add(floor0102);
      step.getObjects().add(anton);
      step.getObjects().add(p2f01);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.5. Generate step 4 of scenario sequence 1
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(businessEventMap.get("event0102"), new IndexOutOfMaxIndex(5, 7),
              new IndexOutOfMaxIndex(1, 1));
      OmgObject house01 = createObj(createObjDesc_state1("house01", create), create, past, inside);
      OmgObject floor0101 = createObj(createObjDesc_state1("floor0101", create), create, past, inside, house01);
      OmgObject floor0102 = createObj(createObjDesc_state1("floor0102", create), create, past, inside, house01);
      OmgObject anton = createObj(createObjDesc_state1("anton", create), create, present, inside);
      OmgObject p2f01 = createObj(createObjDesc_state1("p2f01", create), create, future, inside, anton, floor0101);
      step.getObjects().add(house01);
      step.getObjects().add(floor0101);
      step.getObjects().add(floor0102);
      step.getObjects().add(anton);
      step.getObjects().add(p2f01);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.6. Generate step 5 of scenario sequence 1
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(businessEventMap.get("event0103"), new IndexOutOfMaxIndex(6, 7),
              new IndexOutOfMaxIndex(1, 1));
      OmgObject house01 = createObj(createObjDesc_state1("house01", create), create, past, inside);
      OmgObject floor0101 = createObj(createObjDesc_state1("floor0101", create), create, past, inside, house01);
      OmgObject floor0102 = createObj(createObjDesc_state1("floor0102", create), create, past, inside, house01);
      OmgObject anton = createObj(createObjDesc_state1("anton", create), create, past, inside);
      OmgObject p2f01 = createObj(createObjDesc_state1("p2f01", create), create, present, inside, anton, floor0101);
      step.getObjects().add(house01);
      step.getObjects().add(floor0101);
      step.getObjects().add(floor0102);
      step.getObjects().add(anton);
      step.getObjects().add(p2f01);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.7. Generate final step of scenario sequence 1
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(OmgBusinessEvent.FINAL, new IndexOutOfMaxIndex(7, 7),
              new IndexOutOfMaxIndex(1, 1));
      OmgObject house01 = createObj(createObjDesc_state1("house01", create), create, past, inside);
      OmgObject floor0101 = createObj(createObjDesc_state1("floor0101", create), create, past, inside, house01);
      OmgObject floor0102 = createObj(createObjDesc_state1("floor0102", create), create, past, inside, house01);
      OmgObject anton = createObj(createObjDesc_state1("anton", create), create, past, inside);
      OmgObject p2f01 = createObj(createObjDesc_state1("p2f01", create), create, past, inside, anton, floor0101);
      step.getObjects().add(house01);
      step.getObjects().add(floor0101);
      step.getObjects().add(floor0102);
      step.getObjects().add(anton);
      step.getObjects().add(p2f01);
      result.getScenarioSequenceSteps().add(step);
    }

    return result;
  }

  private static OmgScenarioSequence createScenarioSequence_state1_scenario2() {

    // 0. Get some more test data managed outside this test generator class
    Map<String, OmgTransitionState> transitionStateMap = createTransitionStateMap();
    Map<String, OmgBusinessEvent> businessEventMap = createBusinessEventMap();
    Map<String, OmgScenario> scenarioMap = createScenarioMap();

    // 1. Generate scenario sequence 2 ond its five steps (initial, three real steps, final)
    OmgScenarioSequence result = new OmgScenarioSequence("PersonsAndHouses", scenarioMap.get("scenario2"),
            transitionStateMap.get("state1"));

    // 1.1. Generate initial step of scenario sequence 2
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(OmgBusinessEvent.INITIAL, new IndexOutOfMaxIndex(1, 5),
              new IndexOutOfMaxIndex(1, 1));
      OmgObject anton = createObj(createObjDesc_state1("anton", create), create, future, outside);
      OmgObject floor0101 = createObj(createObjDesc_state1("floor0101", create), create, future, outside);
      OmgObject berta = createObj(createObjDesc_state1("berta", create), create, future, inside);
      OmgObject p2p01 = createObj(createObjDesc_state1("p2p01", create), create, future, inside, anton, berta);
      OmgObject p2f02 = createObj(createObjDesc_state1("p2f02", create), create, future, inside, berta, floor0101);
      step.getObjects().add(anton);
      step.getObjects().add(floor0101);
      step.getObjects().add(berta);
      step.getObjects().add(p2p01);
      step.getObjects().add(p2f02);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.2. Generate step 1 of scenario sequence 2
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(businessEventMap.get("event0201"), new IndexOutOfMaxIndex(2, 5),
              new IndexOutOfMaxIndex(1, 1));
      OmgObject anton = createObj(createObjDesc_state1("anton", create), create, future, outside);
      OmgObject floor0101 = createObj(createObjDesc_state1("floor0101", create), create, future, outside);
      OmgObject berta = createObj(createObjDesc_state1("berta", create), create, present, inside);
      OmgObject p2p01 = createObj(createObjDesc_state1("p2p01", create), create, future, inside, anton, berta);
      OmgObject p2f02 = createObj(createObjDesc_state1("p2f02", create), create, future, inside, berta, floor0101);
      step.getObjects().add(anton);
      step.getObjects().add(floor0101);
      step.getObjects().add(berta);
      step.getObjects().add(p2p01);
      step.getObjects().add(p2f02);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.3. Generate step 2 of scenario sequence 2
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(businessEventMap.get("event0202"), new IndexOutOfMaxIndex(3, 5),
              new IndexOutOfMaxIndex(1, 1));
      OmgObject anton = createObj(createObjDesc_state1("anton", create), create, present, outside);
      OmgObject floor0101 = createObj(createObjDesc_state1("floor0101", create), create, future, outside);
      OmgObject berta = createObj(createObjDesc_state1("berta", create), create, past, inside);
      OmgObject p2p01 = createObj(createObjDesc_state1("p2p01", create), create, present, inside, anton, berta);
      OmgObject p2f02 = createObj(createObjDesc_state1("p2f02", create), create, future, inside, berta, floor0101);
      step.getObjects().add(anton);
      step.getObjects().add(floor0101);
      step.getObjects().add(berta);
      step.getObjects().add(p2p01);
      step.getObjects().add(p2f02);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.4. Generate step 3 of scenario sequence 2
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(businessEventMap.get("event0203"), new IndexOutOfMaxIndex(4, 5),
              new IndexOutOfMaxIndex(1, 1));
      OmgObject anton = createObj(createObjDesc_state1("anton", create), create, past, outside);
      OmgObject floor0101 = createObj(createObjDesc_state1("floor0101", create), create, present, outside);
      OmgObject berta = createObj(createObjDesc_state1("berta", create), create, past, inside);
      OmgObject p2p01 = createObj(createObjDesc_state1("p2p01", create), create, past, inside, anton, berta);
      OmgObject p2f02 = createObj(createObjDesc_state1("p2f02", create), create, present, inside, berta, floor0101);
      step.getObjects().add(anton);
      step.getObjects().add(floor0101);
      step.getObjects().add(berta);
      step.getObjects().add(p2p01);
      step.getObjects().add(p2f02);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.5. Generate final step of scenario sequence 2
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(OmgBusinessEvent.FINAL, new IndexOutOfMaxIndex(5, 5),
              new IndexOutOfMaxIndex(1, 1));
      OmgObject anton = createObj(createObjDesc_state1("anton", create), create, past, outside);
      OmgObject floor0101 = createObj(createObjDesc_state1("floor0101", create), create, past, outside);
      OmgObject berta = createObj(createObjDesc_state1("berta", create), create, past, inside);
      OmgObject p2p01 = createObj(createObjDesc_state1("p2p01", create), create, past, inside, anton, berta);
      OmgObject p2f02 = createObj(createObjDesc_state1("p2f02", create), create, past, inside, berta, floor0101);
      step.getObjects().add(anton);
      step.getObjects().add(floor0101);
      step.getObjects().add(berta);
      step.getObjects().add(p2p01);
      step.getObjects().add(p2f02);
      result.getScenarioSequenceSteps().add(step);
    }

    return result;
  }

  private static OmgScenarioSequence createScenarioSequence_state1_scenario3() {

    // 0. Get some more test data managed outside this test generator class
    Map<String, OmgTransitionState> transitionStateMap = createTransitionStateMap();
    Map<String, OmgBusinessEvent> businessEventMap = createBusinessEventMap();
    Map<String, OmgScenario> scenarioMap = createScenarioMap();

    // 1. Generate scenario sequence 3 ond its eleven steps (initial, nine real steps, final)
    OmgScenarioSequence result = new OmgScenarioSequence("PersonsAndHouses", scenarioMap.get("scenario3"),
            transitionStateMap.get("state1"));

    // 1.1. Generate initial step of scenario sequence 3
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(OmgBusinessEvent.INITIAL, new IndexOutOfMaxIndex(1, 11),
              new IndexOutOfMaxIndex(1, 1));
      OmgObject anton = createObj(createObjDesc_state1("anton", create), create, future, outside);
      OmgObject berta = createObj(createObjDesc_state1("berta", create), create, future, outside);
      OmgObject house02 = createObj(createObjDesc_state1("house02", create), create, future, inside);
      OmgObject floor0101 = createObj(createObjDesc_state1("floor0101", create), create, future, outside);
      OmgObject floor0201 = createObj(createObjDesc_state1("floor0201", create), create, future, inside, house02);
      OmgObject floor0202 = createObj(createObjDesc_state1("floor0202", create), create, future, inside, house02);
      OmgObject floor0203 = createObj(createObjDesc_state1("floor0203", create), create, future, inside, house02);
      OmgObject floor0204 = createObj(createObjDesc_state1("floor0204", create), create, future, inside, house02);
      OmgObject p2f03 = createObj(createObjDesc_state1("p2f03", create), create, future, inside, anton, floor0203);
      OmgObject p2f04 = createObj(createObjDesc_state1("p2f04", create), create, future, inside, berta, floor0203);
      OmgObject p2f01 = createObj(createObjDesc_state1("p2f01", create), delete, future, inside, anton, floor0101);
      OmgObject p2f02 = createObj(createObjDesc_state1("p2f02", create), delete, future, inside, berta, floor0101);
      step.getObjects().add(anton);
      step.getObjects().add(berta);
      step.getObjects().add(house02);
      step.getObjects().add(floor0101);
      step.getObjects().add(floor0201);
      step.getObjects().add(floor0202);
      step.getObjects().add(floor0203);
      step.getObjects().add(floor0204);
      step.getObjects().add(p2f03);
      step.getObjects().add(p2f04);
      step.getObjects().add(p2f01);
      step.getObjects().add(p2f02);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.2. Generate step 1 of scenario sequence 3
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(businessEventMap.get("event0301"), new IndexOutOfMaxIndex(2, 11),
              new IndexOutOfMaxIndex(1, 5));
      OmgObject anton = createObj(createObjDesc_state1("anton", create), create, future, outside);
      OmgObject berta = createObj(createObjDesc_state1("berta", create), create, future, outside);
      OmgObject house02 = createObj(createObjDesc_state1("house02", create), create, present, inside);
      OmgObject floor0101 = createObj(createObjDesc_state1("floor0101", create), create, future, outside);
      OmgObject floor0201 = createObj(createObjDesc_state1("floor0201", create), create, future, inside, house02);
      OmgObject floor0202 = createObj(createObjDesc_state1("floor0202", create), create, future, inside, house02);
      OmgObject floor0203 = createObj(createObjDesc_state1("floor0203", create), create, future, inside, house02);
      OmgObject floor0204 = createObj(createObjDesc_state1("floor0204", create), create, future, inside, house02);
      OmgObject p2f03 = createObj(createObjDesc_state1("p2f03", create), create, future, inside, anton, floor0203);
      OmgObject p2f04 = createObj(createObjDesc_state1("p2f04", create), create, future, inside, berta, floor0203);
      OmgObject p2f01 = createObj(createObjDesc_state1("p2f01", create), delete, future, inside, anton, floor0101);
      OmgObject p2f02 = createObj(createObjDesc_state1("p2f02", create), delete, future, inside, berta, floor0101);
      step.getObjects().add(anton);
      step.getObjects().add(berta);
      step.getObjects().add(house02);
      step.getObjects().add(floor0101);
      step.getObjects().add(floor0201);
      step.getObjects().add(floor0202);
      step.getObjects().add(floor0203);
      step.getObjects().add(floor0204);
      step.getObjects().add(p2f03);
      step.getObjects().add(p2f04);
      step.getObjects().add(p2f01);
      step.getObjects().add(p2f02);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.3. Generate step 2 of scenario sequence 3
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(businessEventMap.get("event0301"), new IndexOutOfMaxIndex(3, 11),
              new IndexOutOfMaxIndex(2, 5));
      OmgObject anton = createObj(createObjDesc_state1("anton", create), create, future, outside);
      OmgObject berta = createObj(createObjDesc_state1("berta", create), create, future, outside);
      OmgObject house02 = createObj(createObjDesc_state1("house02", create), create, past, inside);
      OmgObject floor0101 = createObj(createObjDesc_state1("floor0101", create), create, future, outside);
      OmgObject floor0201 = createObj(createObjDesc_state1("floor0201", create), create, present, inside, house02);
      OmgObject floor0202 = createObj(createObjDesc_state1("floor0202", create), create, future, inside, house02);
      OmgObject floor0203 = createObj(createObjDesc_state1("floor0203", create), create, future, inside, house02);
      OmgObject floor0204 = createObj(createObjDesc_state1("floor0204", create), create, future, inside, house02);
      OmgObject p2f03 = createObj(createObjDesc_state1("p2f03", create), create, future, inside, anton, floor0203);
      OmgObject p2f04 = createObj(createObjDesc_state1("p2f04", create), create, future, inside, berta, floor0203);
      OmgObject p2f01 = createObj(createObjDesc_state1("p2f01", create), delete, future, inside, anton, floor0101);
      OmgObject p2f02 = createObj(createObjDesc_state1("p2f02", create), delete, future, inside, berta, floor0101);
      step.getObjects().add(anton);
      step.getObjects().add(berta);
      step.getObjects().add(house02);
      step.getObjects().add(floor0101);
      step.getObjects().add(floor0201);
      step.getObjects().add(floor0202);
      step.getObjects().add(floor0203);
      step.getObjects().add(floor0204);
      step.getObjects().add(p2f03);
      step.getObjects().add(p2f04);
      step.getObjects().add(p2f01);
      step.getObjects().add(p2f02);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.4. Generate step 3 of scenario sequence 3
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(businessEventMap.get("event0301"), new IndexOutOfMaxIndex(4, 11),
              new IndexOutOfMaxIndex(3, 5));
      OmgObject anton = createObj(createObjDesc_state1("anton", create), create, future, outside);
      OmgObject berta = createObj(createObjDesc_state1("berta", create), create, future, outside);
      OmgObject house02 = createObj(createObjDesc_state1("house02", create), create, past, inside);
      OmgObject floor0101 = createObj(createObjDesc_state1("floor0101", create), create, future, outside);
      OmgObject floor0201 = createObj(createObjDesc_state1("floor0201", create), create, past, inside, house02);
      OmgObject floor0202 = createObj(createObjDesc_state1("floor0202", create), create, present, inside, house02);
      OmgObject floor0203 = createObj(createObjDesc_state1("floor0203", create), create, future, inside, house02);
      OmgObject floor0204 = createObj(createObjDesc_state1("floor0204", create), create, future, inside, house02);
      OmgObject p2f03 = createObj(createObjDesc_state1("p2f03", create), create, future, inside, anton, floor0203);
      OmgObject p2f04 = createObj(createObjDesc_state1("p2f04", create), create, future, inside, berta, floor0203);
      OmgObject p2f01 = createObj(createObjDesc_state1("p2f01", create), delete, future, inside, anton, floor0101);
      OmgObject p2f02 = createObj(createObjDesc_state1("p2f02", create), delete, future, inside, berta, floor0101);
      step.getObjects().add(anton);
      step.getObjects().add(berta);
      step.getObjects().add(house02);
      step.getObjects().add(floor0101);
      step.getObjects().add(floor0201);
      step.getObjects().add(floor0202);
      step.getObjects().add(floor0203);
      step.getObjects().add(floor0204);
      step.getObjects().add(p2f03);
      step.getObjects().add(p2f04);
      step.getObjects().add(p2f01);
      step.getObjects().add(p2f02);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.5. Generate step 4 of scenario sequence 3
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(businessEventMap.get("event0301"), new IndexOutOfMaxIndex(5, 11),
              new IndexOutOfMaxIndex(4, 5));
      OmgObject anton = createObj(createObjDesc_state1("anton", create), create, future, outside);
      OmgObject berta = createObj(createObjDesc_state1("berta", create), create, future, outside);
      OmgObject house02 = createObj(createObjDesc_state1("house02", create), create, past, inside);
      OmgObject floor0101 = createObj(createObjDesc_state1("floor0101", create), create, future, outside);
      OmgObject floor0201 = createObj(createObjDesc_state1("floor0201", create), create, past, inside, house02);
      OmgObject floor0202 = createObj(createObjDesc_state1("floor0202", create), create, past, inside, house02);
      OmgObject floor0203 = createObj(createObjDesc_state1("floor0203", create), create, present, inside, house02);
      OmgObject floor0204 = createObj(createObjDesc_state1("floor0204", create), create, future, inside, house02);
      OmgObject p2f03 = createObj(createObjDesc_state1("p2f03", create), create, future, inside, anton, floor0203);
      OmgObject p2f04 = createObj(createObjDesc_state1("p2f04", create), create, future, inside, berta, floor0203);
      OmgObject p2f01 = createObj(createObjDesc_state1("p2f01", create), delete, future, inside, anton, floor0101);
      OmgObject p2f02 = createObj(createObjDesc_state1("p2f02", create), delete, future, inside, berta, floor0101);
      step.getObjects().add(anton);
      step.getObjects().add(berta);
      step.getObjects().add(house02);
      step.getObjects().add(floor0101);
      step.getObjects().add(floor0201);
      step.getObjects().add(floor0202);
      step.getObjects().add(floor0203);
      step.getObjects().add(floor0204);
      step.getObjects().add(p2f03);
      step.getObjects().add(p2f04);
      step.getObjects().add(p2f01);
      step.getObjects().add(p2f02);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.6. Generate step 5 of scenario sequence 3
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(businessEventMap.get("event0301"), new IndexOutOfMaxIndex(6, 11),
              new IndexOutOfMaxIndex(5, 5));
      OmgObject anton = createObj(createObjDesc_state1("anton", create), create, future, outside);
      OmgObject berta = createObj(createObjDesc_state1("berta", create), create, future, outside);
      OmgObject house02 = createObj(createObjDesc_state1("house02", create), create, past, inside);
      OmgObject floor0101 = createObj(createObjDesc_state1("floor0101", create), create, future, outside);
      OmgObject floor0201 = createObj(createObjDesc_state1("floor0201", create), create, past, inside, house02);
      OmgObject floor0202 = createObj(createObjDesc_state1("floor0202", create), create, past, inside, house02);
      OmgObject floor0203 = createObj(createObjDesc_state1("floor0203", create), create, past, inside, house02);
      OmgObject floor0204 = createObj(createObjDesc_state1("floor0204", create), create, present, inside, house02);
      OmgObject p2f03 = createObj(createObjDesc_state1("p2f03", create), create, future, inside, anton, floor0203);
      OmgObject p2f04 = createObj(createObjDesc_state1("p2f04", create), create, future, inside, berta, floor0203);
      OmgObject p2f01 = createObj(createObjDesc_state1("p2f01", create), delete, future, inside, anton, floor0101);
      OmgObject p2f02 = createObj(createObjDesc_state1("p2f02", create), delete, future, inside, berta, floor0101);
      step.getObjects().add(anton);
      step.getObjects().add(berta);
      step.getObjects().add(house02);
      step.getObjects().add(floor0101);
      step.getObjects().add(floor0201);
      step.getObjects().add(floor0202);
      step.getObjects().add(floor0203);
      step.getObjects().add(floor0204);
      step.getObjects().add(p2f03);
      step.getObjects().add(p2f04);
      step.getObjects().add(p2f01);
      step.getObjects().add(p2f02);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.7. Generate step 6 of scenario sequence 3
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(businessEventMap.get("event0302"), new IndexOutOfMaxIndex(7, 11),
              new IndexOutOfMaxIndex(1, 4));
      OmgObject anton = createObj(createObjDesc_state1("anton", create), create, present, outside);
      OmgObject berta = createObj(createObjDesc_state1("berta", create), create, future, outside);
      OmgObject house02 = createObj(createObjDesc_state1("house02", create), create, past, inside);
      OmgObject floor0101 = createObj(createObjDesc_state1("floor0101", create), create, future, outside);
      OmgObject floor0201 = createObj(createObjDesc_state1("floor0201", create), create, past, inside, house02);
      OmgObject floor0202 = createObj(createObjDesc_state1("floor0202", create), create, past, inside, house02);
      OmgObject floor0203 = createObj(createObjDesc_state1("floor0203", create), create, past, inside, house02);
      OmgObject floor0204 = createObj(createObjDesc_state1("floor0204", create), create, past, inside, house02);
      OmgObject p2f03 = createObj(createObjDesc_state1("p2f03", create), create, present, inside, anton, floor0203);
      OmgObject p2f04 = createObj(createObjDesc_state1("p2f04", create), create, future, inside, berta, floor0203);
      OmgObject p2f01 = createObj(createObjDesc_state1("p2f01", create), delete, future, inside, anton, floor0101);
      OmgObject p2f02 = createObj(createObjDesc_state1("p2f02", create), delete, future, inside, berta, floor0101);
      step.getObjects().add(anton);
      step.getObjects().add(berta);
      step.getObjects().add(house02);
      step.getObjects().add(floor0101);
      step.getObjects().add(floor0201);
      step.getObjects().add(floor0202);
      step.getObjects().add(floor0203);
      step.getObjects().add(floor0204);
      step.getObjects().add(p2f03);
      step.getObjects().add(p2f04);
      step.getObjects().add(p2f01);
      step.getObjects().add(p2f02);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.8. Generate step 7 of scenario sequence 3
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(businessEventMap.get("event0302"), new IndexOutOfMaxIndex(8, 11),
              new IndexOutOfMaxIndex(2, 4));
      OmgObject anton = createObj(createObjDesc_state1("anton", create), create, past, outside);
      OmgObject berta = createObj(createObjDesc_state1("berta", create), create, present, outside);
      OmgObject house02 = createObj(createObjDesc_state1("house02", create), create, past, inside);
      OmgObject floor0101 = createObj(createObjDesc_state1("floor0101", create), create, future, outside);
      OmgObject floor0201 = createObj(createObjDesc_state1("floor0201", create), create, past, inside, house02);
      OmgObject floor0202 = createObj(createObjDesc_state1("floor0202", create), create, past, inside, house02);
      OmgObject floor0203 = createObj(createObjDesc_state1("floor0203", create), create, past, inside, house02);
      OmgObject floor0204 = createObj(createObjDesc_state1("floor0204", create), create, past, inside, house02);
      OmgObject p2f03 = createObj(createObjDesc_state1("p2f03", create), create, past, inside, anton, floor0203);
      OmgObject p2f04 = createObj(createObjDesc_state1("p2f04", create), create, present, inside, berta, floor0203);
      OmgObject p2f01 = createObj(createObjDesc_state1("p2f01", create), delete, future, inside, anton, floor0101);
      OmgObject p2f02 = createObj(createObjDesc_state1("p2f02", create), delete, future, inside, berta, floor0101);
      step.getObjects().add(anton);
      step.getObjects().add(berta);
      step.getObjects().add(house02);
      step.getObjects().add(floor0101);
      step.getObjects().add(floor0201);
      step.getObjects().add(floor0202);
      step.getObjects().add(floor0203);
      step.getObjects().add(floor0204);
      step.getObjects().add(p2f03);
      step.getObjects().add(p2f04);
      step.getObjects().add(p2f01);
      step.getObjects().add(p2f02);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.9. Generate step 8 of scenario sequence 3
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(businessEventMap.get("event0302"), new IndexOutOfMaxIndex(9, 11),
              new IndexOutOfMaxIndex(3, 4));
      OmgObject anton = createObj(createObjDesc_state1("anton", create), create, past, outside);
      OmgObject berta = createObj(createObjDesc_state1("berta", create), create, past, outside);
      OmgObject house02 = createObj(createObjDesc_state1("house02", create), create, past, inside);
      OmgObject floor0101 = createObj(createObjDesc_state1("floor0101", create), create, present, outside);
      OmgObject floor0201 = createObj(createObjDesc_state1("floor0201", create), create, past, inside, house02);
      OmgObject floor0202 = createObj(createObjDesc_state1("floor0202", create), create, past, inside, house02);
      OmgObject floor0203 = createObj(createObjDesc_state1("floor0203", create), create, past, inside, house02);
      OmgObject floor0204 = createObj(createObjDesc_state1("floor0204", create), create, past, inside, house02);
      OmgObject p2f03 = createObj(createObjDesc_state1("p2f03", create), create, past, inside, anton, floor0203);
      OmgObject p2f04 = createObj(createObjDesc_state1("p2f04", create), create, past, inside, berta, floor0203);
      OmgObject p2f01 = createObj(createObjDesc_state1("p2f01", create), delete, present, inside, anton, floor0101);
      OmgObject p2f02 = createObj(createObjDesc_state1("p2f02", create), delete, future, inside, berta, floor0101);
      step.getObjects().add(anton);
      step.getObjects().add(berta);
      step.getObjects().add(house02);
      step.getObjects().add(floor0101);
      step.getObjects().add(floor0201);
      step.getObjects().add(floor0202);
      step.getObjects().add(floor0203);
      step.getObjects().add(floor0204);
      step.getObjects().add(p2f03);
      step.getObjects().add(p2f04);
      step.getObjects().add(p2f01);
      step.getObjects().add(p2f02);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.10. Generate step 9 of scenario sequence 3
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(businessEventMap.get("event0302"), new IndexOutOfMaxIndex(10, 11),
              new IndexOutOfMaxIndex(4, 4));
      OmgObject anton = createObj(createObjDesc_state1("anton", create), create, past, outside);
      OmgObject berta = createObj(createObjDesc_state1("berta", create), create, past, outside);
      OmgObject house02 = createObj(createObjDesc_state1("house02", create), create, past, inside);
      OmgObject floor0101 = createObj(createObjDesc_state1("floor0101", create), create, past, outside);
      OmgObject floor0201 = createObj(createObjDesc_state1("floor0201", create), create, past, inside, house02);
      OmgObject floor0202 = createObj(createObjDesc_state1("floor0202", create), create, past, inside, house02);
      OmgObject floor0203 = createObj(createObjDesc_state1("floor0203", create), create, past, inside, house02);
      OmgObject floor0204 = createObj(createObjDesc_state1("floor0204", create), create, past, inside, house02);
      OmgObject p2f03 = createObj(createObjDesc_state1("p2f03", create), create, past, inside, anton, floor0203);
      OmgObject p2f04 = createObj(createObjDesc_state1("p2f04", create), create, past, inside, berta, floor0203);
      OmgObject p2f01 = createObj(createObjDesc_state1("p2f01", create), delete, past, inside, anton, floor0101);
      OmgObject p2f02 = createObj(createObjDesc_state1("p2f02", create), delete, present, inside, berta, floor0101);
      step.getObjects().add(anton);
      step.getObjects().add(berta);
      step.getObjects().add(house02);
      step.getObjects().add(floor0101);
      step.getObjects().add(floor0201);
      step.getObjects().add(floor0202);
      step.getObjects().add(floor0203);
      step.getObjects().add(floor0204);
      step.getObjects().add(p2f03);
      step.getObjects().add(p2f04);
      step.getObjects().add(p2f01);
      step.getObjects().add(p2f02);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.11. Generate final step of scenario sequence 3
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(OmgBusinessEvent.FINAL, new IndexOutOfMaxIndex(11, 11),
              new IndexOutOfMaxIndex(1, 1));
      OmgObject anton = createObj(createObjDesc_state1("anton", create), create, past, outside);
      OmgObject berta = createObj(createObjDesc_state1("berta", create), create, past, outside);
      OmgObject house02 = createObj(createObjDesc_state1("house02", create), create, past, inside);
      OmgObject floor0101 = createObj(createObjDesc_state1("floor0101", create), create, past, outside);
      OmgObject floor0201 = createObj(createObjDesc_state1("floor0201", create), create, past, inside, house02);
      OmgObject floor0202 = createObj(createObjDesc_state1("floor0202", create), create, past, inside, house02);
      OmgObject floor0203 = createObj(createObjDesc_state1("floor0203", create), create, past, inside, house02);
      OmgObject floor0204 = createObj(createObjDesc_state1("floor0204", create), create, past, inside, house02);
      OmgObject p2f03 = createObj(createObjDesc_state1("p2f03", create), create, past, inside, anton, floor0203);
      OmgObject p2f04 = createObj(createObjDesc_state1("p2f04", create), create, past, inside, berta, floor0203);
      OmgObject p2f01 = createObj(createObjDesc_state1("p2f01", create), delete, past, inside, anton, floor0101);
      OmgObject p2f02 = createObj(createObjDesc_state1("p2f02", create), delete, past, inside, berta, floor0101);
      step.getObjects().add(anton);
      step.getObjects().add(berta);
      step.getObjects().add(house02);
      step.getObjects().add(floor0101);
      step.getObjects().add(floor0201);
      step.getObjects().add(floor0202);
      step.getObjects().add(floor0203);
      step.getObjects().add(floor0204);
      step.getObjects().add(p2f03);
      step.getObjects().add(p2f04);
      step.getObjects().add(p2f01);
      step.getObjects().add(p2f02);
      result.getScenarioSequenceSteps().add(step);
    }

    return result;
  }

  private static OmgScenarioSequence createScenarioSequence_state1_scenario4() {

    // 0. Get some more test data managed outside this test generator class
    Map<String, OmgTransitionState> transitionStateMap = createTransitionStateMap();
    Map<String, OmgBusinessEvent> businessEventMap = createBusinessEventMap();
    Map<String, OmgScenario> scenarioMap = createScenarioMap();

    // 1. Generate scenario sequence 4 ond its four steps (initial, two real steps, final)
    OmgScenarioSequence result = new OmgScenarioSequence("PersonsAndHouses", scenarioMap.get("scenario4"),
            transitionStateMap.get("state1"));

    // 1.1. Generate initial step of scenario sequence 4
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(OmgBusinessEvent.INITIAL, new IndexOutOfMaxIndex(1, 4),
              new IndexOutOfMaxIndex(1, 1));
      OmgObject floor0102 = createObj(createObjDesc_state1("floor0102", create), create, future, outside);
      OmgObject charlie = createObj(createObjDesc_state1("charlie", create), create, future, inside);
      OmgObject p2f05 = createObj(createObjDesc_state1("p2f05", create), create, future, inside, charlie, floor0102);
      step.getObjects().add(floor0102);
      step.getObjects().add(charlie);
      step.getObjects().add(p2f05);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.2. Generate step 1 of scenario sequence 4
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(businessEventMap.get("event0401"), new IndexOutOfMaxIndex(2, 4),
              new IndexOutOfMaxIndex(1, 1));
      OmgObject floor0102 = createObj(createObjDesc_state1("floor0102", create), create, future, outside);
      OmgObject charlie = createObj(createObjDesc_state1("charlie", create), create, present, inside);
      OmgObject p2f05 = createObj(createObjDesc_state1("p2f05", create), create, future, inside, charlie, floor0102);
      step.getObjects().add(floor0102);
      step.getObjects().add(charlie);
      step.getObjects().add(p2f05);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.3. Generate step 2 of scenario sequence 4
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(businessEventMap.get("event0402"), new IndexOutOfMaxIndex(3, 4),
              new IndexOutOfMaxIndex(1, 1));
      OmgObject floor0102 = createObj(createObjDesc_state1("floor0102", create), create, present, outside);
      OmgObject charlie = createObj(createObjDesc_state1("charlie", create), create, past, inside);
      OmgObject p2f05 = createObj(createObjDesc_state1("p2f05", create), create, present, inside, charlie, floor0102);
      step.getObjects().add(floor0102);
      step.getObjects().add(charlie);
      step.getObjects().add(p2f05);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.4. Generate final step of scenario sequence 4
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(OmgBusinessEvent.FINAL, new IndexOutOfMaxIndex(4, 4),
              new IndexOutOfMaxIndex(1, 1));
      OmgObject floor0102 = createObj(createObjDesc_state1("floor0102", create), create, past, outside);
      OmgObject charlie = createObj(createObjDesc_state1("charlie", create), create, past, inside);
      OmgObject p2f05 = createObj(createObjDesc_state1("p2f05", create), create, past, inside, charlie, floor0102);
      step.getObjects().add(floor0102);
      step.getObjects().add(charlie);
      step.getObjects().add(p2f05);
      result.getScenarioSequenceSteps().add(step);
    }

    return result;
  }

  private static OmgScenarioSequence createScenarioSequence_state1_scenario5() {

    // 0. Get some more test data managed outside this test generator class
    Map<String, OmgTransitionState> transitionStateMap = createTransitionStateMap();
    Map<String, OmgBusinessEvent> businessEventMap = createBusinessEventMap();
    Map<String, OmgScenario> scenarioMap = createScenarioMap();

    // 1. Generate scenario sequence 5 ond its seven steps (initial, five real steps, final)
    OmgScenarioSequence result = new OmgScenarioSequence("PersonsAndHouses", scenarioMap.get("scenario5"),
            transitionStateMap.get("state1"));

    // 1.1. Generate initial step of scenario sequence 5
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(OmgBusinessEvent.INITIAL, new IndexOutOfMaxIndex(1, 7),
              new IndexOutOfMaxIndex(1, 1));
      OmgObject charlie_c = createObj(createObjDesc_state1("charlie", create), create, future, outside);
      OmgObject floor0204_c = createObj(createObjDesc_state1("floor0204", create), create, future, outside);
      OmgObject p2f06_c = createObj(createObjDesc_state1("p2f06", create), create, future, inside, charlie_c, floor0204_c);
      OmgObject house01_c = createObj(createObjDesc_state1("house01", create), create, future, outside);
      OmgObject floor0102_c = createObj(createObjDesc_state1("floor0102", create), create, future, outside, house01_c);
      OmgObject p2f05_d = createObj(createObjDesc_state1("p2f05", create), delete, future, inside, charlie_c, floor0102_c);
      OmgObject floor0101_d = createObj(createObjDesc_state1("floor0101", create), delete, future, inside, house01_c);
      OmgObject floor0102_d = createObj(createObjDesc_state1("floor0102", create), delete, future, inside, house01_c);
      OmgObject house01_d = createObj(createObjDesc_state1("house01", create), delete, future, inside);
      step.getObjects().add(charlie_c);
      step.getObjects().add(floor0204_c);
      step.getObjects().add(p2f06_c);
      step.getObjects().add(house01_c);
      step.getObjects().add(floor0102_c);
      step.getObjects().add(p2f05_d);
      step.getObjects().add(floor0101_d);
      step.getObjects().add(floor0102_d);
      step.getObjects().add(house01_d);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.2. Generate step 1 of scenario sequence 5
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(businessEventMap.get("event0501"), new IndexOutOfMaxIndex(2, 7),
              new IndexOutOfMaxIndex(1, 1));
      OmgObject charlie_c = createObj(createObjDesc_state1("charlie", create), create, present, outside);
      OmgObject floor0204_c = createObj(createObjDesc_state1("floor0204", create), create, present, outside);
      OmgObject p2f06_c = createObj(createObjDesc_state1("p2f06", create), create, present, inside, charlie_c, floor0204_c);
      OmgObject house01_c = createObj(createObjDesc_state1("house01", create), create, future, outside);
      OmgObject floor0102_c = createObj(createObjDesc_state1("floor0102", create), create, future, outside, house01_c);
      OmgObject p2f05_d = createObj(createObjDesc_state1("p2f05", create), delete, future, inside, charlie_c, floor0102_c);
      OmgObject floor0101_d = createObj(createObjDesc_state1("floor0101", create), delete, future, inside, house01_c);
      OmgObject floor0102_d = createObj(createObjDesc_state1("floor0102", create), delete, future, inside, house01_c);
      OmgObject house01_d = createObj(createObjDesc_state1("house01", create), delete, future, inside);
      step.getObjects().add(charlie_c);
      step.getObjects().add(floor0204_c);
      step.getObjects().add(p2f06_c);
      step.getObjects().add(house01_c);
      step.getObjects().add(floor0102_c);
      step.getObjects().add(p2f05_d);
      step.getObjects().add(floor0101_d);
      step.getObjects().add(floor0102_d);
      step.getObjects().add(house01_d);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.3. Generate step 2 of scenario sequence 5
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(businessEventMap.get("event0502"), new IndexOutOfMaxIndex(3, 7),
              new IndexOutOfMaxIndex(1, 4));
      OmgObject charlie_c = createObj(createObjDesc_state1("charlie", create), create, past, outside);
      OmgObject floor0204_c = createObj(createObjDesc_state1("floor0204", create), create, past, outside);
      OmgObject p2f06_c = createObj(createObjDesc_state1("p2f06", create), create, past, inside, charlie_c, floor0204_c);
      OmgObject house01_c = createObj(createObjDesc_state1("house01", create), create, future, outside);
      OmgObject floor0102_c = createObj(createObjDesc_state1("floor0102", create), create, present, outside, house01_c);
      OmgObject p2f05_d = createObj(createObjDesc_state1("p2f05", create), delete, present, inside, charlie_c, floor0102_c);
      OmgObject floor0101_d = createObj(createObjDesc_state1("floor0101", create), delete, future, inside, house01_c);
      OmgObject floor0102_d = createObj(createObjDesc_state1("floor0102", create), delete, future, inside, house01_c);
      OmgObject house01_d = createObj(createObjDesc_state1("house01", create), delete, future, inside);
      step.getObjects().add(charlie_c);
      step.getObjects().add(floor0204_c);
      step.getObjects().add(p2f06_c);
      step.getObjects().add(house01_c);
      step.getObjects().add(floor0102_c);
      step.getObjects().add(p2f05_d);
      step.getObjects().add(floor0101_d);
      step.getObjects().add(floor0102_d);
      step.getObjects().add(house01_d);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.4. Generate step 3 of scenario sequence 5
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(businessEventMap.get("event0502"), new IndexOutOfMaxIndex(4, 7),
              new IndexOutOfMaxIndex(2, 4));
      OmgObject charlie_c = createObj(createObjDesc_state1("charlie", create), create, past, outside);
      OmgObject floor0204_c = createObj(createObjDesc_state1("floor0204", create), create, past, outside);
      OmgObject p2f06_c = createObj(createObjDesc_state1("p2f06", create), create, past, inside, charlie_c, floor0204_c);
      OmgObject house01_c = createObj(createObjDesc_state1("house01", create), create, present, outside);
      OmgObject floor0102_c = createObj(createObjDesc_state1("floor0102", create), create, past, outside, house01_c);
      OmgObject p2f05_d = createObj(createObjDesc_state1("p2f05", create), delete, past, inside, charlie_c, floor0102_c);
      OmgObject floor0101_d = createObj(createObjDesc_state1("floor0101", create), delete, present, inside, house01_c);
      OmgObject floor0102_d = createObj(createObjDesc_state1("floor0102", create), delete, future, inside, house01_c);
      OmgObject house01_d = createObj(createObjDesc_state1("house01", create), delete, future, inside);
      step.getObjects().add(charlie_c);
      step.getObjects().add(floor0204_c);
      step.getObjects().add(p2f06_c);
      step.getObjects().add(house01_c);
      step.getObjects().add(floor0102_c);
      step.getObjects().add(p2f05_d);
      step.getObjects().add(floor0101_d);
      step.getObjects().add(floor0102_d);
      step.getObjects().add(house01_d);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.5. Generate step 4 of scenario sequence 5
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(businessEventMap.get("event0502"), new IndexOutOfMaxIndex(5, 7),
              new IndexOutOfMaxIndex(3, 4));
      OmgObject charlie_c = createObj(createObjDesc_state1("charlie", create), create, past, outside);
      OmgObject floor0204_c = createObj(createObjDesc_state1("floor0204", create), create, past, outside);
      OmgObject p2f06_c = createObj(createObjDesc_state1("p2f06", create), create, past, inside, charlie_c, floor0204_c);
      OmgObject house01_c = createObj(createObjDesc_state1("house01", create), create, past, outside);
      OmgObject floor0102_c = createObj(createObjDesc_state1("floor0102", create), create, past, outside, house01_c);
      OmgObject p2f05_d = createObj(createObjDesc_state1("p2f05", create), delete, past, inside, charlie_c, floor0102_c);
      OmgObject floor0101_d = createObj(createObjDesc_state1("floor0101", create), delete, past, inside, house01_c);
      OmgObject floor0102_d = createObj(createObjDesc_state1("floor0102", create), delete, present, inside, house01_c);
      OmgObject house01_d = createObj(createObjDesc_state1("house01", create), delete, future, inside);
      step.getObjects().add(charlie_c);
      step.getObjects().add(floor0204_c);
      step.getObjects().add(p2f06_c);
      step.getObjects().add(house01_c);
      step.getObjects().add(floor0102_c);
      step.getObjects().add(p2f05_d);
      step.getObjects().add(floor0101_d);
      step.getObjects().add(floor0102_d);
      step.getObjects().add(house01_d);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.6. Generate step 5 of scenario sequence 5
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(businessEventMap.get("event0502"), new IndexOutOfMaxIndex(6, 7),
              new IndexOutOfMaxIndex(4, 4));
      OmgObject charlie_c = createObj(createObjDesc_state1("charlie", create), create, past, outside);
      OmgObject floor0204_c = createObj(createObjDesc_state1("floor0204", create), create, past, outside);
      OmgObject p2f06_c = createObj(createObjDesc_state1("p2f06", create), create, past, inside, charlie_c, floor0204_c);
      OmgObject house01_c = createObj(createObjDesc_state1("house01", create), create, past, outside);
      OmgObject floor0102_c = createObj(createObjDesc_state1("floor0102", create), create, past, outside, house01_c);
      OmgObject p2f05_d = createObj(createObjDesc_state1("p2f05", create), delete, past, inside, charlie_c, floor0102_c);
      OmgObject floor0101_d = createObj(createObjDesc_state1("floor0101", create), delete, past, inside, house01_c);
      OmgObject floor0102_d = createObj(createObjDesc_state1("floor0102", create), delete, past, inside, house01_c);
      OmgObject house01_d = createObj(createObjDesc_state1("house01", create), delete, present, inside);
      step.getObjects().add(charlie_c);
      step.getObjects().add(floor0204_c);
      step.getObjects().add(p2f06_c);
      step.getObjects().add(house01_c);
      step.getObjects().add(floor0102_c);
      step.getObjects().add(p2f05_d);
      step.getObjects().add(floor0101_d);
      step.getObjects().add(floor0102_d);
      step.getObjects().add(house01_d);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.7. Generate final step of scenario sequence 5
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(OmgBusinessEvent.FINAL, new IndexOutOfMaxIndex(7, 7),
              new IndexOutOfMaxIndex(1, 1));
      OmgObject charlie_c = createObj(createObjDesc_state1("charlie", create), create, past, outside);
      OmgObject floor0204_c = createObj(createObjDesc_state1("floor0204", create), create, past, outside);
      OmgObject p2f06_c = createObj(createObjDesc_state1("p2f06", create), create, past, inside, charlie_c, floor0204_c);
      OmgObject house01_c = createObj(createObjDesc_state1("house01", create), create, past, outside);
      OmgObject floor0102_c = createObj(createObjDesc_state1("floor0102", create), create, past, outside, house01_c);
      OmgObject p2f05_d = createObj(createObjDesc_state1("p2f05", create), delete, past, inside, charlie_c, floor0102_c);
      OmgObject floor0101_d = createObj(createObjDesc_state1("floor0101", create), delete, past, inside, house01_c);
      OmgObject floor0102_d = createObj(createObjDesc_state1("floor0102", create), delete, past, inside, house01_c);
      OmgObject house01_d = createObj(createObjDesc_state1("house01", create), delete, past, inside);
      step.getObjects().add(charlie_c);
      step.getObjects().add(floor0204_c);
      step.getObjects().add(p2f06_c);
      step.getObjects().add(house01_c);
      step.getObjects().add(floor0102_c);
      step.getObjects().add(p2f05_d);
      step.getObjects().add(floor0101_d);
      step.getObjects().add(floor0102_d);
      step.getObjects().add(house01_d);
      result.getScenarioSequenceSteps().add(step);
    }

    return result;
  }

  public static Set<OmgScenarioSequence> createScenarioSequences_state2() {
    Set<OmgScenarioSequence> result = new HashSet<>();

    result.add(createScenarioSequence_state2_scenario1());

    return result;
  }

  private static OmgScenarioSequence createScenarioSequence_state2_scenario1() {

    // 0. Get some more test data managed outside this test generator class
    Map<String, OmgTransitionState> transitionStateMap = createTransitionStateMap();
    Map<String, OmgBusinessEvent> businessEventMap = createBusinessEventMap();
    Map<String, OmgScenario> scenarioMap = createScenarioMap();

    // 1. Generate scenario sequence 1 ond its three steps (initial, one real step, final)
    OmgScenarioSequence result = new OmgScenarioSequence("PersonsAndHouses", scenarioMap.get("scenario1"),
            transitionStateMap.get("state2"));

    // 1.1. Generate initial step of scenario sequence 1
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(OmgBusinessEvent.INITIAL, new IndexOutOfMaxIndex(1, 3),
              new IndexOutOfMaxIndex(1, 1));
      OmgObject house01 = createObj(ObjectTestDataCreator.createObjDesc_state2("house01", create), create, future, inside);
      step.getObjects().add(house01);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.2. Generate step 1 of scenario sequence 1
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(businessEventMap.get("event0101"), new IndexOutOfMaxIndex(2, 3),
              new IndexOutOfMaxIndex(1, 1));
      OmgObject house01 = createObj(createObjDesc_state1("house01", create), create, present, inside);
      step.getObjects().add(house01);
      result.getScenarioSequenceSteps().add(step);
    }

    // 1.3. Generate final step of scenario sequence 1
    {
      OmgScenarioSequenceStep step = new OmgScenarioSequenceStep(OmgBusinessEvent.FINAL, new IndexOutOfMaxIndex(3, 3),
              new IndexOutOfMaxIndex(1, 1));
      OmgObject house01 = createObj(createObjDesc_state1("house01", create), create, past, inside);
      step.getObjects().add(house01);
      result.getScenarioSequenceSteps().add(step);
    }

    return result;
  }
}
