package org.codemaker.objectmodeldiagramgenerator.testutil;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgClassDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgDomainDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObjectDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioSequenceDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioSequenceStep.Action;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioSequenceStepDescriptor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.codemaker.objectmodeldiagramgenerator.domain.repositories.DescriptorRepository.PROPERTYVALUE_NOTSET;

public class ScenarioSequenceTestDataCreator {

  public static OmgDomainDescriptor createDomainDescriptor(String domainDescriptorKey) {
    return createDomainDescriptorMap().get(domainDescriptorKey);
  }

  public static Map<String, OmgDomainDescriptor> createDomainDescriptorMap() {
    OmgDomainDescriptor domainDescriptor1 = new OmgDomainDescriptor("housedomain", "The House Domain");
    OmgDomainDescriptor domainDescriptor2 = new OmgDomainDescriptor("persondomain", "The Person Domain");
    Map<String, OmgDomainDescriptor> result = new HashMap<>();
    result.put(domainDescriptor1.getKey(), domainDescriptor1);
    result.put(domainDescriptor2.getKey(), domainDescriptor2);
    return result;
  }

  public static OmgClassDescriptor createClassDescriptor(String classDescriptorKey) {
    return createClassDescriptorMap().get(classDescriptorKey);
  }

  public static Map<String, OmgClassDescriptor> createClassDescriptorMap() {
    OmgClassDescriptor classDescriptor1 = new OmgClassDescriptor("houseclass", "House", "housedomain");
    OmgClassDescriptor classDescriptor2 = new OmgClassDescriptor("floorclass", "Floor", "housedomain");
    OmgClassDescriptor classDescriptor3 = new OmgClassDescriptor("personclass", "Person", "persondomain");
    OmgClassDescriptor classDescriptor4 = new OmgClassDescriptor("p2fclass", "Person-to-Floor Relation", "persondomain");
    OmgClassDescriptor classDescriptor5 = new OmgClassDescriptor("p2pclass", "Person-to-Person Relation", "persondomain");

    Map<String, OmgClassDescriptor> result = new HashMap<>();
    result.put(classDescriptor1.getKey(), classDescriptor1);
    result.put(classDescriptor2.getKey(), classDescriptor2);
    result.put(classDescriptor3.getKey(), classDescriptor3);
    result.put(classDescriptor4.getKey(), classDescriptor4);
    result.put(classDescriptor5.getKey(), classDescriptor5);
    return result;
  }


