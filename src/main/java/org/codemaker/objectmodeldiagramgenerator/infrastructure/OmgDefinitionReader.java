package org.codemaker.objectmodeldiagramgenerator.infrastructure;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEvent;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgClass;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgDefinition;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgDomain;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObject;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObjectModel;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgObjectModelSequence;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgScenario;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OmgDefinitionReader {

  private final String SHEETNAME_SCENARIOS = "scenarios";
  private final String SHEETNAME_BUSINESSEVENTS = "businessevents";
  private final String SHEETNAME_PREFIX_OBJECTMODELSEQUENCE = "oms_";
  private final String PROPERTYNAME_SUFFIX_PRIMARYKEY = "_pk";
  private final String PROPERTYNAME_SUFFIX_FOREIGNKEY = "_fk";
  private final String PROPERTYVALUE_NOTSET = "-";

  private final InputStream inputStream;

  public OmgDefinitionReader(InputStream inputStream) {
    this.inputStream = inputStream;
  }

  public OmgDefinition read() throws IOException {

    XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

    // 1. read scenarios
    System.out.println("    Reading the scenarios.");
    XSSFSheet scenarioDefinitionsSheet = workbook.getSheet(SHEETNAME_SCENARIOS);
    Map<String, OmgScenario> scenarioMap = readScenarioMap(scenarioDefinitionsSheet);

    // 2. read business events
    System.out.println("    Reading the business events.");
    XSSFSheet businessEventDefinitionsSheet = workbook.getSheet(SHEETNAME_BUSINESSEVENTS);
    Map<String, OmgBusinessEvent> businessEventMap = readBusinessEventMap(businessEventDefinitionsSheet, scenarioMap);

    // 3. read all object model sequences. We can identify an object model sequence sheet by its name prefix
    System.out.println("    Reading the object model sequences.");
    List<OmgObjectModelSequence> objectModelSequences = new ArrayList<>();
    Iterator<Sheet> sheetIterator = workbook.sheetIterator();
    while (sheetIterator.hasNext()) {
      Sheet sheet = sheetIterator.next();
      if (sheet.getSheetName().startsWith(SHEETNAME_PREFIX_OBJECTMODELSEQUENCE)) {
        System.out.println("        Sheet name: " + sheet.getSheetName());
        objectModelSequences.add(readObjectModelSequence((XSSFSheet) sheet, businessEventMap));
      }
    }

    return new OmgDefinition(scenarioMap, businessEventMap, objectModelSequences);
  }

  private Map<String, OmgScenario> readScenarioMap(XSSFSheet sheet) {
    Map<String, OmgScenario> result = new HashMap<>();

    // First pass: Find all the defined scenarios, but leave the references to their respective predecessors still open.
    Iterator<Row> rowIteratorFirstPass = sheet.rowIterator();
    rowIteratorFirstPass.next();
    while (rowIteratorFirstPass.hasNext()) {
      Row row = rowIteratorFirstPass.next();
      String key = row.getCell(0).getStringCellValue().trim();
      String description = row.getCell(2).getStringCellValue().trim();
      result.put(key, new OmgScenario(key, description, new ArrayList<>()));
    }

    // Second pass: Now that we have found all the defined scenarios, we can add the referenced predecessor scenarios.
    // Yes, I know that this is not the best design to collect all the information like this, but so far this whole software is not
    // intended to be the beginning of a rise of a super software. Instead, I spend my spare private free time to program this.
    Iterator<Row> rowIteratorSecondPass = sheet.rowIterator();
    rowIteratorSecondPass.next();
    while (rowIteratorSecondPass.hasNext()) {
      Row row = rowIteratorSecondPass.next();
      String key = row.getCell(0).getStringCellValue().trim();
      OmgScenario scenario = result.get(key);
      String predecessorKeysRaw = row.getCell(1).getStringCellValue().trim();
      for (String predecessorKey : predecessorKeysRaw.split(",")) {
        OmgScenario predecessorScenario = result.get(predecessorKey.trim());
        if (predecessorScenario != null) {
          scenario.getPredecessors().add(predecessorScenario);
        }
      }
      System.out.println("        Scenario: " + scenario.getKey() + ", " + scenario.getDescription() + ", " + scenario.getPredecessors());
    }

    return result;
  }

  private Map<String, OmgBusinessEvent> readBusinessEventMap(XSSFSheet sheet, Map<String, OmgScenario> scenarioMap) {
    Map<String, OmgBusinessEvent> result = new HashMap<>();
    Iterator<Row> rowIterator = sheet.rowIterator();
    rowIterator.next();
    while (rowIterator.hasNext()) {
      Row row = rowIterator.next();
      String key = row.getCell(0).getStringCellValue().trim();
      String description = row.getCell(1).getStringCellValue().trim();
      String scenarioKey = row.getCell(2).getStringCellValue().trim();
      OmgScenario scenario = scenarioMap.get(scenarioKey);
      result.put(key, new OmgBusinessEvent(key, description, scenario));
      System.out.println("        Business event: " + key + ", " + scenarioKey + ", " + description);
    }
    return result;
  }

  private OmgObjectModelSequence readObjectModelSequence(XSSFSheet sheet, Map<String, OmgBusinessEvent> businessEventMap) {
    // 1. Determine the name of the sequence
    String sequenceName = sheet.getSheetName().substring(SHEETNAME_PREFIX_OBJECTMODELSEQUENCE.length());
    System.out.println("        Sequence name:  " + sequenceName);

    // 2. Find the size of the sheet to be parsed.
    int maxRowIndex = sheet.getLastRowNum();
    int maxColumnIndex = maxColumnIndex(sheet);
    System.out.println("        maxRowIndex:    " + maxRowIndex);
    System.out.println("        maxColumnIndex: " + maxColumnIndex);

    // 3. find the domains and the column indices which belong to the domains.
    Map<Integer, OmgDomain> columnIndexDomainMap = columnIndexDomainMap(sheet, maxColumnIndex);

    // 4. find the classes and the column indices which belong to the classes.
    Map<Integer, OmgClass> columnIndexClassMap = columnIndexClassMap(sheet, maxColumnIndex, columnIndexDomainMap);

    // 5. By now we have collected all the meta data, and that means we can finally start parsing the actual object model sequence content.
    List<OmgObjectModel> objectModels = objectModels(sheet, maxColumnIndex, columnIndexClassMap, maxRowIndex, businessEventMap);

    return new OmgObjectModelSequence(sequenceName, objectModels);
  }

  private int maxColumnIndex(XSSFSheet sheet) {
    // For the number of columns we look at the third row because there we see all the properties, and from there we can derive the
    // right-most column.
    int result = 0;
    XSSFCell currentCell;
    XSSFRow thirdRow = sheet.getRow(2);
    do {
      result++;
      currentCell = thirdRow.getCell(result);
    } while (currentCell != null && currentCell.getRawValue() != null && currentCell.getRawValue().length() > 0);
    return result - 1;
  }

  private Map<Integer, OmgDomain> columnIndexDomainMap(XSSFSheet sheet, int maxColumnIndex) {
    // This information is stored in the first row of the sheet, and there we can start looking at the second column. The first column
    // contains the business events.
    Map<Integer, OmgDomain> result = new HashMap<>();

    OmgDomain currentDomain = null;
    XSSFRow firstRow = sheet.getRow(0);
    for (int i = 1; i <= maxColumnIndex; i++) {
      XSSFCell currentCell = firstRow.getCell(i);
      if (currentCell != null && currentCell.getStringCellValue().trim().length() > 0) {
        // We found the start of a new domain.
        String currentDomainNameRaw = currentCell.getStringCellValue();
        String domainNameRawPattern = "(.+)\\((.+)\\)";
        Matcher matcher = Pattern.compile(domainNameRawPattern).matcher(currentDomainNameRaw);
        if (matcher.matches()) {
          String domainName = matcher.group(1).trim();
          String domainKey = matcher.group(2).trim();
          currentDomain = new OmgDomain(domainKey, domainName);
          System.out.println("        Domain:         " + domainKey + ", " + domainName);
        }
      }
      result.put(i, currentDomain);
    }
    return result;
  }

  private Map<Integer, OmgClass> columnIndexClassMap(XSSFSheet sheet, int maxColumnIndex, Map<Integer, OmgDomain> columnIndexDomainMap) {
    // This information is stored in the second row of the sheet, and there we can start looking at the second column. The first
    // column  contains the business events.
    Map<Integer, OmgClass> result = new HashMap<>();

    OmgClass currentClass = null;
    XSSFRow secondRow = sheet.getRow(1);
    for (int i = 1; i <= maxColumnIndex; i++) {
      XSSFCell currentCell = secondRow.getCell(i);
      if (currentCell != null && currentCell.getStringCellValue().trim().length() > 0) {
        // We found the start of a new class.
        String currentClassNameRaw = currentCell.getStringCellValue();
        String classNameRawPattern = "(.+)\\((.+)\\)";
        Matcher matcher = Pattern.compile(classNameRawPattern).matcher(currentClassNameRaw);
        if (matcher.matches()) {
          String className = matcher.group(1).trim();
          String classKey = matcher.group(2).trim();
          OmgDomain domain = columnIndexDomainMap.get(i);
          currentClass = new OmgClass(classKey, className, domain);
          System.out.println("        Class:          " + classKey + ", " + className);
        }
      }
      result.put(i, currentClass);
    }
    return result;
  }

  private List<OmgObjectModel> objectModels(XSSFSheet sheet, int maxColumnIndex, Map<Integer, OmgClass> columnIndexClassMap,
                                            int maxRowIndex, Map<String, OmgBusinessEvent> businessEventMap) {
    List<OmgObjectModel> result = new ArrayList<>();
    // During the parsing of the concrete objects we always need direct access to this third row because this row contains the
    // corresponding property keys for the concrete property values.
    XSSFRow propertyKeysRow = sheet.getRow(2);

    // We need to skip the first three rows because they contain the metadata.
    for (int rowIndex = 3; rowIndex <= maxRowIndex; rowIndex++) {
      XSSFRow row = sheet.getRow(rowIndex);
      // Read the business event
      XSSFCell businessEventCell = row.getCell(0);
      if (businessEventCell != null && businessEventMap.containsKey(businessEventCell.getStringCellValue())) {
        // We found a row which is supposed to contain information we need to process. It all starts with the business event.
        OmgBusinessEvent businessEvent = businessEventMap.get(businessEventCell.getStringCellValue());

        // Now we can parse the rest of the row which is supposed to contain the actual object property values.
        Set<OmgObject> objects = new HashSet<>();
        OmgClass soFarClass = columnIndexClassMap.get(1);
        Map<String, String> soFarPropertyMap = new HashMap<>();
        for (int columnIndex = 1; columnIndex <= maxColumnIndex; columnIndex++) {

          OmgClass clazz = columnIndexClassMap.get(columnIndex);

          // We parse the concrete value of a given property. We fetch the key of that property from the third row.
          String propertyKey = propertyKeysRow.getCell(columnIndex).getStringCellValue().trim();
          String propertyValue = row.getCell(columnIndex).getStringCellValue().trim();
          System.out.print("        Property:       ");
          System.out.printf("%-20s = %-30s", propertyKey, propertyValue);
          System.out.printf(" / (row|col: %3d|%3d)\n", rowIndex, columnIndex);

          // We need to check if we have parsed all the properties of the current class
          if (!clazz.equals(soFarClass) || (columnIndex == maxColumnIndex)) {
            if (columnIndex == maxColumnIndex) {
              // We hit the last column, and that means we need to store the current property and its value with the current object and
              // not with the next object.
              soFarPropertyMap.put(propertyKey, propertyValue);
            }

            // Find the primary key for the to-be-created object
            String soFarObjectKeyRaw = null;
            for (String key : soFarPropertyMap.keySet()) {
              if (key.endsWith(PROPERTYNAME_SUFFIX_PRIMARYKEY)) {
                // We found the value of the object's primary key. We memorize the raw format of that key to be able to later remove
                // it from the list of properties.
                soFarObjectKeyRaw = key;
              }
            }
            if (soFarObjectKeyRaw == null) {
              throw new RuntimeException(
                      "The sheet contained an object with no primary key.\nSheet name: " + sheet.getSheetName() + "\nRow: " + rowIndex +
                              "\nColumn: " + columnIndex);
            }

            // We collect the object's key value from the properties first. Then we remove this property from the map because
            // otherwise the object's key would also be contained in that map, and this would be at least confusing.
            // But we will only create the object and add it to the objects list if the key value is set.
            String soFarObjectKeyValue = soFarPropertyMap.get(soFarObjectKeyRaw);
            if (!soFarObjectKeyValue.equals(PROPERTYVALUE_NOTSET)) {
              Map<String, String> soFarPropertyMapCopy = new HashMap<>(soFarPropertyMap);
              soFarPropertyMapCopy.remove(soFarObjectKeyRaw);
              Set<OmgObject> dependeeObjects = dependeeObjects(soFarPropertyMapCopy, result);
              OmgObject soFarObject = new OmgObject(soFarObjectKeyValue, soFarClass, soFarPropertyMapCopy, dependeeObjects);
              objects.add(soFarObject);
            }

            // Prepare reading the next object
            soFarClass = clazz;
            soFarPropertyMap = new HashMap<>();
          }
          soFarPropertyMap.put(propertyKey, propertyValue);
        }

        // We reached the end of the row. That means we can collect all objects and the business event and create the result.
        OmgObjectModel objectModel = new OmgObjectModel(businessEvent, objects);
        result.add(objectModel);
      }
    }
    return result;
  }

  private Set<OmgObject> dependeeObjects(Map<String, String> propertyMap, List<OmgObjectModel> objectModels) {
    Set<OmgObject> result = new HashSet<>();

    for (String key : propertyMap.keySet()) {
      if (key.endsWith(PROPERTYNAME_SUFFIX_FOREIGNKEY)) {
        // We have found a reference to on or several objects. We now need to search the complete list of object models to find the
        // corresponding dependee object(s).
        String foreignKeyValueRaw = propertyMap.get(key).trim();
        String[] splitresult = foreignKeyValueRaw.split("[(),]");
        Set<String> foreignKeyValues = new HashSet<>(Arrays.asList(splitresult));

        for (OmgObjectModel objectModel : objectModels) {
          for (OmgObject object : objectModel.getObjects()) {
            if (foreignKeyValues.contains(object.getKey())) {
              result.add(object);
            }
          }
        }
      }
    }

    return result;
  }
}
