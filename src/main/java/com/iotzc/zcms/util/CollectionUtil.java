package com.iotzc.zcms.util;

import java.util.Collection;
import java.util.Map;

public class CollectionUtil {

    private CollectionUtil() {}
    
    @SuppressWarnings("rawtypes")
    public static <T extends Collection> boolean isEmpty(T object) {
        if (null == object) {
            return true;
        }
        return object.isEmpty();
    }
    
    @SuppressWarnings("rawtypes")
    public static <T extends Map> boolean isEmpty(T object) {
        if (null == object) {
            return true;
        }
        return object.isEmpty();
    }
    
    @SuppressWarnings("rawtypes")
    public static <T extends Collection> boolean isNotEmpty(T object) {
        if (null == object) {
            return false;
        }
        return object.size() > 0;
    }
    
    @SuppressWarnings("rawtypes")
    public static <T extends Map> boolean isNotEmpty(T object) {
        if (null == object) {
            return false;
        }
        return object.size() > 0;
    }
}
