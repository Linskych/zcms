package com.iotzc.zcms.redis;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisCacheManager {
    
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    
//***************key-value operation************************
    
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
    
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }
    
    public void setEx(String key, String value, long seconds) {
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }
    
    public void setNx(String key, String value) {
        redisTemplate.opsForValue().setIfAbsent(key, value);
    }
    
    public void setNxEx(String key, String value, long seconds) {
        redisTemplate.opsForValue().setIfAbsent(key, value, seconds, TimeUnit.SECONDS);
    }
    
    public void setXx(String key, String value) {
        redisTemplate.opsForValue().setIfPresent(key, value);
    }
    
    public void setXxEx(String key, String value, long seconds) {
        redisTemplate.opsForValue().setIfPresent(key, value, seconds, TimeUnit.SECONDS);
    }
}

















