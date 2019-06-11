package com.iotzc.zcms.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.iotzc.zcms.redis.RedisCacheManager;
import com.iotzc.zcms.shiro.UserRealm;
import com.iotzc.zcms.shiro.cache.ShiroCacheManger;
import com.iotzc.zcms.shiro.cache.ShiroRedisCache;
import com.iotzc.zcms.shiro.cache.ShiroSessionDao;
import com.iotzc.zcms.util.UserPasswordUtil;

@Configuration
public class ShiroConfiguration {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        bean.setLoginUrl("/login");
//        bean.setSuccessUrl("index");
        bean.setUnauthorizedUrl("/403");
        
        Map<String, String> filterChainMap = new LinkedHashMap<>();
        filterChainMap.put("/css/**", "anon");
        filterChainMap.put("/js/**", "anon");
        filterChainMap.put("/layuiadmin/**", "anon");
        filterChainMap.put("/views/**", "anon");
        filterChainMap.put("/favicon.ico", "anon");
        
        filterChainMap.put("/getCaptcha", "anon");
        filterChainMap.put("/check", "anon");
        filterChainMap.put("/api/**", "anon"); //客户端接口，需要token
        filterChainMap.put("/server/**", "anon"); //服务器接口，需要鉴权
        
        filterChainMap.put("/logout", "logout");
        
        filterChainMap.put("/**", "authc");
        //可以将上述的过滤链存储到数据库中，实现动态修改，不用修改代码重启应用
        
        bean.setFilterChainDefinitionMap(filterChainMap);
        
        return bean;
    }
    
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        
        manager.setSessionManager(sessionManager());
        manager.setCacheManager(cacheManager());
        manager.setRealm(userRealm());
//        manager.setRealms(realms);
        return manager;
    }
    
    @Bean
    public UserRealm userRealm() {
        UserRealm realm = new UserRealm();
        HashedCredentialsMatcher matcher =  new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName(UserPasswordUtil.ALGORITHM_NAME);
        matcher.setHashIterations(UserPasswordUtil.HASHITERATIONS);
        realm.setCredentialsMatcher(matcher);
        realm.setCachingEnabled(true);
        realm.setAuthorizationCachingEnabled(true);
        realm.setAuthorizationCachingEnabled(true);
        
        return realm;
    }
    
    @Bean
    public ShiroCacheManger cacheManager() {
        return new ShiroCacheManger();
    }
    
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(sessionDao());
        return sessionManager;
    }
    
    @Bean
    public ShiroSessionDao sessionDao () {
        return new ShiroSessionDao();
    }
    
    @Bean
    public ShiroRedisCache shiroRedisCache() { //ShiroCacheManager中的getCache()使用这个方法名作为bean名称
        return new ShiroRedisCache();
    }
}
