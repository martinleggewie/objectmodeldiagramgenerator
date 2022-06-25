package org.codemaker.objectmodeldiagramgenerator.testutil;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEvent;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEventDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenario;

import java.util.HashMap;
import java.util.Map;

import static org.codemaker.objectmodeldiagramgenerator.testutil.ScenarioTestDataCreator.createScenarioMap;

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

  public static Map<String, OmgBusinessEventDescriptor> createBusinessEventDescriptorMap() {
    Map<String, OmgBusinessEventDescriptor> result = new HashMap<>();
    result.put("event0101", new OmgBusinessEventDescriptor("event0101", DESCRIPTION0101, "scenario1"));
    result.put("event0102", new OmgBusinessEventDescriptor("event0102", DESCRIPTION0102, "scenario1"));
    result.put("event0103", new OmgBusinessEventDescriptor("event0103", DESCRIPTION0103, "scenario1"));
    result.put("event0201", new OmgBusinessEventDescriptor("event0201", DESCRIPTION0201, "scenario2"));
    result.put("event0202", new OmgBusinessEventDescriptor("event0202", DESCRIPTION0202, "scenario2"));
    result.put("event0203", new OmgBusinessEventDescriptor("event0203", DESCRIPTION0203, "scenario2"));
    result.put("event0301", new OmgBusinessEventDescriptor("event0301", DESCRIPTION0301, "scenario3"));
    result.put("event0302", new OmgBusinessEventDescriptor("event0302", DESCRIPTION0302, "scenario3"));
    result.put("event0401", new OmgBusinessEventDescriptor("event0401", DESCRIPTION0401, "scenario4"));
    result.put("event0402", new OmgBusinessEventDescriptor("event0402", DESCRIPTION0402, "scenario4"));
    result.put("event0501", new OmgBusinessEventDescriptor("event0501", DESCRIPTION0501, "scenario5"));
    result.put("event0502", new OmgBusinessEventDescriptor("event0502", DESCRIPTION0502, "scenario5"));
    return result;
  }

  public static Map<String, OmgBusinessEvent> createBusinessEventMap() {
    Map<String, OmgScenario> scenarioMap = createScenarioMap();
    Map<String, OmgBusinessEvent> result = new HashMap<>();
    result.put("event0101", new OmgBusinessEvent("event0101", DESCRIPTION0101, scenarioMap.get("scenario1")));
    result.put("event0102", new OmgBusinessEvent("event0102", DESCRIPTION0102, scenarioMap.get("scenario1")));
    result.put("event0103", new OmgBusinessEvent("event0103", DESCRIPTION0103, scenarioMap.get("scenario1")));
    result.put("event0201", new OmgBusinessEvent("event0201", DESCRIPTION0201, scenarioMap.get("scenario2")));
    result.put("event0202", new OmgBusinessEvent("event0202", DESCRIPTION0202, scenarioMap.get("scenario2")));
    result.put("event0203", new OmgBusinessEvent("event0203", DESCRIPTION0203, scenarioMap.get("scenario2")));
    result.put("event0301", new OmgBusinessEvent("event0301", DESCRIPTION0301, scenarioMap.get("scenario3")));
    result.put("event0302", new OmgBusinessEvent("event0302", DESCRIPTION0302, scenarioMap.get("scenario3")));
    result.put("event0401", new OmgBusinessEvent("event0401", DESCRIPTION0401, scenarioMap.get("scenario4")));
    result.put("event0402", new OmgBusinessEvent("event0402", DESCRIPTION0402, scenarioMap.get("scenario4")));
    result.put("event0501", new OmgBusinessEvent("event0501", DESCRIPTION0501, scenarioMap.get("scenario5")));
    result.put("event0502", new OmgBusinessEvent("event0502", DESCRIPTION0502, scenarioMap.get("scenario5")));
    return result;
  }
}
