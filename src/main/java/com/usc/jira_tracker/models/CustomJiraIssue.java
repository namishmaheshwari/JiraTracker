package com.usc.jira_tracker.models;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.Date;

public class CustomJiraIssue {

    private String key;
    private String issueType;
    private String priority;
    private String summary;
    private String projectManager;
    private String userContact;
    private String status;
    private String storyPoints;
    private String fixVersion;
    private String created;
    private String updated;
    private String epicLink;
    private String resolved;
    private String sprint;
    private String department;
    private String goLiveDate;
    private String leadDeveloper;
    private String isNew;
    private Date updatedDate;

    /**
     * @param key
     * @param issueType
     * @param priority
     * @param summary
     * @param projectManager
     * @param userContact
     * @param status
     * @param storyPoints
     * @param fixVersion
     * @param created
     * @param updated
     * @param epicLink
     * @param resolved
     * @param sprint
     * @param department
     * @param goLiveDate
     * @param leadDeveloper
     */
    public CustomJiraIssue(String key, String issueType, String priority, String summary, String projectManager,
                           String userContact, String status, String storyPoints, String fixVersion, String created,
                           String updated, String epicLink, String resolved, String sprint, String department, String goLiveDate, String leadDeveloper) {
        this.key = key;
        this.issueType = issueType;
        this.priority = priority;
        this.summary = summary;
        this.projectManager = projectManager;
        this.userContact = userContact;
        this.status = status;
        this.storyPoints = storyPoints;
        this.fixVersion = fixVersion;
        this.created = created;
        this.updated = updated;
        this.epicLink = epicLink;
        this.resolved = resolved;
        this.sprint = sprint;
        this.department = department;
        this.goLiveDate = goLiveDate;
        this.leadDeveloper = leadDeveloper;
    }

    public String getKey() {
        return key;
    }

    public String getIssueType() {
        return issueType;
    }

    public String getPriority() {
        return priority;
    }

    public String getSummary() {
        return summary;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public String getUserContact() {
        return userContact;
    }

    public String getStatus() {
        return status;
    }

    public String getStoryPoints() {
        return storyPoints;
    }

    public String getFixVersion() {
        return fixVersion;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }

    public String getEpicLink() {
        return epicLink;
    }

    public String getResolved() {
        return resolved;
    }

    public String getSprint() {
        return sprint;
    }

    public String getDepartment() {
        return department;
    }

    public String getGoLiveDate() {
        return goLiveDate;
    }

    public String getLeadDeveloper() {
        return leadDeveloper;
    }

    public String isNew() {
        return isNew;
    }

    public void setNew(String aNew) {
        isNew = aNew;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        String res= "{" +
                "key='" + String.format("%1$"+8+ "s", key) + '\'' +
                ", issueType='" + String.format("%1$"+15+ "s", issueType) + '\'' +
                ", priority='" + String.format("%1$"+15+ "s", priority) + '\'' +
                ", summary='" + String.format("%1$"+15+ "s", summary) + '\'' +
                ", projectManager='" + String.format("%1$"+15+ "s", projectManager) + '\'' +
                ", userContact='" + String.format("%1$"+15+ "s", userContact) + '\'' +
                ", status='" + String.format("%1$"+15+ "s", status) + '\'' +
                ", storyPoints='" + String.format("%1$"+15+ "s", storyPoints) + '\'' +
                ", fixVersion='" + String.format("%1$"+15+ "s", fixVersion) + '\'' +
                ", created='" + String.format("%1$"+15+ "s", created) + '\'' +
                ", updated='" + String.format("%1$"+15+ "s", updated) + '\'' +
                ", epicLink='" + String.format("%1$"+15+ "s", epicLink) + '\'' +
                ", resolved='" + String.format("%1$"+15+ "s", resolved) + '\'' +
                ", sprint='" + String.format("%1$"+15+ "s", sprint) + '\'' +
                ", department='" + String.format("%1$"+15+ "s", department) + '\'' +
                ", goLiveDate='" + String.format("%1$"+15+ "s", goLiveDate) + '\'' +
                ", leadDeveloper='" + String.format("%1$"+15+ "s", leadDeveloper) + '\'' +
                '}';

        JSONObject jsonObj=null;
        try {
            jsonObj = new JSONObject(res);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj.toString();

    }


}
