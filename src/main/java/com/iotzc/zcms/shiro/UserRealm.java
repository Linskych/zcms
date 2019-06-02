package com.iotzc.zcms.shiro;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.iotzc.zcms.exception.AccountException;
import com.iotzc.zcms.model.User;
import com.iotzc.zcms.service.UserService;
import com.iotzc.zcms.util.ConstantData;
import com.iotzc.zcms.util.ServerCode;

public class UserRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(UserRealm.class);
    
    @Autowired
    private UserService userService;
    /**
     * 鉴权
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        System.out.println("doGetAuthorizationInfo.................");
        log.info("doGetAuthorizationInfo.................");
        return null;
    }

    /**
     * 身份认证
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("doGetAuthenticationInfo.................");
        if (null == token.getPrincipal()) {
            return null;
        }
        String account = token.getPrincipal().toString();
        User user = userService.getByPhone(account);
        if (null == user) {
            throw new AccountException();
        }
        if (ConstantData.USER_STATUS_INVALID == user.getStatus()) {
            throw new AccountException(ServerCode.ACCOUNT_INVALID, "无效账号，请确认");
        }
        SimpleAuthenticationInfo authc = new SimpleAuthenticationInfo(
                user.getPhone(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
        return authc;
    }

}
