# JiraTracker

converting to c# code

Jira Reporting Tool (Java code already available)
Steps:
1.	Fetch data from the Jira (~800-900 Jira issues) (return: List<CustomJiraIssue>)
•	Given code uses the JIRA JAVA Rest client. (only available in java)
•	We can use JIRA rest client for C#
•	Or can directly use jira client using the below package:
<PackageReference Include="atlassian.sdk" Version="10.5.0" />
•	Dummy code:
var jira = Jira.CreateRestClient(jUrl, jUserID, jPassword);
Task<IPagedQueryResult<Issue>> issues= jira.Issues.GetIssuesFromJqlAsync(query,1000);
•	Query:
"project='Academic Technology' AND (resolved is EMPTY OR resolved >StartOfMonth(-1))"

2.	Fetch records from the last run report (excelsheet: “list from”) (return: List<ExcelRecord>)
•	Used ExcelDataReader Api for this.
<PackageReference Include="ExcelDataReader" Version="3.5.0" />
•	Mark all the jira issues as new which are not found in the excel sheet record list.

3.	Get records from the other spread sheets as a single list (excelsheet: “List from” and “NEW”)(return :List<ExcelRecord>).
•	CleanUp: Also filter the records on the below 2 conditions
i.	prevStatus: Closed or abandoned 
ii.	currStatus: Open and abandoned

4.	Filter and write the list of jira issues fetched on the step 1.
•	IsNew= “New” and status!= “open” and epicLink==null
•	UpdatedDate > LastReportRunDate
•	Finally write the filtered list to the output report - NEW spreadsheet.

EPICS
5.	Again export the jira issues using query= project=’Academic Technology’ AND type=Epic
•	Simply convert the CustomJiraIssue to the ExcelRecord
•	Write the ExcelRecord list the output report – PROJECTS spreadsheet.

