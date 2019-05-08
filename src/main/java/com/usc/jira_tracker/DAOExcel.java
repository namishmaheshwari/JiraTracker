package com.usc.jira_tracker;

import com.google.gson.Gson;
import com.sun.xml.bind.v2.runtime.reflect.opt.Const;
import com.usc.jira_tracker.models.ExcelRecord;
import com.usc.jira_tracker.models.ExcelWorkbook;
import com.usc.jira_tracker.utils.Constants;
import com.usc.jira_tracker.utils.ExcelClient;
import com.usc.jira_tracker.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DAOExcel {

    private static File file;
    private static Gson gson;
    private static HashMap<Constants.Sheet_type, Integer> workbookKeyMap;
    private static ExcelWorkbook[] workbooks;

    public DAOExcel(String fileName) {
        file = new File(fileName);
        gson = new Gson();
        try {
            workbooks = gson.fromJson(ExcelClient.getExcelDataAsJsonObject(file), ExcelWorkbook[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        workbookKeyMap = new HashMap<Constants.Sheet_type, Integer>();
        workbookKeyMap.put(Constants.Sheet_type.PREV_LIST,0);
        workbookKeyMap.put(Constants.Sheet_type.NEW_LIST,1);
        workbookKeyMap.put(Constants.Sheet_type.PROJECTS,2);
        workbookKeyMap.put(Constants.Sheet_type.JIRA_EXPORT,3);

    }

    public static List<ExcelRecord> getListFromWorkBook(String workbookKey){

        return workbooks[workbookKeyMap.get(workbookKey)].getSheetRecords();
//        for(ExcelWorkbook excelWorkbook:workbooks){
//            if(excelWorkbook.getSheetName().equalsIgnoreCase(workbookKey)){
//                return excelWorkbook.getSheetRecords();
//            }
//        }
//        return null;
    }

    public static List<ExcelRecord> getLatestList() throws IOException {
            List<ExcelRecord> latestListFrom = new ArrayList<ExcelRecord>();
            for(ExcelWorkbook excelWorkbook : workbooks){
                if(excelWorkbook.getSheetName().startsWith("List from")){
                    List<ExcelRecord> oldListFrom = excelWorkbook.getSheetRecords();
                    for (ExcelRecord e:oldListFrom) {
                        latestListFrom.add(new ExcelRecord(e.getKey(),e.getSummary(),e.getQuarter(),e.getCurrStatus(),"",e.getDepartment()));
                    }
                }
                if(excelWorkbook.getSheetName().equalsIgnoreCase("NEW")){
                    List<ExcelRecord> prevNewRecords = excelWorkbook.getSheetRecords();
                    for (ExcelRecord e:prevNewRecords) {
                        latestListFrom.add(new ExcelRecord(e.getKey(),e.getSummary(),e.getQuarter(),e.getStatus(),"",e.getDepartment()));
                    }
                }
            }
            return latestListFrom;
    }

    public static void write(Constants.Sheet_type sheetName,List<ExcelRecord> list,String outFileName ) throws IOException {
        List<String> header= Utils.getHeader(sheetName);
        ExcelClient.writeExcel(workbookKeyMap.get(sheetName),header,list,outFileName);
    }
}
