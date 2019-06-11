package com.iotzc.zcms.shiro.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;

import com.iotzc.zcms.redis.RedisCacheManager;
import com.iotzc.zcms.util.CollectionUtil;

public class ShiroRedisCache implements Cache<String, String> {

    private final static String SHIRO_REDIS_PREFFIX = "SHIRO_SESSION_";
    private final static int SHIRO_SESSION_EXPIRE = 30*60; //seconds
    
    @Autowired
    private RedisCacheManager redisCacheManager;

    @Override
    public void clear() throws CacheException {
        Set<String> keys = this.keys();
        if (CollectionUtil.isNotEmpty(keys)) {
            redisCacheManager.del(keys);
        }
    }

    @Override
    public String get(String key) throws CacheException {
        return redisCacheManager.get(createKey(key));
    }

    @Override
    public Set<String> keys() {
        return redisCacheManager.keys(createKey("*"));
    }

    @Override
    public String put(String key, String value) throws CacheException {
        redisCacheManager.setEx(createKey(key), value, getSessionExpire());
        return value;
    }

    @Override
    public String remove(String key) throws CacheException {
        String value = redisCacheManager.get(createKey(key));
        Boolean ok = redisCacheManager.del(createKey(key));
        return ok ? value : "";
    }

    @Override
    public int size() {
        Set<String> keys = this.keys();
        return null == keys ? 0: keys.size();
    }

    @Override
    public Collection<String> values() {
        Set<String> keys = this.keys();
        if (CollectionUtil.isEmpty(keys)) {
            return null;
        }
        List<String> list = new ArrayList<>();
        for (String key : keys) {
            list.add(get(createKey(key)));
        }
        return list;
    }
    
    public static int getSessionExpire() {
        return SHIRO_SESSION_EXPIRE;
    }
    
//    @Override
//    public void clear() throws CacheException {
//        
//        
//    }
//
//    @Override
//    public V get(K key) throws CacheException {
//        return (V) redisCacheManager.get(createKey(key));
//    }
//
//    @Override
//    public Set<K> keys() {
//        return (Set<K>) redisCacheManager.keys(SHIRO_REDIS_PREFFIX + "_*");
//    }
//
//    @Override
//    public V put(K arg0, V arg1) throws CacheException {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public V remove(K arg0) throws CacheException {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public int size() {
//        // TODO Auto-generated method stub
//        return 0;
//    }
//
//    @Override
//    public Collection<V> values() {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
    private String createKey(String key) {
        return SHIRO_REDIS_PREFFIX + key;
    }
}
