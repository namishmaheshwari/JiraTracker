package com.usc.jira_tracker.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.usc.jira_tracker.models.ExcelRecord;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelClient {

    public static JsonArray getExcelDataAsJsonObject(File excelFile) throws IOException {

        JsonArray sheetsJsonObject = new JsonArray();

        Workbook workbook = null;
        workbook = new XSSFWorkbook(new FileInputStream(excelFile));


        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            JsonArray sheetArray = new JsonArray();
            ArrayList<String> columnNames = new ArrayList<String>();
            Sheet sheet = workbook.getSheetAt(i);
            Iterator<Row> sheetIterator = sheet.iterator();

            while (sheetIterator.hasNext()) {
                Row currentRow = sheetIterator.next();
                JsonObject jsonObject = new JsonObject();

                if (currentRow.getRowNum() != 0) {
                    if(currentRow.getCell(0)!=null) {
                        for (int j = 0; j < columnNames.size(); j++) {
                            if (currentRow.getCell(j) != null) {
                                if (currentRow.getCell(j).getCellType() == Cell.CELL_TYPE_STRING) {
                                    jsonObject.addProperty(columnNames.get(j), currentRow.getCell(j).getStringCellValue());
                                } else if (currentRow.getCell(j).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                    jsonObject.addProperty(columnNames.get(j), currentRow.getCell(j).getNumericCellValue());
                                } else if (currentRow.getCell(j).getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                                    jsonObject.addProperty(columnNames.get(j), currentRow.getCell(j).getBooleanCellValue());
                                } else if (currentRow.getCell(j).getCellType() == Cell.CELL_TYPE_BLANK) {
                                    jsonObject.addProperty(columnNames.get(j), "");
                                }else if(currentRow.getCell(j).getCellType() == Cell.CELL_TYPE_FORMULA){
                                    switch(currentRow.getCell(j).getCachedFormulaResultType()){
                                        case Cell.CELL_TYPE_NUMERIC:
                                            jsonObject.addProperty(columnNames.get(j), currentRow.getCell(j).getNumericCellValue());
                                            break;
                                        case Cell.CELL_TYPE_STRING:
                                            jsonObject.addProperty(columnNames.get(j), currentRow.getCell(j).getStringCellValue());
                                            break;
                                    }
                                }
                            } else {
                                jsonObject.addProperty(columnNames.get(j), "");
                            }
                        }
                        sheetArray.add(jsonObject);
                    }
                } else {
                    // store column names
                    for (int k = 0; k < currentRow.getPhysicalNumberOfCells(); k++) {
                        columnNames.add(currentRow.getCell(k).getStringCellValue());
                    }
                }
            }
            JsonObject prop = new JsonObject();
            prop.addProperty("sheetName",workbook.getSheetName(i));
            prop.add("sheetRecords",sheetArray);
            sheetsJsonObject.add(prop);
        }

        return sheetsJsonObject;
    }

    public static void writeExcel(int sheetIndex,List<String> header, List<ExcelRecord> listRecords, String excelFilePath) throws IOException {
        //TODO
        //  1. Write wrapper to write multiple sheets at once.
        //  2. copy paste template and then write into the destination.


        FileInputStream fsIP= new FileInputStream(new File(excelFilePath));
        Workbook workbook = new XSSFWorkbook(fsIP);


        Sheet sheet = workbook.getSheetAt(sheetIndex);
        createHeaderRow(sheet,header);
        int rowCount = 0;

        for (ExcelRecord aRecord : listRecords) {
            Row row = sheet.getRow(++rowCount);
            writeBook(aRecord, row, header);
        }

        FileOutputStream outputStream = new FileOutputStream(excelFilePath);
        workbook.write(outputStream);
    }

    private static void createHeaderRow(Sheet sheet,List<String> header) {

        Row row = sheet.getRow(0);
        for(int i=0;i<header.size();i++){
            Cell cell = row.getCell(i);
            cell.setCellValue(header.get(i));
        }
    }

    private static void writeBook(ExcelRecord aRecord, Row row, List<String> header) {

        for(int i=0;i<header.size();i++){
            Cell cell = row.getCell(i);
            cell.setCellValue(aRecord.getHeader(header.get(i)));
        }
    }
}
