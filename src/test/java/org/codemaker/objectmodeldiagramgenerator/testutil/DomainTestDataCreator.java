package org.codemaker.objectmodeldiagramgenerator.testutil;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgDomain;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgDomainDescriptor;

import java.util.HashMap;
import java.util.Map;

public class DomainTestDataCreator {
  public static Map<String, OmgDomainDescriptor> createDomainDescriptorMap() {
    OmgDomainDescriptor domainDescriptor1 = new OmgDomainDescriptor("housedomain", "The House Domain");
    OmgDomainDescriptor domainDescriptor2 = new OmgDomainDescriptor("persondomain", "The Person Domain");
    Map<String, OmgDomainDescriptor> result = new HashMap<>();
    result.put(domainDescriptor1.getKey(), domainDescriptor1);
    result.put(domainDescriptor2.getKey(), domainDescriptor2);
    return result;
  }

  public static Map<String, OmgDomain> createDomainMap() {
    Map<String, OmgDomain> result = new HashMap<>();
    Map<String, OmgDomainDescriptor> domainDescriptorMap = createDomainDescriptorMap();
    for (OmgDomainDescriptor domainDescriptor : domainDescriptorMap.values()) {
      OmgDomain domain = new OmgDomain(domainDescriptor.getKey(), domainDescriptor.getDisplayName());
      result.put(domain.getKey(), domain);
    }
    return result;
  }
}
