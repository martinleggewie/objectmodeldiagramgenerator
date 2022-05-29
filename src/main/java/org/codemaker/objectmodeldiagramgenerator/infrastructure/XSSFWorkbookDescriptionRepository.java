package org.codemaker.objectmodeldiagramgenerator.infrastructure;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEventDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionStateDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.repositories.DescriptorRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class XSSFWorkbookDescriptionRepository implements DescriptorRepository {

  private final String SHEETNAME_TRANSITIONSTATES = "transitionstates";
  private final String SHEETNAME_SCENARIOS = "scenarios";
  private final String SHEETNAME_BUSINESSEVENTS = "businessevents";
  private final String SHEETNAME_PREFIX_OBJECTMODELSEQUENCE = "oms_";
  private final String PROPERTYNAME_SUFFIX_PRIMARYKEY = "_pk";
  private final String PROPERTYNAME_SUFFIX_FOREIGNKEY = "_fk";
  private final String PROPERTYVALUE_NOTSET = "-";

  private final XSSFWorkbook workbook;

  public XSSFWorkbookDescriptionRepository(XSSFWorkbook workbook) {
    this.workbook = workbook;
  }

  public Set<OmgTransitionStateDescriptor> findAllTransitionStateDescriptors() {
    return new HashSet<>(transitionStateMap().values());
  }

  public OmgTransitionStateDescriptor findTransitionStateDescriptor(String transitionStateDescriptorKey) {
    return transitionStateMap().get(transitionStateDescriptorKey);
  }

  private Map<String, OmgTransitionStateDescriptor> transitionStateMap() {
    Map<String, OmgTransitionStateDescriptor> result = new HashMap<>();

    // Find all the defined transition states and their property values.
    XSSFSheet transitionStateDescriptorsSheet = workbook.getSheet(SHEETNAME_TRANSITIONSTATES);
    Iterator<Row> rowIterator = transitionStateDescriptorsSheet.rowIterator();
    rowIterator.next();
    while (rowIterator.hasNext()) {
      Row row = rowIterator.next();
      String key = row.getCell(0).getStringCellValue().trim();
      String predecessorKey = row.getCell(1).getStringCellValue().trim();
      if (predecessorKey.equals(PROPERTYVALUE_NOTSET)) {
        predecessorKey = "";
      }
      String description = row.getCell(2).getStringCellValue().trim();
      OmgTransitionStateDescriptor transitionStateDescriptor = new OmgTransitionStateDescriptor(key, description, predecessorKey);
      result.put(key, transitionStateDescriptor);
    }

    return result;
  }


  public Set<OmgScenarioDescriptor> findAllScenarioDescriptors() {
    return new HashSet<>(scenarioDescriptorsMap().values());
  }

  public OmgScenarioDescriptor findScenarioDescriptor(String scenarioDescriptorKey) {
    return scenarioDescriptorsMap().get(scenarioDescriptorKey);
  }

  private Map<String, OmgScenarioDescriptor> scenarioDescriptorsMap() {
    Map<String, OmgScenarioDescriptor> result = new HashMap<>();

    // Find all the defined scenarios and their property values.
    XSSFSheet scenarioDescriptorsSheet = workbook.getSheet(SHEETNAME_SCENARIOS);
    Iterator<Row> rowIterator = scenarioDescriptorsSheet.rowIterator();
    rowIterator.next();
    while (rowIterator.hasNext()) {
      Row row = rowIterator.next();
      String key = row.getCell(0).getStringCellValue().trim();
      String predecessorKeysRaw = row.getCell(1).getStringCellValue().trim();
      String description = row.getCell(2).getStringCellValue().trim();

      List<String> predecessorKeys = new ArrayList<>();
      if (!predecessorKeysRaw.equals(PROPERTYVALUE_NOTSET)) {
        for (String predecessorKey : predecessorKeysRaw.split(",")) {
          predecessorKeys.add(predecessorKey.trim());
        }
      }
      OmgScenarioDescriptor scenarioDescriptor = new OmgScenarioDescriptor(key, description, predecessorKeys);
      result.put(key, scenarioDescriptor);
    }

    return result;
  }

  @Override
  public Set<OmgBusinessEventDescriptor> findAllBusinessEventDescriptors() {
    return new HashSet<>(businessEventDescriptorMap().values());
  }

  @Override
  public OmgBusinessEventDescriptor findBusinessEventDescriptor(String businessDescriptorKey) {
    return businessEventDescriptorMap().get(businessDescriptorKey);
  }

  private Map<String, OmgBusinessEventDescriptor> businessEventDescriptorMap() {
    Map<String, OmgBusinessEventDescriptor> result = new HashMap<>();

    // Find all the defined business events and their property values.
    XSSFSheet businessEventDescriptorsSheet = workbook.getSheet(SHEETNAME_BUSINESSEVENTS);
    Iterator<Row> rowIterator = businessEventDescriptorsSheet.rowIterator();
    rowIterator.next();
    while (rowIterator.hasNext()) {
      Row row = rowIterator.next();
      String key = row.getCell(0).getStringCellValue().trim();
      String description = row.getCell(1).getStringCellValue().trim();
      String scenarioKey = row.getCell(2).getStringCellValue().trim();
      OmgBusinessEventDescriptor businessEventDescriptor = new OmgBusinessEventDescriptor(key, description, scenarioKey);
      result.put(key, businessEventDescriptor);
    }

    return result;
  }
}
