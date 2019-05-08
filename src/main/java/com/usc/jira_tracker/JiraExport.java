package com.usc.jira_tracker;

import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.JiraRestClientFactory;
import com.atlassian.jira.rest.client.domain.*;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.usc.jira_tracker.models.CustomJiraIssue;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


import static com.usc.jira_tracker.utils.Constants.JIRA_ADMIN_PASSWORD;
import static com.usc.jira_tracker.utils.Constants.JIRA_ADMIN_USERNAME;
import static com.usc.jira_tracker.utils.Constants.JIRA_URL;

public class JiraExport {

    public static List<CustomJiraIssue> getJiraIssues(String query) throws InterruptedException, JSONException {

        // Print usage instructions
        StringBuilder intro = new StringBuilder();
        intro.append("**********************************************************************************************\r\n");
        intro.append("* JIRA Java REST Client ('JRJC') example.                                                    *\r\n");
        intro.append("* NOTE: Start JIRA using the Atlassian Plugin SDK before running this example.               *\r\n");
        intro.append("* (for example, use 'atlas-run-standalone --product jira --version 6.0 --data-version 6.0'.) *\r\n");
        intro.append("**********************************************************************************************\r\n");
        System.out.println(intro.toString());

        // Construct the JRJC client
        System.out.println(String.format("Logging in to %s with username '%s' and password '********'", JIRA_URL, JIRA_ADMIN_USERNAME));
        JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
        URI uri = null;
        try {
            uri = new URI(JIRA_URL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        JiraRestClient client = factory.createWithBasicHttpAuthentication(uri, JIRA_ADMIN_USERNAME, JIRA_ADMIN_PASSWORD);

        //getting all issues with jql
        int i=0,total=0;
        List<CustomJiraIssue> customJiraIssues= new ArrayList<CustomJiraIssue>();
        while(i==0 || i<total-650){
            SearchResult searchResult=client.getSearchClient().searchJql(query,40,i).claim();
            System.out.println(searchResult.getTotal());
            total=searchResult.getTotal();
            i=i+40;
            System.out.println("i="+i);
            Iterable<BasicIssue> issues=searchResult.getIssues();

            for(BasicIssue issueKey: issues){
                System.out.println(issueKey.getKey());
                Issue issue = factory.createWithBasicHttpAuthentication(uri, JIRA_ADMIN_USERNAME, JIRA_ADMIN_PASSWORD).getIssueClient().getIssue(issueKey.getKey()).claim();
                String key=issue.getKey();
                String issueType=issue.getIssueType().toString();
                String priority= issue.getPriority().toString();
                String summary = issue.getSummary();

                String projectManager=null;
//                if(issue.getField("customfield_11119").getValue()!=null){
//                    projectManager=issue.getField("customfield_11119").getValue().toString();
//                }

                String userContact=null;
//                if(issue.getField("customfield_10237").getValue()!=null){
//                    userContact=issue.getField("customfield_10237").getValue().toString();
//                }
                String status= issue.getStatus().getName();

                String storyPoints=null;
//                if(issue.getField("customfield_10312").getValue()!=null){
//                    storyPoints=issue.getField("customfield_10312").getValue().toString();
//                }
                String fixVersion= issue.getFixVersions().toString();
                String created = issue.getCreationDate().toString();
                String updated = issue.getUpdateDate().toString();

                String epicLink=null;
                if(issue.getField("customfield_10910").getValue()!=null){
                    epicLink=issue.getField("customfield_10910").getValue().toString();
                }

                String resolved=null;
                if(issue.getField("resolutiondate").getValue()!=null){
                    resolved= issue.getField("resolutiondate").getValue().toString();
                }
                String sprint=null;
//                if(issue.getField("customfield_10315").getValue()!=null){
//                    sprint= issue.getField("customfield_10315").getValue().toString();
//                }

                String department=null;
                if(issue.getField("customfield_11118").getValue()!=null){
                    String jsonResult=issue.getField("customfield_11118").getValue().toString();
                    department= new JSONObject(new JSONArray(jsonResult).get(0).toString()).get("value").toString();
                }

                String goLiveDate=null;
//                if(issue.getField("customfield_11111").getValue()!=null){
//                    goLiveDate= issue.getField("customfield_11111").getValue().toString();
//                }

                String leadDeveloper=null;
//                if(issue.getField("customfield_11210").getValue()!=null){
//                    leadDeveloper= issue.getField("customfield_11210").getValue().toString();
//                }

                CustomJiraIssue customJiraIssue=new CustomJiraIssue(key,issueType,priority,summary,projectManager,
                        userContact,status,storyPoints,fixVersion,created,
                        updated,epicLink,resolved,sprint,department,goLiveDate,leadDeveloper);
                customJiraIssues.add(customJiraIssue);
//                System.out.println(customJiraIssue.toString());

                Thread.sleep(150);
            }
        }

        System.out.println("Complete. Now exiting.");
        return customJiraIssues;
    }

    // Invoke the JRJC Client
//        Promise<User> promise = client.getUserClient().getUser(JIRA_ADMIN_USERNAME);
//        User user = promise.claim();

    //getting particular issue and its subtasks
//        Issue issue5=client.getIssueClient().getIssue("AT-1933").claim();
//        for (Field f:issue5.getFields()) {
//            System.out.println(f.getId()+" "+f.getName()+" "+f.getType()+" "+f.getValue()+" "+f.getClass());
//
//        }
//        Iterable<Subtask> substasks=issue5.getSubtasks();

    // Done
}
