package org.codemaker.objectmodeldiagramgenerator.testutil;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgClass;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgClassDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgDomain;

import java.util.HashMap;
import java.util.Map;

import static org.codemaker.objectmodeldiagramgenerator.testutil.DomainTestDataCreator.createDomainMap;

public class ClassTestDataCreator {

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

  public static Map<String, OmgClass> createClassMap() {
    Map<String, OmgClass> result = new HashMap<>();
    Map<String, OmgClassDescriptor> classDescriptorMap = createClassDescriptorMap();
    Map<String, OmgDomain> domainMap = createDomainMap();
    for (OmgClassDescriptor classDescriptor : classDescriptorMap.values()) {
      OmgDomain domain = domainMap.get(classDescriptor.getDomainKey());
      OmgClass clazz = new OmgClass(classDescriptor.getKey(), classDescriptor.getDisplayName(), domain);
      result.put(clazz.getKey(), clazz);
    }
    return result;
  }
}
