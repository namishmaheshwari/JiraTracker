package com.usc.jira_tracker;

import com.usc.jira_tracker.models.CustomJiraIssue;
import com.usc.jira_tracker.models.ExcelRecord;
import com.usc.jira_tracker.utils.Constants;
import com.usc.jira_tracker.utils.Utils;
import org.codehaus.jettison.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class Application {

    public static void main(String[] args) throws InterruptedException, IOException, JSONException, ParseException {
        String date="2019-04-02";
        String inputFileName="/Users/namishmaheshwari/Downloads/"+date+" - ESD Portfolio.xlsx";
        String outputFileName="/Users/namishmaheshwari/Downloads/dummy.xlsx";
        DAOExcel reader = DataReader.getInstance(inputFileName);

        List<CustomJiraIssue> jiraExport = JiraExport.getJiraIssues("project='Academic Technology' AND (resolved is EMPTY OR resolved >StartOfMonth(-1))");
        List<ExcelRecord> prevList= reader.getListFromWorkBook(Constants.PREV_LIST);
        Utils.markNewLabel(jiraExport,prevList);

        List<ExcelRecord> latestList = reader.getLatestList();
        Utils.changeCurrentStatus(jiraExport,latestList);

        //no need to fetch only
        //TODO : can be removed if we dont fetch closed status records but needs changing query
        Utils.cleanupLatestList(latestList);
        reader.write(Constants.Sheet_type.PREV_LIST,latestList,outputFileName);

        //New Records
        List<ExcelRecord> newJiraIssues = Utils.getNewRecords(jiraExport,date);
        reader.write(Constants.Sheet_type.NEW_LIST,newJiraIssues,outputFileName);

        //Epics
        List<CustomJiraIssue> epics = JiraExport.getJiraIssues("project='Academic Technology' AND type=Epic");

        List<ExcelRecord> projectsList= Utils.convertEpicsIntoProjects(epics);
        reader.write(Constants.Sheet_type.PROJECTS,projectsList,outputFileName);
    }
}
