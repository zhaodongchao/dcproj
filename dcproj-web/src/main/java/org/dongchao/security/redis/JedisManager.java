package org.dongchao.security.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhaodongchao on 2017/5/21.
 */
public class JedisManager {

    private static Logger logger = LoggerFactory.getLogger(JedisManager.class);
    private JedisPool jedisPool;

    /**
     * 获取资源
     */
    public Jedis getResource() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            logger.debug("getResource:{}", jedis);
        } catch (Exception e) {
            logger.error("getResource:{}", e);
            if (jedis != null)
                jedis.close();
            throw e;
        }
        return jedis;
    }

    public void returnResource(Jedis jedis) {
        if (null != jedis || jedis.isConnected()) {
            jedis.close();
        }
    }

    public void save(byte[] key, int seconds, byte[] value) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (seconds<=0){
                return;
            }
            jedis.setex(key, seconds, value);
        } catch (Exception e) {
            logger.warn("setex {} = {}", key, value, e);
        } finally {
            returnResource(jedis);
        }

    }

    public byte[] get(byte[] key) {
        Jedis jedis = null;
        byte[] result = new byte[0];
        try {
            result = null;
            jedis = getResource();
            result = jedis.get(key);
        } catch (Exception e) {
            logger.warn("get occurred error", key, e);
        } finally {
            returnResource(jedis);
        }

        return result;
    }

    public void update(byte[] key, byte[] value) {
        Jedis jedis = null ;
        try {
            jedis = getResource() ;
            jedis.set(key, value);
        } catch (Exception e) {
            logger.warn("update error", key, e);
        } finally {
            returnResource(jedis);
        }
    }

    public long delete(String key) {
        Jedis jedis = null ;
        long result = 0;
        try {
            jedis = getResource();
            result = jedis.del(key);
        } catch (Exception e) {
            logger.warn("delete error", key, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    public Set<byte[]> getByPattern(String pattern) {
        Jedis jedis = null ;
        Set<byte[]> alls = new HashSet<>();
        try {
            jedis = getResource();
            Set<byte[]> keys =  jedis.keys((pattern+"*").getBytes());
            for (byte[] key : keys) {
                alls.add(getResource().get(key));
            }
        } catch (Exception e) {
            logger.warn("getAll occurred error", e);
        } finally {
            returnResource(jedis);
        }
        return alls;
    }

    public void flushDB() {
        Jedis jedis = null ;
        try {
            jedis = getResource() ;
            jedis.flushDB();
        } catch (Exception e) {
            logger.warn("flushDB occurred error", e);
        } finally {
            returnResource(jedis);
        }
    }


    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

}
