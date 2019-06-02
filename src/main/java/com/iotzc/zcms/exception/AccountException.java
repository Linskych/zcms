package com.iotzc.zcms.exception;

import com.iotzc.zcms.util.ServerCode;

public class AccountException extends RuntimeException {

    private static final long serialVersionUID = -6758798361552517788L;

    private int code = ServerCode.ACCOUNT_PASSWORD_ERROR;
    
    public AccountException() {
        super("账号或密码错误");
    }
    
    public AccountException(String msg) {
        super(msg);
    }
    
    public AccountException(int code, String msg) {
        super(msg);
        this.code = code;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
}
