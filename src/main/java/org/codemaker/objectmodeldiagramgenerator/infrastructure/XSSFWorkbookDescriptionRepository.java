package org.codemaker.objectmodeldiagramgenerator.infrastructure;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEventDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgClassDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgDomainDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObjectDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioSequenceDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenarioSequenceStepDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgTransitionStateDescriptor;
import org.codemaker.objectmodeldiagramgenerator.domain.repositories.DescriptorRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XSSFWorkbookDescriptionRepository implements DescriptorRepository {

  private final String SHEETNAME_TRANSITIONSTATES = "transitionstates";
  private final String SHEETNAME_SCENARIOS = "scenarios";
  private final String SHEETNAME_BUSINESSEVENTS = "businessevents";
  private final String SHEETNAME_PREFIX_OBJECTMODELSEQUENCE = "oms_";
  private final String PROPERTYNAME_SUFFIX_PRIMARYKEY = "_pk";
  private final String PROPERTYNAME_SUFFIX_FOREIGNKEY = "_fk";
  private final String PROPERTYVALUE_XLS_NOTSET = "-";

  private final XSSFWorkbook workbook;

  public XSSFWorkbookDescriptionRepository(XSSFWorkbook workbook) {
    this.workbook = workbook;
  }

  public Set<OmgTransitionStateDescriptor> findTransitionStateDescriptors() {
    return new HashSet<>(transitionStateMap().values());
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
      if (key.length() == 0) {
        // Current row has a null value for the transition state key. Let's ignore this complete row.
        continue;
      }
      String predecessorKey = row.getCell(1).getStringCellValue().trim();
      if (predecessorKey.equals(PROPERTYVALUE_XLS_NOTSET)) {
        predecessorKey = PROPERTYVALUE_NOTSET;
      }
      String description = row.getCell(2).getStringCellValue().trim();
      OmgTransitionStateDescriptor transitionStateDescriptor = new OmgTransitionStateDescriptor(key, description, predecessorKey);
      result.put(key, transitionStateDescriptor);
    }

    return result;
  }


  public Set<OmgScenarioDescriptor> findScenarioDescriptors() {
    return new HashSet<>(scenarioDescriptorsMap().values());
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
      if (key.length() == 0) {
        // Current row has a null value for the scenario key. Let's ignore this complete row.
        continue;
      }
      String predecessorKeysRaw = row.getCell(1).getStringCellValue().trim();
      String description = row.getCell(2).getStringCellValue().trim();

      OmgScenarioDescriptor scenarioDescriptor = new OmgScenarioDescriptor(key, description);
      if (!predecessorKeysRaw.equals(PROPERTYVALUE_XLS_NOTSET)) {
        for (String predecessorKey : predecessorKeysRaw.split(",")) {
          scenarioDescriptor.getPredecessorKeys().add(predecessorKey.trim());
        }
      }
      result.put(key, scenarioDescriptor);
    }

    return result;
  }

  @Override
  public Set<OmgBusinessEventDescriptor> findBusinessEventDescriptors() {
    return new HashSet<>(businessEventDescriptorMap().values());
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
      if (key.length() == 0) {
        // Current row has a null value for the event key. Let's ignore this complete row.
        continue;
      }
      String description = row.getCell(1).getStringCellValue().trim();
      String scenarioKey = row.getCell(2).getStringCellValue().trim();
      OmgBusinessEventDescriptor businessEventDescriptor = new OmgBusinessEventDescriptor(key, description, scenarioKey);
      result.put(key, businessEventDescriptor);
    }

    return result;
  }

  @Override
  public Set<OmgScenarioSequenceDescriptor> findScenarioSequenceDescriptors() {
    Set<OmgScenarioSequenceDescriptor> result = new HashSet<>();

    Iterator<Sheet> sheetIterator = workbook.sheetIterator();
    while (sheetIterator.hasNext()) {
      Sheet sheet = sheetIterator.next();
      if (sheet.getSheetName().startsWith(SHEETNAME_PREFIX_OBJECTMODELSEQUENCE)) {
        Set<OmgScenarioSequenceDescriptor> scenarioSequenceDescriptors = scenarioSequenceDescriptors((XSSFSheet) sheet);
        result.addAll(scenarioSequenceDescriptors);
      }
    }

    return result;
  }

  private Set<OmgScenarioSequenceDescriptor> scenarioSequenceDescriptors(XSSFSheet sheet) {
    Set<OmgScenarioSequenceDescriptor> result = new HashSet<>();

    // 1. Determine the title and the corresponding transition state
    String title = null;
    String transitionStateDescriptorKey = null;
    String sheetNamePattern = SHEETNAME_PREFIX_OBJECTMODELSEQUENCE + "(\\w+)_(\\w+)";
    String sheetName = sheet.getSheetName();
    Matcher sheetNameMatcher = Pattern.compile(sheetNamePattern).matcher(sheetName);
    if (sheetNameMatcher.find()) {
      title = sheetNameMatcher.group(1);
      transitionStateDescriptorKey = sheetNameMatcher.group(2);
    }

    // 2. Find the size of the sheet to be parsed.
    int maxRowIndex = sheet.getLastRowNum();
    int maxColumnIndex = maxColumnIndex(sheet);

    // 3. find the domains and the column indices which belong to the domains.
    Map<Integer, OmgDomainDescriptor> columnIndexDomainDescriptorMap = columnIndexDomainDescriptorMap(sheet, maxColumnIndex);

    // 4. find the classes and the column indices which belong to the classes.
    Map<Integer, OmgClassDescriptor> columnIndexClassDescriptorMap = columnIndexClassDescriptorMap(sheet, maxColumnIndex,
            columnIndexDomainDescriptorMap);

    // 5. find all scenario sequence steps which are contained in the complete sheet
    List<OmgScenarioSequenceStepDescriptor> scenarioSequenceStepDescriptors = scenarioSequenceStepDescriptors(sheet, maxColumnIndex,
            maxRowIndex, columnIndexDomainDescriptorMap, columnIndexClassDescriptorMap);

    // 6. Now that we have the complete list of scenario sequence steps, we only need to find out which ones belong to which scenario
    // sequence. How hard can it be?
    Map<String, OmgBusinessEventDescriptor> businessEventDescriptorMap = businessEventDescriptorMap();
    Map<String, OmgScenarioSequenceDescriptor> scenarioKeyScenarioSequenceDescriptorMap = new HashMap<>();
    for (OmgScenarioSequenceStepDescriptor scenarioSequenceStepDescriptor : scenarioSequenceStepDescriptors) {
      String businessEventDescriptorKey = scenarioSequenceStepDescriptor.getBusinessEventDescriptorKey();
      OmgBusinessEventDescriptor businessEventDescriptor = businessEventDescriptorMap.get(businessEventDescriptorKey);
      String scenarioKey = businessEventDescriptor.getScenarioKey();
      OmgScenarioSequenceDescriptor scenarioSequenceDescriptor = scenarioKeyScenarioSequenceDescriptorMap.get(scenarioKey);
      if (scenarioSequenceDescriptor == null) {
        scenarioSequenceDescriptor = new OmgScenarioSequenceDescriptor(transitionStateDescriptorKey, title);
        scenarioSequenceDescriptor.getDomainDescriptors().addAll(columnIndexDomainDescriptorMap.values());
        scenarioSequenceDescriptor.getClassDescriptors().addAll(columnIndexClassDescriptorMap.values());
        scenarioKeyScenarioSequenceDescriptorMap.put(scenarioKey, scenarioSequenceDescriptor);
        result.add(scenarioSequenceDescriptor);
      }
      scenarioSequenceDescriptor.getScenarioSequenceStepDescriptors().add(scenarioSequenceStepDescriptor);
    }

    return result;
  }

  private List<OmgScenarioSequenceStepDescriptor> scenarioSequenceStepDescriptors(XSSFSheet sheet, int maxColumnIndex, int maxRowIndex,
                                                                                  Map<Integer, OmgDomainDescriptor> columnIndexDomainDescriptorMap,
                                                                                  Map<Integer, OmgClassDescriptor> columnIndexClassDescriptorMap) {
    List<OmgScenarioSequenceStepDescriptor> result = new ArrayList<>();

    // During the parsing of the concrete objects we always need direct access to this third row because this row contains the
    // corresponding property keys for the concrete property values.
    XSSFRow propertyKeysRow = sheet.getRow(2);

    // We need to skip the first three rows because they contain the metadata.
    for (int rowIndex = 3; rowIndex <= maxRowIndex; rowIndex++) {
      XSSFRow row = sheet.getRow(rowIndex);
      // Read the business event - if this business event is null then we skip the complete row
      XSSFCell businessEventKeyCell = row.getCell(0);
      if (businessEventKeyCell != null && businessEventKeyCell.getStringCellValue() != null && businessEventKeyCell.getStringCellValue()
              .trim().length() > 0) {
        // We found a row which is supposed to contain information we need to process. It all starts with the business event.
        String businessEventDescriptorKey = businessEventKeyCell.getStringCellValue().trim();

        // Then we expect the information about which action this row represents
        String actionValue = row.getCell(1).getStringCellValue().trim().toLowerCase();

        // Now we can parse the rest of the row which is supposed to contain the actual object property values.
        Set<OmgObjectDescriptor> objectDescriptors = new HashSet<>();

        // Initialize both domain and class descriptor with the left-most index value which is 2 because the first real object starts in
        // column 3 (which calculates to 2 because the index counting starts with 0).
        OmgDomainDescriptor soFarDomainDescriptor = columnIndexDomainDescriptorMap.get(2);
        OmgClassDescriptor soFarClassDescriptor = columnIndexClassDescriptorMap.get(2);
        Map<String, String> soFarPropertyMap = new HashMap<>();
        for (int columnIndex = 2; columnIndex <= maxColumnIndex; columnIndex++) {

          OmgDomainDescriptor domainDescriptor = columnIndexDomainDescriptorMap.get(columnIndex);
          OmgClassDescriptor classDescriptor = columnIndexClassDescriptorMap.get(columnIndex);

          // We parse the concrete value of a given property. We fetch the key of that property from the third row.
          String propertyKey = propertyKeysRow.getCell(columnIndex).getStringCellValue().trim();
          String propertyValue = row.getCell(columnIndex).getStringCellValue().trim();
          if (false) {
            System.out.print("        Property:       ");
            System.out.printf("%-20s = %-30s", propertyKey, propertyValue);
            System.out.printf(" / (row|col: %3d|%3d)\n", rowIndex, columnIndex);
          }
          if (propertyValue.equals(PROPERTYVALUE_XLS_NOTSET)) {
            propertyValue = PROPERTYVALUE_NOTSET;
          }

          // We need to check if we have parsed all the properties of the current class
          if (!classDescriptor.equals(soFarClassDescriptor) || (columnIndex == maxColumnIndex)) {
            // Ok, we found the beginning of a new object descriptor or hit the last column.
            if (columnIndex == maxColumnIndex) {
              // We hit the last column, and that means we need to store the current property and its value with the current object and
              // not with the next object.
              soFarPropertyMap.put(propertyKey, propertyValue);
            }

            // Find the name of the primary key for the to-be-created object descriptor in the map of all so far collected properties.
            String soFarObjectDescriptorKeyName = null;
            for (String key : soFarPropertyMap.keySet()) {
              if (key.endsWith(PROPERTYNAME_SUFFIX_PRIMARYKEY)) {
                // We found the value of the object's primary key. We memorize the raw format of that key to be able to later remove
                // it from the list of properties.
                soFarObjectDescriptorKeyName = key;
              }
            }
            if (soFarObjectDescriptorKeyName == null) {
              throw new RuntimeException(
                      "The sheet contained an object with no primary key.\nSheet name: " + sheet.getSheetName() + "\nRow: " + rowIndex +
                              "\nColumn: " + columnIndex);
            }

            // We collect the object descriptor's key value from the properties first. Then we remove this property from the map because
            // otherwise the object descriptor's own key would also be contained in that map, and this would be at least confusing.
            // But we will only create the object descriptor and add it to the objects descriptor list if the key value is set.
            String soFarObjectDescriptorKeyValue = soFarPropertyMap.get(soFarObjectDescriptorKeyName);
            if (!soFarObjectDescriptorKeyValue.equals(PROPERTYVALUE_NOTSET)) {
              Map<String, String> soFarPropertyMapCopy = new HashMap<>(soFarPropertyMap);
              soFarPropertyMapCopy.remove(soFarObjectDescriptorKeyName);
              OmgObjectDescriptor soFarObjectDescriptor = new OmgObjectDescriptor(soFarDomainDescriptor.getKey(),
                      soFarClassDescriptor.getKey(), soFarObjectDescriptorKeyValue);
              soFarObjectDescriptor.getPropertyMap().putAll(soFarPropertyMapCopy);
              soFarObjectDescriptor.getDependeeKeys().addAll(dependeeKeys(soFarPropertyMapCopy));
              objectDescriptors.add(soFarObjectDescriptor);
            }

            // Prepare reading the next object
            soFarDomainDescriptor = domainDescriptor;
            soFarClassDescriptor = classDescriptor;
            soFarPropertyMap = new HashMap<>();
          }
          soFarPropertyMap.put(propertyKey, propertyValue);
        }

        // We reached the last column end of the row. That means we can use all collected object descriptors, the business event
        // descriptor, and the action value to create a object sequence step descriptor and add it to the result.
        OmgScenarioSequenceStepDescriptor objectSequenceStepDescriptor = new OmgScenarioSequenceStepDescriptor(businessEventDescriptorKey,
                actionValue);
        objectSequenceStepDescriptor.getObjectDescriptors().addAll(objectDescriptors);
        result.add(objectSequenceStepDescriptor);
      }
    }
    return result;
  }

  private List<String> dependeeKeys(Map<String, String> propertyMap) {
    List<String> result = new ArrayList<>();
    for (String key : propertyMap.keySet()) {
      if (key.endsWith(PROPERTYNAME_SUFFIX_FOREIGNKEY)) {
        // We have found a reference to one or several objects. We now need to search everything to find the
        // corresponding dependee object(s).
        String foreignKeyValueRaw = propertyMap.get(key).trim();
        String[] splitresult = foreignKeyValueRaw.split("[(),]");
        for (String splitKey : splitresult) {
          splitKey = splitKey.trim();
          if (splitKey.length() > 0 && !splitKey.equals(PROPERTYVALUE_NOTSET)) {
            result.add(splitKey);
          }
        }
      }
    }
    return result;
  }

  private int maxColumnIndex(XSSFSheet sheet) {
    // For the number of columns we look at the third row because there we see all the properties, and from there we can derive the
    // right-most column.
    int result = 1;
    XSSFCell currentCell;
    XSSFRow thirdRow = sheet.getRow(2);
    do {
      result++;
      currentCell = thirdRow.getCell(result);
    } while (currentCell != null && currentCell.getRawValue() != null && currentCell.getRawValue().length() > 0);
    return result - 1;
  }

  private Map<Integer, OmgDomainDescriptor> columnIndexDomainDescriptorMap(XSSFSheet sheet, int maxColumnIndex) {
    // This information is stored in the first row of the sheet, and there we can start looking at the third column. The first column
    // contains the business events, the second the action key.
    Map<Integer, OmgDomainDescriptor> result = new HashMap<>();

    OmgDomainDescriptor currentDomainDescriptor = null;
    XSSFRow firstRow = sheet.getRow(0);
    for (int i = 2; i <= maxColumnIndex; i++) {
      XSSFCell currentCell = firstRow.getCell(i);
      if (currentCell != null && currentCell.getStringCellValue().trim().length() > 0) {
        // We found the start of a new domain.
        String currentDomainInfo = currentCell.getStringCellValue();
        String domainInfoPattern = "(.+)\\((.+)\\)";
        Matcher matcher = Pattern.compile(domainInfoPattern).matcher(currentDomainInfo);
        if (matcher.matches()) {
          String domainDisplayName = matcher.group(1).trim();
          String domainKey = matcher.group(2).trim();
          currentDomainDescriptor = new OmgDomainDescriptor(domainKey, domainDisplayName);
        }
      }
      result.put(i, currentDomainDescriptor);
    }
    return result;
  }

  private Map<Integer, OmgClassDescriptor> columnIndexClassDescriptorMap(XSSFSheet sheet, int maxColumnIndex,
                                                                         Map<Integer, OmgDomainDescriptor> columnIndexDomainDescriptorMap) {
    // This information is stored in the second row of the sheet, and there we can start looking at the third column. The first
    // column contains the business events, the second the action key.
    Map<Integer, OmgClassDescriptor> result = new HashMap<>();

    OmgClassDescriptor currentClassDescriptor = null;
    XSSFRow secondRow = sheet.getRow(1);
    for (int i = 2; i <= maxColumnIndex; i++) {
      XSSFCell currentCell = secondRow.getCell(i);
      if (currentCell != null && currentCell.getStringCellValue().trim().length() > 0) {
        // We found the start of a new class.
        String currentClassInfo = currentCell.getStringCellValue();
        String classInfoPattern = "(.+)\\((.+)\\)";
        Matcher matcher = Pattern.compile(classInfoPattern).matcher(currentClassInfo);
        if (matcher.matches()) {
          String classDisplayName = matcher.group(1).trim();
          String classKey = matcher.group(2).trim();
          OmgDomainDescriptor domainDescriptor = columnIndexDomainDescriptorMap.get(i);
          currentClassDescriptor = new OmgClassDescriptor(classKey, classDisplayName, domainDescriptor.getKey());
        }
      }
      result.put(i, currentClassDescriptor);
    }
    return result;
  }

}
