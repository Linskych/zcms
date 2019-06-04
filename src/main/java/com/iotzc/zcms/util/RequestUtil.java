package com.iotzc.zcms.util;



import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class RequestUtil {

    private RequestUtil() {}
    
    public static Integer getParamInt(HttpServletRequest request, String name) {
        return StringUtils.isEmpty(request.getParameter(name)) ? null : Integer.valueOf(request.getParameter(name));
    }
    
    public static Integer getParamInt(HttpServletRequest request, String name, Integer defaultValue) {
        return StringUtils.isEmpty(request.getParameter(name)) ? defaultValue : Integer.valueOf(request.getParameter(name));
    }
    
}
