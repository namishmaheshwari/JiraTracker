package com.usc.jira_tracker.models;

import java.util.HashMap;
import java.util.List;

public class ExcelWorkbook {

    String sheetName;
    List<ExcelRecord> sheetRecords;

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List<ExcelRecord> getSheetRecords() {
        return sheetRecords;
    }

    public void setSheetRecords(List<ExcelRecord> sheetRecords) {
        this.sheetRecords = sheetRecords;
    }
}
