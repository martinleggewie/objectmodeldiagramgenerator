package org.codemaker.objectmodeldiagramgenerator.testutil;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEvent;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEventDescriptor;

import java.util.HashMap;
import java.util.Map;

public class BusinessEventTestDataCreator {

  private static final String DESCRIPTION0101 = "The first house is built with in total two floors.";
  private static final String DESCRIPTION0102 = "Anton spawns on earth.";
  private static final String DESCRIPTION0103 = "Anton moves in to first house, first floor.";
  private static final String DESCRIPTION0201 = "Berta spawns on earth.";
  private static final String DESCRIPTION0202 = "Anton and Berta marry each other.";
  private static final String DESCRIPTION0203 = "Berta moves in to first house, first floor, as well.";
  private static final String DESCRIPTION0301 = "The second house is built with in total four floors.";
  private static final String DESCRIPTION0302 = "Anton and Berta move from first house to second house, third floor.";
  private static final String DESCRIPTION0401 = "Charlie spawns on earth.";
  private static final String DESCRIPTION0402 = "Charlie moves in to first house, second floor.";
  private static final String DESCRIPTION0501 = "Charlie moves from first house to second house, fourth floor, keeps contract with first "
          + "house.";
  private static final String DESCRIPTION0502 = "The first house is destroyed. Luckily, no-one lived there anymore.";
  private static final String DESCRIPTION0000 = "This is no business event.";

  public static OmgBusinessEventDescriptor createDescriptor(String businessEventDescriptorKey) {
    OmgBusinessEventDescriptor descriptor0101 = new OmgBusinessEventDescriptor("event0101", DESCRIPTION0101, "scenario1");
    OmgBusinessEventDescriptor descriptor0102 = new OmgBusinessEventDescriptor("event0102", DESCRIPTION0102, "scenario1");
    OmgBusinessEventDescriptor descriptor0103 = new OmgBusinessEventDescriptor("event0103", DESCRIPTION0103, "scenario1");
    OmgBusinessEventDescriptor descriptor0201 = new OmgBusinessEventDescriptor("event0201", DESCRIPTION0201, "scenario2");
    OmgBusinessEventDescriptor descriptor0202 = new OmgBusinessEventDescriptor("event0202", DESCRIPTION0202, "scenario2");
    OmgBusinessEventDescriptor descriptor0203 = new OmgBusinessEventDescriptor("event0203", DESCRIPTION0203, "scenario2");
    OmgBusinessEventDescriptor descriptor0301 = new OmgBusinessEventDescriptor("event0301", DESCRIPTION0301, "scenario3");
    OmgBusinessEventDescriptor descriptor0302 = new OmgBusinessEventDescriptor("event0302", DESCRIPTION0302, "scenario3");
    OmgBusinessEventDescriptor descriptor0401 = new OmgBusinessEventDescriptor("event0401", DESCRIPTION0401, "scenario4");
    OmgBusinessEventDescriptor descriptor0402 = new OmgBusinessEventDescriptor("event0402", DESCRIPTION0402, "scenario4");
    OmgBusinessEventDescriptor descriptor0501 = new OmgBusinessEventDescriptor("event0501", DESCRIPTION0501, "scenario5");
    OmgBusinessEventDescriptor descriptor0502 = new OmgBusinessEventDescriptor("event0502", DESCRIPTION0502, "scenario5");
    OmgBusinessEventDescriptor descriptor0000 = new OmgBusinessEventDescriptor("event0000", DESCRIPTION0000, "scenario1");
    Map<String, OmgBusinessEventDescriptor> descriptorMap = new HashMap<>();
    descriptorMap.put(descriptor0101.getKey(), descriptor0101);
    descriptorMap.put(descriptor0102.getKey(), descriptor0102);
    descriptorMap.put(descriptor0103.getKey(), descriptor0103);
    descriptorMap.put(descriptor0201.getKey(), descriptor0201);
    descriptorMap.put(descriptor0202.getKey(), descriptor0202);
    descriptorMap.put(descriptor0203.getKey(), descriptor0203);
    descriptorMap.put(descriptor0301.getKey(), descriptor0301);
    descriptorMap.put(descriptor0302.getKey(), descriptor0302);
    descriptorMap.put(descriptor0401.getKey(), descriptor0401);
    descriptorMap.put(descriptor0402.getKey(), descriptor0402);
    descriptorMap.put(descriptor0501.getKey(), descriptor0501);
    descriptorMap.put(descriptor0502.getKey(), descriptor0502);
    descriptorMap.put(descriptor0000.getKey(), descriptor0000);
    return descriptorMap.get(businessEventDescriptorKey);
  }

