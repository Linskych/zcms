package com.iotzc.zcms.util;

public class StringUtil {

    private StringUtil() {}
    
    /**
     * change "ABC_abc" to "abcAbc"
     * */
    public static String underLine2CamelCase(String inputString) {

        if (inputString.indexOf('_') == -1) {
            return inputString;
        }
        
        StringBuilder sb = new StringBuilder();

        boolean nextUpperCase = false;
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);

            switch (c) {
            case '_':
                if (sb.length() > 0) {
                    nextUpperCase = true;
                }
                break;
            default:
                if (nextUpperCase) {
                    sb.append(Character.toUpperCase(c));
                    nextUpperCase = false;
                } else {
                    sb.append(Character.toLowerCase(c));
                }
                break;
            }
        }
        return sb.toString();
    }
}
