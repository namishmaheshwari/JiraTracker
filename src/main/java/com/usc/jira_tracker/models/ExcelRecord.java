package com.usc.jira_tracker.models;

import com.google.gson.annotations.SerializedName;

public class ExcelRecord {

    @SerializedName("Key")
    String key;
    @SerializedName("Summary")
    String summary;
    @SerializedName("Quarter")
    String quarter;
    @SerializedName("Status")
    String status;
    @SerializedName("Previous Status")
    String prevStatus;
    @SerializedName("Current Status")
    String currStatus;
    @SerializedName("Department")
    String department;

    public ExcelRecord(String key, String summary, String quarter, String prevStatus, String currStatus, String department) {
        this.key = key;
        this.summary = summary;
        this.quarter = quarter;
        this.prevStatus = prevStatus;
        this.currStatus = currStatus;
        this.department = department;
    }

    public ExcelRecord(String key, String summary, String quarter, String status,String department) {
        this.key = key;
        this.summary = summary;
        this.quarter = quarter;
        this.prevStatus = null;
        this.currStatus = null;
        this.status=status;
        this.department = department;
    }

    public String getKey() {
        return key;
    }

    public String getSummary() {
        return summary;
    }

    public String getQuarter() {
        return quarter;
    }

    public String getStatus() {
        return status;
    }

    public String getPrevStatus() {
        return prevStatus;
    }

    public String getCurrStatus() {
        return currStatus;
    }

    public String getDepartment() {
        return department;
    }

    public void setCurrStatus(String currStatus) {
        this.currStatus = currStatus;
    }

    public String getHeader(String key){
        if(key.equals("Key"))
            return getKey();
        else if(key.equals("Summary"))
                return getSummary();
            else if(key.equals("Quarter"))
                    return getQuarter();
                else if (key.equals("Status"))
                        return getStatus();
                    else if (key.equals("Previous Status"))
                            return getPrevStatus();
                        else if(key.equals("Current Status"))
                                return getCurrStatus();
                            else if(key.equals("Department"))
                                    return getDepartment();
                                else
                                    System.out.println("Error");
        return null;
    }
}
