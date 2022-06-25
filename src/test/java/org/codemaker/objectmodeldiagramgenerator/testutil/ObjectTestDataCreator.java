package org.codemaker.objectmodeldiagramgenerator.testutil;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgAction;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgEra;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObject;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObjectDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgOrigin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgAction.create;
import static org.codemaker.objectmodeldiagramgenerator.domain.repositories.DescriptorRepository.PROPERTYVALUE_NOTSET;
import static org.codemaker.objectmodeldiagramgenerator.testutil.ClassTestDataCreator.createClassMap;

public class ObjectTestDataCreator {
  public static OmgObjectDescriptor createObjDesc_state1(String objectDescriptorKey, OmgAction action) {
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

    if (action.equals(create)) {
      return objectDescriptorMap_create.get(objectDescriptorKey);
    } else {
      return objectDescriptorMap_delete.get(objectDescriptorKey);
    }
  }

  public static OmgObjectDescriptor createObjDesc_state2(String objectDescriptorKey, OmgAction action) {
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

    if (action.equals(create)) {
      return objectDescriptorMap_create.get(objectDescriptorKey);
    } else {
      return objectDescriptorMap_delete.get(objectDescriptorKey);
    }
  }

  public static OmgObject createObj(OmgObjectDescriptor objectDescriptor, OmgAction action, OmgEra era, OmgOrigin origin,
                                    OmgObject... dependeeObjects) {
    OmgObject result = new OmgObject(objectDescriptor.getKey(), createClassMap().get(objectDescriptor.getClassKey()), action, era, origin);
    result.getPropertyMap().putAll(objectDescriptor.getPropertyMap());
    result.getDependeeObjects().addAll(new HashSet<>(Arrays.asList(dependeeObjects)));
    return result;
  }
}
