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
    private JedisManager redisManager;

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = getSessionIdGenerator().generateId(session);
        assignSessionId(session,sessionId);
        redisManager.save(sessionId.toString().getBytes(), (int) session.getTimeout(), sessionToByte(session));
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        byte[] session = redisManager.get(sessionId.toString().getBytes());
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
        redisManager.update(sessionId.toString().getBytes(), sessionToByte(session));
    }

    @Override
    public void delete(Session session) {
       redisManager.delete(session.getId().toString());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<byte[]> bytesSessions = redisManager.getAll();
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
}
