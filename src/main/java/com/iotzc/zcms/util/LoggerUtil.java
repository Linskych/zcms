package com.iotzc.zcms.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {

    private final static String API = "api";
    private final static String BUSSINESS = "bussiness";
    private final static String EXCEPTION = "exception";
    private final static String DB = "db";
    private final static String SERVER = "server";
    
    public static Logger getApiLog() {
        return LoggerFactory.getLogger(API);
    }
    
    public static Logger getBussinessLog() {
        return LoggerFactory.getLogger(BUSSINESS);
    }
    
    public static Logger getExceptionLog() {
        return LoggerFactory.getLogger(EXCEPTION);
    }
    
    public static Logger getDbLog() {
        return LoggerFactory.getLogger(DB);
    }
    
    public static Logger getServerLog() {
        return LoggerFactory.getLogger(SERVER);
    }
}
