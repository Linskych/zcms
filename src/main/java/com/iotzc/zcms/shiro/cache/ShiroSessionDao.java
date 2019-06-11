package com.iotzc.zcms.shiro.cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import com.iotzc.zcms.util.CollectionUtil;
import com.iotzc.zcms.util.JacksonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShiroSessionDao extends AbstractSessionDAO {

    @Autowired
    private ShiroRedisCache shiroRedisCache;
    
    @Override
    public void update(Session session) throws UnknownSessionException {
        saveSession(session);

    }

    @Override
    public void delete(Session session) {
        shiroRedisCache.remove(String.valueOf(session.getId()));
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Collection<String> collection = shiroRedisCache.values();
        if (CollectionUtil.isEmpty(collection)) {
            return null;
        }
        List<Session> list = new ArrayList<>();
        for (String session : collection) {
            list.add(JacksonUtil.fromJson(session, Session.class));
        }
        return list;
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (null == sessionId) {
            log.error("shiro session id is null");
            return null;
        }
        String sessionJson = shiroRedisCache.get(String.valueOf(sessionId));
        if (null == sessionJson) {
            log.error("shiro session is expired");
            return null;
        }
        return JacksonUtil.fromJson(sessionJson, Session.class);
    }
    
    private void saveSession(Session session) {
        if (null == session || null == session.getId()) {
            log.error("shiro session or session id is null");
            return;
        }
        String value = JacksonUtil.toJson(session);
        session.setTimeout(ShiroRedisCache.getSessionExpire()*1000L);
        shiroRedisCache.put(String.valueOf(session.getId()), value);
    }

}
