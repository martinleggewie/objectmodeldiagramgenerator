package org.codemaker.objectmodeldiagramgenerator.infrastructure;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgBusinessEvent;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgDefinition;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class OmgDefinitionReader {

  private InputStream inputStream;

  public OmgDefinitionReader(InputStream inputStream) {
    this.inputStream = inputStream;
  }

  public OmgDefinition read() throws IOException {
    OmgDefinition result = new OmgDefinition();

    XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

    XSSFSheet businessEventDefinitionsSheet = workbook.getSheet("Business Event Definitions");
    Iterator<Row> rowIterator = businessEventDefinitionsSheet.rowIterator();
    rowIterator.next();
    while (rowIterator.hasNext()) {
      Row row = rowIterator.next();
      String key = row.getCell(0).getStringCellValue();
      String description = row.getCell(1).getStringCellValue();
      result.getBusinessEventMap().put(key, new OmgBusinessEvent(key, description, "(not yet defined)"));
    }



//    Iterator<Row> rowIterator = cfgSysSheet.rowIterator();
//    rowIterator.next();
//    while (rowIterator.hasNext()) {
//      Row row = rowIterator.next();
//      String sysName = row.getCell(0).getStringCellValue();
//      String sysType = row.getCell(1).getStringCellValue();
//      result.put(sysName, new Sys(sysName, sysType, MigrationState.STAYING));
//
//    }


    return result;
  }



}