  public static OmgBusinessEvent create(String businessEventKey) {
    OmgBusinessEvent event0101 = new OmgBusinessEvent("event0101", DESCRIPTION0101, ScenarioTestDataCreator.create("scenario1"));
    OmgBusinessEvent event0102 = new OmgBusinessEvent("event0102", DESCRIPTION0102, ScenarioTestDataCreator.create("scenario1"));
    OmgBusinessEvent event0103 = new OmgBusinessEvent("event0103", DESCRIPTION0103, ScenarioTestDataCreator.create("scenario1"));
    OmgBusinessEvent event0201 = new OmgBusinessEvent("event0201", DESCRIPTION0201, ScenarioTestDataCreator.create("scenario2"));
    OmgBusinessEvent event0202 = new OmgBusinessEvent("event0202", DESCRIPTION0202, ScenarioTestDataCreator.create("scenario2"));
    OmgBusinessEvent event0203 = new OmgBusinessEvent("event0203", DESCRIPTION0203, ScenarioTestDataCreator.create("scenario2"));
    OmgBusinessEvent event0301 = new OmgBusinessEvent("event0301", DESCRIPTION0301, ScenarioTestDataCreator.create("scenario3"));
    OmgBusinessEvent event0302 = new OmgBusinessEvent("event0302", DESCRIPTION0302, ScenarioTestDataCreator.create("scenario3"));
    OmgBusinessEvent event0401 = new OmgBusinessEvent("event0401", DESCRIPTION0401, ScenarioTestDataCreator.create("scenario4"));
    OmgBusinessEvent event0402 = new OmgBusinessEvent("event0402", DESCRIPTION0402, ScenarioTestDataCreator.create("scenario4"));
    OmgBusinessEvent event0501 = new OmgBusinessEvent("event0501", DESCRIPTION0501, ScenarioTestDataCreator.create("scenario5"));
    OmgBusinessEvent event0502 = new OmgBusinessEvent("event0502", DESCRIPTION0502, ScenarioTestDataCreator.create("scenario5"));
    OmgBusinessEvent event0000 = new OmgBusinessEvent("event0000", DESCRIPTION0000, ScenarioTestDataCreator.create("scenario1"));
    Map<String, OmgBusinessEvent> businessEventMap = new HashMap<>();
    businessEventMap.put(event0101.getKey(), event0101);
    businessEventMap.put(event0102.getKey(), event0102);
    businessEventMap.put(event0103.getKey(), event0103);
    businessEventMap.put(event0201.getKey(), event0201);
    businessEventMap.put(event0202.getKey(), event0202);
    businessEventMap.put(event0203.getKey(), event0203);
    businessEventMap.put(event0301.getKey(), event0301);
    businessEventMap.put(event0302.getKey(), event0302);
    businessEventMap.put(event0401.getKey(), event0401);
    businessEventMap.put(event0402.getKey(), event0402);
    businessEventMap.put(event0501.getKey(), event0501);
    businessEventMap.put(event0502.getKey(), event0502);
    businessEventMap.put(event0000.getKey(), event0000);
    return businessEventMap.get(businessEventKey);
  }

  public static Map<String, OmgBusinessEvent> createBusinessEventMap() {
    OmgBusinessEvent event0101 = create("event0101");
    OmgBusinessEvent event0102 = create("event0102");
    OmgBusinessEvent event0103 = create("event0103");
    OmgBusinessEvent event0201 = create("event0201");
    OmgBusinessEvent event0202 = create("event0202");
    OmgBusinessEvent event0203 = create("event0203");
    OmgBusinessEvent event0301 = create("event0301");
    OmgBusinessEvent event0302 = create("event0302");
    OmgBusinessEvent event0401 = create("event0401");
    OmgBusinessEvent event0402 = create("event0402");
    OmgBusinessEvent event0501 = create("event0501");
    OmgBusinessEvent event0502 = create("event0502");

    Map<String, OmgBusinessEvent> result = new HashMap<>();
    result.put(event0101.getKey(), event0101);
    result.put(event0102.getKey(), event0102);
    result.put(event0103.getKey(), event0103);
    result.put(event0201.getKey(), event0201);
    result.put(event0202.getKey(), event0202);
    result.put(event0203.getKey(), event0203);
    result.put(event0301.getKey(), event0301);
    result.put(event0302.getKey(), event0302);
    result.put(event0401.getKey(), event0401);
    result.put(event0402.getKey(), event0402);
    result.put(event0501.getKey(), event0501);
    result.put(event0502.getKey(), event0502);
    return result;
  }
}
