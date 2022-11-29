package com.kkcloud.risk.vo;

public class ParentCommonStatusCode {

    public static final ServiceStatusCode SUCCESS = new ServiceStatusCode(200, "Successful Operation");
    public static final ServiceStatusCode FAILURE = new ServiceStatusCode(500, "An exception occurred in the server side!");
    public static final ServiceStatusCode NOT_ACCEPTABLE = new ServiceStatusCode(406,"The Input Data is Not Acceptable!");
    public static final ServiceStatusCode UNAUTHORIZED = new ServiceStatusCode(10901, "Username or Password is Incorrect");
    public static final ServiceStatusCode FORBIDDEN = new ServiceStatusCode(10903, "Authorization Failed");
    public static final ServiceStatusCode USER_EXIST = new ServiceStatusCode(109011, "User Account Already Exist");
    public static final ServiceStatusCode NULL_VALUE = new ServiceStatusCode(109011, "No Data Found");
    public static final ServiceStatusCode INVALID_INPUT = new ServiceStatusCode(109000, "Invalid Input");
    public static final ServiceStatusCode TOO_LARGE_FILE = new ServiceStatusCode(10409, "File too large!");
    public static final ServiceStatusCode INVALID_USER = new ServiceStatusCode(10403, "Incorrect Username or Password.");
    public static final ServiceStatusCode INVALID_FILE = new ServiceStatusCode(10404, "Please upload a csv file!");
}
