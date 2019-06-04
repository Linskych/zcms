package com.iotzc.zcms.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.iotzc.zcms.shiro.UserRealm;

@Configuration
public class ShiroConfiguration {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("index");
        bean.setUnauthorizedUrl("/403");
        
        Map<String, String> filterChainMap = new LinkedHashMap<>();
        filterChainMap.put("/css/**", "anon");
        filterChainMap.put("/js/**", "anon");
        filterChainMap.put("/layuiadmin/**", "anon");
        filterChainMap.put("/views/**", "anon");
        filterChainMap.put("/getCaptcha", "anon");
        filterChainMap.put("/favicon.ico", "anon");
        filterChainMap.put("/check", "anon");
        filterChainMap.put("/api/**", "anon"); //客户端接口，需要token
        filterChainMap.put("/server/**", "anon"); //服务器接口，需要鉴权
        filterChainMap.put("/logout", "logout");
        filterChainMap.put("/**", "authc");
        
        bean.setFilterChainDefinitionMap(filterChainMap);
        
        return bean;
    }
    
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(userRealm());
        return manager;
    }
    
    @Bean
    public UserRealm userRealm() {
        UserRealm realm = new UserRealm();
        HashedCredentialsMatcher matcher =  new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);
        matcher.setHashIterations(2);
        realm.setCredentialsMatcher(matcher);
        
        return realm;
    }
}
