package com.usc.jira_tracker.utils;

import com.usc.jira_tracker.JiraExport;
import com.usc.jira_tracker.models.CustomJiraIssue;
import com.usc.jira_tracker.models.ExcelRecord;
import com.usc.jira_tracker.models.Projects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Utils {

    static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public static void markNewLabel(List<CustomJiraIssue> issues, List<ExcelRecord> prevIssues) {

        for(CustomJiraIssue issue: issues){
            ExcelRecord record=Utils.findExcelRecord(prevIssues,issue.getKey());
            if(record==null){
                issue.setNew("New");
            }else{
                issue.setNew(record.getDepartment());
            }
        }
    }

    public static CustomJiraIssue findCustomJiraIssue(List<CustomJiraIssue> issues,String key){
        for (CustomJiraIssue issue : issues) {
            if(issue.getKey().equals(key)){
                return issue;
            }
        }
        return null;
    }


    public static ExcelRecord findExcelRecord(List<ExcelRecord> excelrecords,String key){
        for (ExcelRecord record : excelrecords) {
            if(record.getKey().equals(key)){
                return record;
            }
        }
        return null;
    }


    public static void changeCurrentStatus(List<CustomJiraIssue> jiraExport, List<ExcelRecord> latestList) {
        for (ExcelRecord record : latestList) {
            if(record.getKey().length()>2){
                System.out.println(record.getKey());
                CustomJiraIssue issue= Utils.findCustomJiraIssue(jiraExport,"AT-"+record.getKey().substring(0,record.getKey().length()-2));
                if (issue == null) {
                    System.out.println("Error: Issue not found "+"AT-"+record.getKey());
                }else{
                    record.setCurrStatus(issue.getStatus());
                }
            }
        }
    }

    public static void cleanupLatestList(List<ExcelRecord> latestList) {
        for (ExcelRecord record : latestList) {
            if(record.getPrevStatus().equalsIgnoreCase("closed") || record.getPrevStatus().equalsIgnoreCase("abandoned")){
                latestList.remove(record);
            }else{
                if(record.getCurrStatus().equalsIgnoreCase("open") || record.getCurrStatus().equalsIgnoreCase("abandoned")){
                    latestList.remove(record);
                }
            }
        }
    }

    public static List<ExcelRecord> convertEpicsIntoProjects(List<CustomJiraIssue> epics) {
        List<ExcelRecord> projects= new ArrayList<ExcelRecord>();
        for(CustomJiraIssue issue:epics){
            projects.add(new ExcelRecord(issue.getKey(),issue.getSummary(),null,issue.getStatus(),issue.getDepartment()));
        }
        return projects;
    }

    public static List<String> getHeader(Constants.Sheet_type sheetType) {
        switch (sheetType){
            case PROJECTS:
                List<String> header_projects= new ArrayList<String>();
                header_projects.add(Constants.RECORD_KEY);
                header_projects.add(Constants.RECORD_Summary);
                header_projects.add(Constants.RECORD_STATUS);
                header_projects.add(Constants.RECORD_DEPARTMENT);
                return header_projects;
            case NEW_LIST:
                List<String> header_new= new ArrayList<String>();
                header_new.add(Constants.RECORD_KEY);
                header_new.add(Constants.RECORD_Summary);
                header_new.add(Constants.RECORD_Quarter);
                header_new.add(Constants.RECORD_STATUS);
                header_new.add(Constants.RECORD_DEPARTMENT);
                return header_new;
            case PREV_LIST:
                List<String> header_list_from= new ArrayList<String>();
                header_list_from.add(Constants.RECORD_KEY);
                header_list_from.add(Constants.RECORD_Summary);
                header_list_from.add(Constants.RECORD_Quarter);
                header_list_from.add(Constants.RECORD_PREV_STATUS);
                header_list_from.add(Constants.RECORD_CURR_STATUS);
                header_list_from.add(Constants.RECORD_DEPARTMENT);
                return header_list_from;
        }

        return null;
    }

    public static List<ExcelRecord> getNewRecords(List<CustomJiraIssue> jiraExports, String date) throws ParseException {
        List<ExcelRecord> newRecords= new ArrayList<ExcelRecord>();
        Date lastReportDate= formatter.parse(date);
        for (CustomJiraIssue issue : jiraExports){
            if(issue.isNew().equalsIgnoreCase("New") && !issue.getStatus().equalsIgnoreCase("Open") && issue.getEpicLink()==null){
                issue.setUpdatedDate(formatter.parse(issue.getUpdated()));
                //isAfter
                if (issue.getUpdatedDate().compareTo(lastReportDate) > 0) {
                    ExcelRecord record= new ExcelRecord( issue.getKey(),issue.getSummary(),issue.getFixVersion(),issue.getStatus(),issue.getDepartment());
                    newRecords.add(record);
                }
            }
        }
        return newRecords;
    }
}
