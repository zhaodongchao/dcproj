package org.dongchao.web.security;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.dongchao.web.security.redis.JedisManager;

import java.io.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 自定义sessionDao，将session cache存入redis数据库
 * Created by zhaodongchao on 2017/5/21.
 */
public class MySessionDao extends AbstractSessionDAO {
    private static String KEY_PREFIX = "session_" ;
    private String keyPrefix = KEY_PREFIX ;

    private JedisManager redisManager;

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = getSessionIdGenerator().generateId(session);
        assignSessionId(session,sessionId);
        redisManager.save(setPrefix(sessionId.toString()).getBytes(), (int) session.getTimeout(), sessionToByte(session));
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        byte[] session = redisManager.get(setPrefix(sessionId.toString()).getBytes());
        if (null == session) {
            return null;
        }
        return byteToSession(session);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        if (null == session) {
            return;
        }
        Serializable sessionId = session.getId();
        redisManager.update(setPrefix(sessionId.toString()).getBytes(), sessionToByte(session));
    }

    @Override
    public void delete(Session session) {
       redisManager.delete(setPrefix(session.getId().toString()));
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<byte[]> bytesSessions = redisManager.getByPattern(this.keyPrefix);
        Set<Session> sessions = new HashSet<>();
        for (byte[] byteSession : bytesSessions) {
            sessions.add(byteToSession(byteSession));
        }
        return sessions;
    }

    // 把session对象转化为byte保存到redis中
    private byte[] sessionToByte(Session session) {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        byte[] bytes = null;
        try {
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(session);
            bytes = bo.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    // 把byte还原为session
    private Session byteToSession(byte[] bytes) {
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
        ObjectInputStream in;
        SimpleSession session = null;
        try {
            in = new ObjectInputStream(bi);
            session = (SimpleSession) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return session;
    }

    public JedisManager getRedisManager() {
        return redisManager;
    }

    public void setRedisManager(JedisManager redisManager) {
        this.redisManager = redisManager;
    }

    /**
     * 为了与其他业务不冲突，给存入数据库的key加上一个前缀来作为标识
     * @param key
     * @return
     */
    private String setPrefix(String key) {
        key= KEY_PREFIX+"_"+key;
        return key;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }
}
