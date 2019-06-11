package com.iotzc.zcms.util;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.iotzc.zcms.model.User;

public class UserPasswordUtil {

    private UserPasswordUtil() {}
    
    public final static String ALGORITHM_NAME = Md5Hash.ALGORITHM_NAME;
    public final static int HASHITERATIONS = 2;
    private final static RandomNumberGenerator rng = new SecureRandomNumberGenerator();
    
    public static void initPassword(User user) {
        user.setSalt(rng.nextBytes().toHex());
        String encodedPwd = new SimpleHash(
                ALGORITHM_NAME,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                HASHITERATIONS
            ).toHex();
        user.setPassword(encodedPwd);
    }
    
    public static boolean checkPassword(User user, String password) {
        String encodedPwd = new SimpleHash(
                ALGORITHM_NAME,
                password,
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                HASHITERATIONS
            ).toHex();
        return user.getPassword().equals(encodedPwd);
    }
}
