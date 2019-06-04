package com.iotzc.zcms.util;

public class ServerCode {

    private ServerCode() {}
    
    public static final int SUCCESS = 0;
    public static final int COMMON_FAIL = 999; //通用错误码
    public static final int UNKNOWN_ERROR = -1; //未知错误码
    
    public static final int ACCOUNT_PASSWORD_ERROR = 1001;
    public static final int ACCOUNT_INVALID = 1002;
}