  public static OmgObjectDescriptor createObjectDescriptor_state1(String objectDescriptorKey, Action action) {
    // The houses
    OmgObjectDescriptor objectDescriptorHouse01_create = new OmgObjectDescriptor("housedomain", "houseclass", "house01");
    objectDescriptorHouse01_create.getPropertyMap().put("name", "The First House");
    objectDescriptorHouse01_create.getPropertyMap().put("address", "First Street 1");

    OmgObjectDescriptor objectDescriptorHouse01_delete = new OmgObjectDescriptor("housedomain", "houseclass", "house01");
    objectDescriptorHouse01_delete.getPropertyMap().put("name", PROPERTYVALUE_NOTSET);
    objectDescriptorHouse01_delete.getPropertyMap().put("address", PROPERTYVALUE_NOTSET);

    OmgObjectDescriptor objectDescriptorHouse02_create = new OmgObjectDescriptor("housedomain", "houseclass", "house02");
    objectDescriptorHouse02_create.getPropertyMap().put("name", "The Second House");
    objectDescriptorHouse02_create.getPropertyMap().put("address", "Second Street 2");

    // The floors
    OmgObjectDescriptor objectDescriptorFloor0101_create = new OmgObjectDescriptor("housedomain", "floorclass", "floor0101");
    objectDescriptorFloor0101_create.getPropertyMap().put("name", "The First Floor");
    objectDescriptorFloor0101_create.getPropertyMap().put("house_fk", "house01");
    objectDescriptorFloor0101_create.getDependeeKeys().add("house01");

    OmgObjectDescriptor objectDescriptorFloor0101_delete = new OmgObjectDescriptor("housedomain", "floorclass", "floor0101");
    objectDescriptorFloor0101_delete.getPropertyMap().put("name", PROPERTYVALUE_NOTSET);
    objectDescriptorFloor0101_delete.getPropertyMap().put("house_fk", PROPERTYVALUE_NOTSET);

    OmgObjectDescriptor objectDescriptorFloor0102_create = new OmgObjectDescriptor("housedomain", "floorclass", "floor0102");
    objectDescriptorFloor0102_create.getPropertyMap().put("name", "The Second Floor");
    objectDescriptorFloor0102_create.getPropertyMap().put("house_fk", "house01");
    objectDescriptorFloor0102_create.getDependeeKeys().add("house01");

    OmgObjectDescriptor objectDescriptorFloor0102_delete = new OmgObjectDescriptor("housedomain", "floorclass", "floor0102");
    objectDescriptorFloor0102_delete.getPropertyMap().put("name", PROPERTYVALUE_NOTSET);
    objectDescriptorFloor0102_delete.getPropertyMap().put("house_fk", PROPERTYVALUE_NOTSET);

    OmgObjectDescriptor objectDescriptorFloor0201_create = new OmgObjectDescriptor("housedomain", "floorclass", "floor0201");
    objectDescriptorFloor0201_create.getPropertyMap().put("name", "The First Floor");
    objectDescriptorFloor0201_create.getPropertyMap().put("house_fk", "house02");
    objectDescriptorFloor0201_create.getDependeeKeys().add("house02");

    OmgObjectDescriptor objectDescriptorFloor0202_create = new OmgObjectDescriptor("housedomain", "floorclass", "floor0202");
    objectDescriptorFloor0202_create.getPropertyMap().put("name", "The Second Floor");
    objectDescriptorFloor0202_create.getPropertyMap().put("house_fk", "house02");
    objectDescriptorFloor0202_create.getDependeeKeys().add("house02");

    OmgObjectDescriptor objectDescriptorFloor0203_create = new OmgObjectDescriptor("housedomain", "floorclass", "floor0203");
    objectDescriptorFloor0203_create.getPropertyMap().put("name", "The Third Floor");
    objectDescriptorFloor0203_create.getPropertyMap().put("house_fk", "house02");
    objectDescriptorFloor0203_create.getDependeeKeys().add("house02");

    OmgObjectDescriptor objectDescriptorFloor0204_create = new OmgObjectDescriptor("housedomain", "floorclass", "floor0204");
    objectDescriptorFloor0204_create.getPropertyMap().put("name", "The Fourth Floor");
    objectDescriptorFloor0204_create.getPropertyMap().put("house_fk", "house02");
    objectDescriptorFloor0204_create.getDependeeKeys().add("house02");

    // The persons
    OmgObjectDescriptor objectDescriptorAnton_create = new OmgObjectDescriptor("persondomain", "personclass", "anton");
    objectDescriptorAnton_create.getPropertyMap().put("name", "Anton A");
    objectDescriptorAnton_create.getPropertyMap().put("age", "23");

    OmgObjectDescriptor objectDescriptorBerta_create = new OmgObjectDescriptor("persondomain", "personclass", "berta");
    objectDescriptorBerta_create.getPropertyMap().put("name", "Berta B");
    objectDescriptorBerta_create.getPropertyMap().put("age", "34");

    OmgObjectDescriptor objectDescriptorCharl_create = new OmgObjectDescriptor("persondomain", "personclass", "charlie");
    objectDescriptorCharl_create.getPropertyMap().put("name", "Charlie C");
    objectDescriptorCharl_create.getPropertyMap().put("age", "45");

    // The person-to-floor relations
    OmgObjectDescriptor objectDescriptorP2F01_create = new OmgObjectDescriptor("persondomain", "p2fclass", "p2f01");
    objectDescriptorP2F01_create.getPropertyMap().put("type", "tenant");
    objectDescriptorP2F01_create.getPropertyMap().put("person_fk", "anton");
    objectDescriptorP2F01_create.getPropertyMap().put("floor_fk", "floor0101");
    objectDescriptorP2F01_create.getDependeeKeys().add("anton");
    objectDescriptorP2F01_create.getDependeeKeys().add("floor0101");

    OmgObjectDescriptor objectDescriptorP2F01_delete = new OmgObjectDescriptor("persondomain", "p2fclass", "p2f01");
    objectDescriptorP2F01_delete.getPropertyMap().put("type", PROPERTYVALUE_NOTSET);
    objectDescriptorP2F01_delete.getPropertyMap().put("person_fk", PROPERTYVALUE_NOTSET);
    objectDescriptorP2F01_delete.getPropertyMap().put("floor_fk", PROPERTYVALUE_NOTSET);

    OmgObjectDescriptor objectDescriptorP2F02_create = new OmgObjectDescriptor("persondomain", "p2fclass", "p2f02");
    objectDescriptorP2F02_create.getPropertyMap().put("type", "tenant");
    objectDescriptorP2F02_create.getPropertyMap().put("person_fk", "berta");
    objectDescriptorP2F02_create.getPropertyMap().put("floor_fk", "floor0101");
    objectDescriptorP2F02_create.getDependeeKeys().add("berta");
    objectDescriptorP2F02_create.getDependeeKeys().add("floor0101");

    OmgObjectDescriptor objectDescriptorP2F02_delete = new OmgObjectDescriptor("persondomain", "p2fclass", "p2f02");
    objectDescriptorP2F02_delete.getPropertyMap().put("type", PROPERTYVALUE_NOTSET);
    objectDescriptorP2F02_delete.getPropertyMap().put("person_fk", PROPERTYVALUE_NOTSET);
    objectDescriptorP2F02_delete.getPropertyMap().put("floor_fk", PROPERTYVALUE_NOTSET);

    OmgObjectDescriptor objectDescriptorP2F03_create = new OmgObjectDescriptor("persondomain", "p2fclass", "p2f03");
    objectDescriptorP2F03_create.getPropertyMap().put("type", "tenant");
    objectDescriptorP2F03_create.getPropertyMap().put("person_fk", "anton");
    objectDescriptorP2F03_create.getPropertyMap().put("floor_fk", "floor0203");
    objectDescriptorP2F03_create.getDependeeKeys().add("anton");
    objectDescriptorP2F03_create.getDependeeKeys().add("floor0203");

    OmgObjectDescriptor objectDescriptorP2F04_create = new OmgObjectDescriptor("persondomain", "p2fclass", "p2f04");
    objectDescriptorP2F04_create.getPropertyMap().put("type", "tenant");
    objectDescriptorP2F04_create.getPropertyMap().put("person_fk", "berta");
    objectDescriptorP2F04_create.getPropertyMap().put("floor_fk", "floor0203");
    objectDescriptorP2F04_create.getDependeeKeys().add("berta");
    objectDescriptorP2F04_create.getDependeeKeys().add("floor0203");

    OmgObjectDescriptor objectDescriptorP2F05_create = new OmgObjectDescriptor("persondomain", "p2fclass", "p2f05");
    objectDescriptorP2F05_create.getPropertyMap().put("type", "tenant");
    objectDescriptorP2F05_create.getPropertyMap().put("person_fk", "charlie");
    objectDescriptorP2F05_create.getPropertyMap().put("floor_fk", "floor0102");
    objectDescriptorP2F05_create.getDependeeKeys().add("charlie");
    objectDescriptorP2F05_create.getDependeeKeys().add("floor0102");

    OmgObjectDescriptor objectDescriptorP2F05_delete = new OmgObjectDescriptor("persondomain", "p2fclass", "p2f05");
    objectDescriptorP2F05_delete.getPropertyMap().put("type", PROPERTYVALUE_NOTSET);
    objectDescriptorP2F05_delete.getPropertyMap().put("person_fk", PROPERTYVALUE_NOTSET);
    objectDescriptorP2F05_delete.getPropertyMap().put("floor_fk", PROPERTYVALUE_NOTSET);

    OmgObjectDescriptor objectDescriptorP2F06_create = new OmgObjectDescriptor("persondomain", "p2fclass", "p2f06");
    objectDescriptorP2F06_create.getPropertyMap().put("type", "tenant");
    objectDescriptorP2F06_create.getPropertyMap().put("person_fk", "charlie");
    objectDescriptorP2F06_create.getPropertyMap().put("floor_fk", "floor0204");
    objectDescriptorP2F06_create.getDependeeKeys().add("charlie");
    objectDescriptorP2F06_create.getDependeeKeys().add("floor0204");

    // The person-to-person relations
    OmgObjectDescriptor objectDescriptorP2P01_create = new OmgObjectDescriptor("persondomain", "p2pclass", "p2p01");
    objectDescriptorP2P01_create.getPropertyMap().put("type", "marriage");
    objectDescriptorP2P01_create.getPropertyMap().put("person_fk", "(anton, berta)");
    objectDescriptorP2P01_create.getDependeeKeys().add("anton");
    objectDescriptorP2P01_create.getDependeeKeys().add("berta");

    // Store everything in the corresponding map

    // First the map which contains the objects which will be created
    Map<String, OmgObjectDescriptor> objectDescriptorMap_create = new HashMap<>();
    objectDescriptorMap_create.put(objectDescriptorHouse01_create.getKey(), objectDescriptorHouse01_create);
    objectDescriptorMap_create.put(objectDescriptorHouse02_create.getKey(), objectDescriptorHouse02_create);
    objectDescriptorMap_create.put(objectDescriptorFloor0101_create.getKey(), objectDescriptorFloor0101_create);
    objectDescriptorMap_create.put(objectDescriptorFloor0102_create.getKey(), objectDescriptorFloor0102_create);
    objectDescriptorMap_create.put(objectDescriptorFloor0201_create.getKey(), objectDescriptorFloor0201_create);
    objectDescriptorMap_create.put(objectDescriptorFloor0202_create.getKey(), objectDescriptorFloor0202_create);
    objectDescriptorMap_create.put(objectDescriptorFloor0203_create.getKey(), objectDescriptorFloor0203_create);
    objectDescriptorMap_create.put(objectDescriptorFloor0204_create.getKey(), objectDescriptorFloor0204_create);
    objectDescriptorMap_create.put(objectDescriptorAnton_create.getKey(), objectDescriptorAnton_create);
    objectDescriptorMap_create.put(objectDescriptorBerta_create.getKey(), objectDescriptorBerta_create);
    objectDescriptorMap_create.put(objectDescriptorCharl_create.getKey(), objectDescriptorCharl_create);
    objectDescriptorMap_create.put(objectDescriptorP2F01_create.getKey(), objectDescriptorP2F01_create);
    objectDescriptorMap_create.put(objectDescriptorP2F02_create.getKey(), objectDescriptorP2F02_create);
    objectDescriptorMap_create.put(objectDescriptorP2F03_create.getKey(), objectDescriptorP2F03_create);
    objectDescriptorMap_create.put(objectDescriptorP2F04_create.getKey(), objectDescriptorP2F04_create);
    objectDescriptorMap_create.put(objectDescriptorP2F05_create.getKey(), objectDescriptorP2F05_create);
    objectDescriptorMap_create.put(objectDescriptorP2F06_create.getKey(), objectDescriptorP2F06_create);
    objectDescriptorMap_create.put(objectDescriptorP2P01_create.getKey(), objectDescriptorP2P01_create);

    // And then the map which contains the objects which will be deleted
    Map<String, OmgObjectDescriptor> objectDescriptorMap_delete = new HashMap<>();
    objectDescriptorMap_delete.put(objectDescriptorHouse01_delete.getKey(), objectDescriptorHouse01_delete);
    objectDescriptorMap_delete.put(objectDescriptorFloor0101_delete.getKey(), objectDescriptorFloor0101_delete);
    objectDescriptorMap_delete.put(objectDescriptorFloor0102_delete.getKey(), objectDescriptorFloor0102_delete);
    objectDescriptorMap_delete.put(objectDescriptorP2F01_delete.getKey(), objectDescriptorP2F01_delete);
    objectDescriptorMap_delete.put(objectDescriptorP2F02_delete.getKey(), objectDescriptorP2F02_delete);
    objectDescriptorMap_delete.put(objectDescriptorP2F05_delete.getKey(), objectDescriptorP2F05_delete);

    if (action.equals(Action.create)) {
      return objectDescriptorMap_create.get(objectDescriptorKey);
    } else {
      return objectDescriptorMap_delete.get(objectDescriptorKey);
    }
  }

