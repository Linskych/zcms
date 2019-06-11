package com.iotzc.zcms.redis;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisCacheManager {
    
    @Value("${spring.zcms.common.redis-prefix}")
    private String keyPrefix;
    
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    
//***************key-value operation************************
    
    public String get(String key) {
        return redisTemplate.opsForValue().get(createKey(key));
    }
    
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(createKey(key), value);
    }
    
    public void setEx(String key, String value, long seconds) {
        redisTemplate.opsForValue().set(createKey(key), value, seconds, TimeUnit.SECONDS);
    }
    
    public Boolean setNx(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(createKey(key), value);
    }
    
    public Boolean setNxEx(String key, String value, long seconds) {
        return redisTemplate.opsForValue().setIfAbsent(createKey(key), value, seconds, TimeUnit.SECONDS);
    }
    
    public Boolean setXx(String key, String value) {
        return redisTemplate.opsForValue().setIfPresent(createKey(key), value);
    }
    
    public Boolean setXxEx(String key, String value, long seconds) {
        return redisTemplate.opsForValue().setIfPresent(createKey(key), value, seconds, TimeUnit.SECONDS);
    }
    
    public Boolean del(String key) {
        return redisTemplate.delete(createKey(key));
    }
    
    public Long del(Collection<String> keys) {
        return redisTemplate.delete(keys);
    }
    
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }
    
    
    
    
    
    
    
    
    private String createKey(String key) {
        return keyPrefix + key;
    }
}

















