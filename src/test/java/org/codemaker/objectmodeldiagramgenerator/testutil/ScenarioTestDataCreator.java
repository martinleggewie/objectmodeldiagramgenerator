package org.codemaker.objectmodeldiagramgenerator.testutil;

import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenario;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioDescriptor;

import java.util.HashMap;
import java.util.Map;

public class ScenarioTestDataCreator {

  private static final String DESCRIPTION1 = "The first house is built, and then Anton moves in to the first floor.";
  private static final String DESCRIPTION2 = "Anton marries Berta, and Berta moves into the first house to the first floor as well.";
  private static final String DESCRIPTION3 = "The second house is built, and Anton and Berta move from first house to second house, " +
          "third floor.";
  private static final String DESCRIPTION4 = "Charlie moves in to the first house, second floor.";
  private static final String DESCRIPTION5 =
          "The first house is destroyed, but before that, Charlie luckily also moves to the second " + "house, fourth floor, but kept " + "tenant contract with first house.";
  private static final String DESCRIPTION6 = "Anton, Berta, and Charlie become friends and get older.";

  public static Map<String, OmgScenarioDescriptor> createScenarioDescriptorMap() {
    OmgScenarioDescriptor descriptor1 = new OmgScenarioDescriptor("scenario1", DESCRIPTION1);
    OmgScenarioDescriptor descriptor2 = new OmgScenarioDescriptor("scenario2", DESCRIPTION2);
    OmgScenarioDescriptor descriptor3 = new OmgScenarioDescriptor("scenario3", DESCRIPTION3);
    OmgScenarioDescriptor descriptor4 = new OmgScenarioDescriptor("scenario4", DESCRIPTION4);
    OmgScenarioDescriptor descriptor5 = new OmgScenarioDescriptor("scenario5", DESCRIPTION5);
    OmgScenarioDescriptor descriptor6 = new OmgScenarioDescriptor("scenario6", DESCRIPTION6);
    descriptor2.getPredecessorKeys().add("scenario1");
    descriptor3.getPredecessorKeys().add("scenario2");
    descriptor4.getPredecessorKeys().add("scenario1");
    descriptor5.getPredecessorKeys().add("scenario1");
    descriptor5.getPredecessorKeys().add("scenario3");
    descriptor5.getPredecessorKeys().add("scenario4");
    descriptor6.getPredecessorKeys().add("scenario1");
    descriptor6.getPredecessorKeys().add("scenario2");
    descriptor6.getPredecessorKeys().add("scenario4");
    Map<String, OmgScenarioDescriptor> result = new HashMap<>();
    result.put(descriptor1.getKey(), descriptor1);
    result.put(descriptor2.getKey(), descriptor2);
    result.put(descriptor3.getKey(), descriptor3);
    result.put(descriptor4.getKey(), descriptor4);
    result.put(descriptor5.getKey(), descriptor5);
    result.put(descriptor6.getKey(), descriptor6);
    return result;
  }

  public static Map<String, OmgScenario> createScenarioMap() {
    OmgScenario scenario1 = new OmgScenario("scenario1", DESCRIPTION1);
    OmgScenario scenario2 = new OmgScenario("scenario2", DESCRIPTION2);
    OmgScenario scenario3 = new OmgScenario("scenario3", DESCRIPTION3);
    OmgScenario scenario4 = new OmgScenario("scenario4", DESCRIPTION4);
    OmgScenario scenario5 = new OmgScenario("scenario5", DESCRIPTION5);
    OmgScenario scenario6 = new OmgScenario("scenario6", DESCRIPTION6);
    scenario2.getPredecessors().add(scenario1);
    scenario3.getPredecessors().add(scenario2);
    scenario4.getPredecessors().add(scenario1);
    scenario5.getPredecessors().add(scenario1);
    scenario5.getPredecessors().add(scenario3);
    scenario5.getPredecessors().add(scenario4);
    scenario6.getPredecessors().add(scenario1);
    scenario6.getPredecessors().add(scenario2);
    scenario6.getPredecessors().add(scenario4);

    Map<String, OmgScenario> result = new HashMap<>();
    result.put(scenario1.getKey(), scenario1);
    result.put(scenario2.getKey(), scenario2);
    result.put(scenario3.getKey(), scenario3);
    result.put(scenario4.getKey(), scenario4);
    result.put(scenario5.getKey(), scenario5);
    result.put(scenario6.getKey(), scenario6);
    return result;
  }
}
