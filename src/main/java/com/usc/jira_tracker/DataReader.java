package com.usc.jira_tracker;

public class DataReader {

    private static DAOExcel dataExcel;

    public static DAOExcel getInstance(String fileName){

        if(dataExcel ==null){
            dataExcel = new DAOExcel(fileName);
        }
        return dataExcel;
    }
}
