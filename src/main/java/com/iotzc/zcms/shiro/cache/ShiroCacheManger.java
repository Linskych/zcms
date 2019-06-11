package com.iotzc.zcms.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.iotzc.zcms.util.ApplicationContextHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShiroCacheManger implements CacheManager {

    @Autowired
    private ApplicationContextHelper applicationContextHelper;
    
    @Override
    public Cache getCache(String name) throws CacheException {
        ShiroRedisCache cache = applicationContextHelper.getBean("shiroRedisCache", ShiroRedisCache.class);
        if (null != cache) {
            return cache;
        }
        log.error("There is no bean named shiroRedisCache");
        return new ShiroRedisCache();
    }

}
