package com.iotzc.zcms.util;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Result {

    @JsonProperty
    private int code;
    @JsonProperty
    private String message;
    @JsonProperty
    private Object data;
    
    public Result() {}
    
    public Result(int code) {
        this.code = code;
    }
    
    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public Result(int code, Object data) {
        this.code = code;
        this.data = data;
    }
    
    public Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    
    public static Result success() {
        return new Result(ServerCode.SUCCESS);
    }
    
    public static Result success(String message) {
        return new Result(ServerCode.SUCCESS, message);
    }
    
    public static Result success(Object data) {
        return new Result(ServerCode.SUCCESS, data);
    }
    
    public static Result success(String message, Object data) {
        return new Result(ServerCode.SUCCESS, message, data);
    }
    
    public static Result fail(String message) {
        return new Result(ServerCode.COMMON_FAIL, message);
    }
    
    public static Result fail(int code, String message) {
        return new Result(code, message);
    }
    
    public static Result fail(String message, Object data) {
        return new Result(ServerCode.COMMON_FAIL, message, data);
    }
    
    public static Result fail(int code, String message, Object data) {
        return new Result(code, message, data);
    }
    
    public static String jsonSucc() {
        return JacksonUtil.toJson(new Result(ServerCode.SUCCESS));
    }
    
    public static String jsonSucc(String message) {
        return JacksonUtil.toJson(new Result(ServerCode.SUCCESS, message));
    }
    
    public static String jsonSucc(Object data) {
        return JacksonUtil.toJson(new Result(ServerCode.SUCCESS, data));
    }
    
    public static String jsonSucc(String message, Object data) {
        return JacksonUtil.toJson(new Result(ServerCode.SUCCESS, message, data));
    }
    
    public static String jsonFail(String message) {
        return JacksonUtil.toJson(new Result(ServerCode.COMMON_FAIL, message));
    }
    
    public static String jsonFail(int code, String message) {
        return JacksonUtil.toJson(new Result(code, message));
    }
    
    public static String jsonFail(String message, Object data) {
        return JacksonUtil.toJson(new Result(ServerCode.COMMON_FAIL, message, data));
    }
    
    public static String jsonFail(int code, String message, Object data) {
        return JacksonUtil.toJson(new Result(code, message, data));
    }
    
    
    
}
