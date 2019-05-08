package com.usc.jira_tracker.utils;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;

public class Constants {
    public static final String JIRA_URL = "https://jira.usc.edu/jira";
    public static final String JIRA_ADMIN_USERNAME = "Jira_Reporting";
    public static final String JIRA_ADMIN_PASSWORD = "test";

//    public static final String JIRA_URL = "http://localhost:8080";
//    public static final String JIRA_ADMIN_USERNAME = "namishmaheshwari";
//    public static final String JIRA_ADMIN_PASSWORD = "123456789";


    public static final String PREV_LIST = "List from";
    public static final String NEW_LIST = "NEW";
    public static final String PROJECTS = "Projects";
    public static final String JIRA_EXPORT = "JIRA EXPORT";

    public enum Sheet_type {
        PROJECTS, NEW_LIST, JIRA_EXPORT, PREV_LIST;
    }

    public static final String RECORD_KEY = "Key";
    public static final String RECORD_Summary = "Summary";
    public static final String RECORD_Quarter = "Quarter";
    public static final String RECORD_STATUS = "Status";
    public static final String RECORD_PREV_STATUS = "Previous Satus";
    public static final String RECORD_CURR_STATUS = "Current Status";
    public static final String RECORD_DEPARTMENT = "Department";

}
