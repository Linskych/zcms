package com.iotzc.zcms.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Java相关的加密信息可以参考<a href = "https://docs.oracle.com/javase/8/docs/api/index.html">MessageDigest Algorithms</a>
 * */
public class Encoder {

    /**
     * MD5消息摘要
     * */
    public static String md5(String msg) {
        MessageDigest md5 = null;
        String encodeMsg = "";
        try {
            md5 = MessageDigest.getInstance("MD5");
            encodeMsg = byte2Hex(md5.digest(msg.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encodeMsg;
    }
    
    // 下面的方法是javax.xml.bind.DatatypeConverter.printHexBinary(byte[] data)的内部实现
    private static final char[] hexCode = "0123456789ABCDEF".toCharArray();
    private static String byte2Hex(byte[] data) {
        StringBuilder r = new StringBuilder(data.length * 2);
        for (byte b : data) {
            r.append(hexCode[(b >> 4) & 0xF]);
            r.append(hexCode[(b & 0xF)]);
        }
        return r.toString();
    }
}
