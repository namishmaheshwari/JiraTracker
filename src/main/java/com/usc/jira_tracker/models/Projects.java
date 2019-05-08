package com.usc.jira_tracker.models;

import com.google.gson.annotations.SerializedName;

public class Projects {
    @SerializedName("Key")
    String key;
    @SerializedName("Summary")
    String summary;
    @SerializedName("Status")
    String status;
    @SerializedName("Department")
    String department;

    public Projects(String key, String summary, String status, String department) {
        this.key = key;
        this.summary = summary;
        this.status = status;
        this.department = department;
    }

    public String getKey() {
        return key;
    }

    public String getSummary() {
        return summary;
    }

    public String getStatus() {
        return status;
    }

    public String getDepartment() {
        return department;
    }
}