  public static OmgObjectDescriptor createObjectDescriptor_state2(String objectDescriptorKey, Action action) {
    // The houses
    OmgObjectDescriptor objectDescriptorHouse01_create = new OmgObjectDescriptor("housedomain", "houseclass", "house01");
    objectDescriptorHouse01_create.getPropertyMap().put("name", "The First House");
    objectDescriptorHouse01_create.getPropertyMap().put("address", "First Street 1");

    // Store everything in the corresponding map

    // First the map which contains the objects which will be created
    Map<String, OmgObjectDescriptor> objectDescriptorMap_create = new HashMap<>();
    objectDescriptorMap_create.put(objectDescriptorHouse01_create.getKey(), objectDescriptorHouse01_create);

    // And then the map which contains the objects which will be deleted
    Map<String, OmgObjectDescriptor> objectDescriptorMap_delete = new HashMap<>();

    if (action.equals(Action.create)) {
      return objectDescriptorMap_create.get(objectDescriptorKey);
    } else {
      return objectDescriptorMap_delete.get(objectDescriptorKey);
    }
  }

  public static Set<OmgScenarioSequenceDescriptor> createScenarioSequenceDescriptors_state1() {
    Set<OmgScenarioSequenceDescriptor> result = new HashSet<>();

    // 1. Create the first scenario sequence descriptor
    OmgScenarioSequenceDescriptor scenSeqDesc1 = new OmgScenarioSequenceDescriptor("state1", "PersonsAndHouses");
    scenSeqDesc1.getDomainDescriptors().addAll(createDomainDescriptorMap().values());
    scenSeqDesc1.getClassDescriptors().addAll(createClassDescriptorMap().values());
    OmgScenarioSequenceStepDescriptor scenSeqStepDesc11 = new OmgScenarioSequenceStepDescriptor("event0101", Action.create.name());
    scenSeqStepDesc11.getObjectDescriptors().add(createObjectDescriptor_state1("house01", Action.create));
    OmgScenarioSequenceStepDescriptor scenSeqStepDesc12 = new OmgScenarioSequenceStepDescriptor("event0101", Action.create.name());
    scenSeqStepDesc12.getObjectDescriptors().add(createObjectDescriptor_state1("floor0101", Action.create));
    OmgScenarioSequenceStepDescriptor scenSeqStepDesc13 = new OmgScenarioSequenceStepDescriptor("event0101", Action.create.name());
    scenSeqStepDesc13.getObjectDescriptors().add(createObjectDescriptor_state1("floor0102", Action.create));
    OmgScenarioSequenceStepDescriptor scenSeqStepDesc14 = new OmgScenarioSequenceStepDescriptor("event0102", Action.create.name());
    scenSeqStepDesc14.getObjectDescriptors().add(createObjectDescriptor_state1("anton", Action.create));
    OmgScenarioSequenceStepDescriptor scenSeqStepDesc15 = new OmgScenarioSequenceStepDescriptor("event0103", Action.create.name());
    scenSeqStepDesc15.getObjectDescriptors().add(createObjectDescriptor_state1("p2f01", Action.create));
    scenSeqDesc1.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc11);
    scenSeqDesc1.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc12);
    scenSeqDesc1.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc13);
    scenSeqDesc1.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc14);
    scenSeqDesc1.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc15);

    // 2. Create the second scenario sequence descriptor
    OmgScenarioSequenceDescriptor scenSeqDesc2 = new OmgScenarioSequenceDescriptor("state1", "PersonsAndHouses");
    scenSeqDesc2.getDomainDescriptors().addAll(createDomainDescriptorMap().values());
    scenSeqDesc2.getClassDescriptors().addAll(createClassDescriptorMap().values());
    OmgScenarioSequenceStepDescriptor scenSeqStepDesc21 = new OmgScenarioSequenceStepDescriptor("event0201", Action.create.name());
    scenSeqStepDesc21.getObjectDescriptors().add(createObjectDescriptor_state1("berta", Action.create));
    OmgScenarioSequenceStepDescriptor scenSeqStepDesc22 = new OmgScenarioSequenceStepDescriptor("event0202", Action.create.name());
    scenSeqStepDesc22.getObjectDescriptors().add(createObjectDescriptor_state1("p2p01", Action.create));
    OmgScenarioSequenceStepDescriptor scenSeqStepDesc23 = new OmgScenarioSequenceStepDescriptor("event0203", Action.create.name());
    scenSeqStepDesc23.getObjectDescriptors().add(createObjectDescriptor_state1("p2f02", Action.create));
    scenSeqDesc2.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc21);
    scenSeqDesc2.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc22);
    scenSeqDesc2.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc23);

    // 3. Create the third scenario sequence descriptor
    OmgScenarioSequenceDescriptor scenSeqDesc3 = new OmgScenarioSequenceDescriptor("state1", "PersonsAndHouses");
    scenSeqDesc3.getDomainDescriptors().addAll(createDomainDescriptorMap().values());
    scenSeqDesc3.getClassDescriptors().addAll(createClassDescriptorMap().values());
    OmgScenarioSequenceStepDescriptor scenSeqStepDesc31 = new OmgScenarioSequenceStepDescriptor("event0301", Action.create.name());
    scenSeqStepDesc31.getObjectDescriptors().add(createObjectDescriptor_state1("house02", Action.create));
    OmgScenarioSequenceStepDescriptor scenSeqStepDesc32 = new OmgScenarioSequenceStepDescriptor("event0301", Action.create.name());
    scenSeqStepDesc32.getObjectDescriptors().add(createObjectDescriptor_state1("floor0201", Action.create));
    OmgScenarioSequenceStepDescriptor scenSeqStepDesc33 = new OmgScenarioSequenceStepDescriptor("event0301", Action.create.name());
    scenSeqStepDesc33.getObjectDescriptors().add(createObjectDescriptor_state1("floor0202", Action.create));
    OmgScenarioSequenceStepDescriptor scenSeqStepDesc34 = new OmgScenarioSequenceStepDescriptor("event0301", Action.create.name());
    scenSeqStepDesc34.getObjectDescriptors().add(createObjectDescriptor_state1("floor0203", Action.create));
    OmgScenarioSequenceStepDescriptor scenSeqStepDesc35 = new OmgScenarioSequenceStepDescriptor("event0301", Action.create.name());
    scenSeqStepDesc35.getObjectDescriptors().add(createObjectDescriptor_state1("floor0204", Action.create));
    OmgScenarioSequenceStepDescriptor scenSeqStepDesc36 = new OmgScenarioSequenceStepDescriptor("event0302", Action.create.name());
    scenSeqStepDesc36.getObjectDescriptors().add(createObjectDescriptor_state1("p2f03", Action.create));
    OmgScenarioSequenceStepDescriptor scenSeqStepDesc37 = new OmgScenarioSequenceStepDescriptor("event0302", Action.create.name());
    scenSeqStepDesc37.getObjectDescriptors().add(createObjectDescriptor_state1("p2f04", Action.create));
    OmgScenarioSequenceStepDescriptor scenSeqStepDesc38 = new OmgScenarioSequenceStepDescriptor("event0302", Action.delete.name());
    scenSeqStepDesc38.getObjectDescriptors().add(createObjectDescriptor_state1("p2f01", Action.delete));
    OmgScenarioSequenceStepDescriptor scenSeqStepDesc39 = new OmgScenarioSequenceStepDescriptor("event0302", Action.delete.name());
    scenSeqStepDesc39.getObjectDescriptors().add(createObjectDescriptor_state1("p2f02", Action.delete));
    scenSeqDesc3.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc31);
    scenSeqDesc3.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc32);
    scenSeqDesc3.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc33);
    scenSeqDesc3.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc34);
    scenSeqDesc3.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc35);
    scenSeqDesc3.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc36);
    scenSeqDesc3.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc37);
    scenSeqDesc3.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc38);
    scenSeqDesc3.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc39);

    // 4. Create the fourth scenario sequence descriptor
    OmgScenarioSequenceDescriptor scenSeqDesc4 = new OmgScenarioSequenceDescriptor("state1", "PersonsAndHouses");
    scenSeqDesc4.getDomainDescriptors().addAll(createDomainDescriptorMap().values());
    scenSeqDesc4.getClassDescriptors().addAll(createClassDescriptorMap().values());
    OmgScenarioSequenceStepDescriptor scenSeqStepDesc41 = new OmgScenarioSequenceStepDescriptor("event0401", Action.create.name());
    scenSeqStepDesc41.getObjectDescriptors().add(createObjectDescriptor_state1("charlie", Action.create));
    OmgScenarioSequenceStepDescriptor scenSeqStepDesc42 = new OmgScenarioSequenceStepDescriptor("event0402", Action.create.name());
    scenSeqStepDesc42.getObjectDescriptors().add(createObjectDescriptor_state1("p2f05", Action.create));
    scenSeqDesc4.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc41);
    scenSeqDesc4.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc42);

    // 5. Create the fifth scenario sequence descriptor
    OmgScenarioSequenceDescriptor scenSeqDesc5 = new OmgScenarioSequenceDescriptor("state1", "PersonsAndHouses");
    scenSeqDesc5.getDomainDescriptors().addAll(createDomainDescriptorMap().values());
    scenSeqDesc5.getClassDescriptors().addAll(createClassDescriptorMap().values());
    OmgScenarioSequenceStepDescriptor scenSeqStepDesc51 = new OmgScenarioSequenceStepDescriptor("event0501", Action.create.name());
    scenSeqStepDesc51.getObjectDescriptors().add(createObjectDescriptor_state1("p2f06", Action.create));
    OmgScenarioSequenceStepDescriptor scenSeqStepDesc52 = new OmgScenarioSequenceStepDescriptor("event0502", Action.delete.name());
    scenSeqStepDesc52.getObjectDescriptors().add(createObjectDescriptor_state1("p2f05", Action.delete));
    OmgScenarioSequenceStepDescriptor scenSeqStepDesc53 = new OmgScenarioSequenceStepDescriptor("event0502", Action.delete.name());
    scenSeqStepDesc53.getObjectDescriptors().add(createObjectDescriptor_state1("floor0101", Action.delete));
    OmgScenarioSequenceStepDescriptor scenSeqStepDesc54 = new OmgScenarioSequenceStepDescriptor("event0502", Action.delete.name());
    scenSeqStepDesc54.getObjectDescriptors().add(createObjectDescriptor_state1("floor0102", Action.delete));
    OmgScenarioSequenceStepDescriptor scenSeqStepDesc55 = new OmgScenarioSequenceStepDescriptor("event0502", Action.delete.name());
    scenSeqStepDesc55.getObjectDescriptors().add(createObjectDescriptor_state1("house01", Action.delete));
    scenSeqDesc5.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc51);
    scenSeqDesc5.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc52);
    scenSeqDesc5.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc53);
    scenSeqDesc5.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc54);
    scenSeqDesc5.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc55);

    // And now finish it: Add the five scenario sequence descriptors to the result
    result.add(scenSeqDesc1);
    result.add(scenSeqDesc2);
    result.add(scenSeqDesc3);
    result.add(scenSeqDesc4);
    result.add(scenSeqDesc5);

    return result;
  }

  public static Set<OmgScenarioSequenceDescriptor> createScenarioSequenceDescriptors_state2() {
    Set<OmgScenarioSequenceDescriptor> result = new HashSet<>();

    // 1. Create the first scenario sequence descriptor
    OmgScenarioSequenceDescriptor scenSeqDesc1 = new OmgScenarioSequenceDescriptor("state2", "PersonsAndHouses");
    scenSeqDesc1.getDomainDescriptors().addAll(createDomainDescriptorMap().values());
    scenSeqDesc1.getClassDescriptors().addAll(createClassDescriptorMap().values());
    OmgScenarioSequenceStepDescriptor scenSeqStepDesc11 = new OmgScenarioSequenceStepDescriptor("event0101", Action.create.name());
    scenSeqStepDesc11.getObjectDescriptors().add(createObjectDescriptor_state2("house01", Action.create));
    scenSeqDesc1.getScenarioSequenceStepDescriptors().add(scenSeqStepDesc11);

    // And now finish it: Add the scenario sequence descriptor to the result
    result.add(scenSeqDesc1);

    return result;
  }
}
