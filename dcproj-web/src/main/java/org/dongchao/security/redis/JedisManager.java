package org.dongchao.security.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.*;

/**
 * Created by zhaodongchao on 2017/5/21.
 */
public class JedisManager {

    private static Logger logger = LoggerFactory.getLogger(JedisManager.class);
    private JedisPool jedisPool;

    public JedisManager(JedisPool jedisPool) {
        if (null == jedisPool) {
            throw new IllegalArgumentException("JedisPool should not be null");
        }
        this.jedisPool = jedisPool;
    }

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

    /**
     * 保存单个键值对
     *
     * @param key     键
     * @param seconds 过期时间，单位：秒
     * @param value   值
     */
    public void save(byte[] key, int seconds, byte[] value) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (seconds <= 0) {
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
            jedis = getResource();
            result = jedis.get(key);
        } catch (Exception e) {
            logger.warn("get occurred error", key, e);
        } finally {
            returnResource(jedis);
        }

        return result;
    }
    public String get(String key) {
        return new String(this.get(key.getBytes()));
    }

    public void update(byte[] key, byte[] value) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.set(key, value);
        } catch (Exception e) {
            logger.warn("update error", key, e);
        } finally {
            returnResource(jedis);
        }
    }

    public long delete(String key) {
        Jedis jedis = null;
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

    /**
     * 查询所有key以pattern开头的值的集合
     */
    public Set<byte[]> getbByPattern(String pattern) {
        Jedis jedis = null;
        Set<byte[]> alls = new HashSet<>();
        try {
            jedis = getResource();
            Set<byte[]> keys = jedis.keys(pattern.getBytes("utf-8"));
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
    public Set<String> getSByPattern(String pattern) {
        Set<byte[]> bytes = getbByPattern(pattern);
        Set<String> result = new HashSet<>();
        for (byte[] b : bytes){
            result.add(new String(b));
        }
        return result;
    }
    /**
     * 查询所有以pattern开头的byte[]类型的key的集合
     * @param pattern byte[]类型的匹配符
     */
     public Set<byte[]> keys(byte[] pattern){
        Jedis jedis = null ;
        Set<byte[]> result = null ;
         try {
             jedis = getResource();
             result = jedis.keys(pattern);
         } catch (Exception e) {
             logger.warn("keys error",pattern, e);
         } finally {
             returnResource(jedis);
         }
         return result ;
    }

    /**
     * 查询所有以pattern开头的String类型的key的集合
     * @param pattern String类型的匹配符
     */
    public Set<String> keys(String pattern){
        Jedis jedis = null ;
        Set<String> result = null ;
        try {
            jedis = getResource();
            result = jedis.keys(pattern);
        } catch (Exception e) {
            logger.warn("keys exception",pattern, e);
        } finally {
            returnResource(jedis);
        }
        return result ;
    }
  //==============Set===================================================
    /**
     * 将set类型数据存入redis
     * @param key key
     * @param set set
     */
    public void sadd(byte[] key, Set<byte[]> set) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Pipeline pipeline = jedis.pipelined();
            for (byte[] s : set) {
                pipeline.sadd(key, s);
            }
            pipeline.sync();
        } catch (Exception e) {
            logger.warn("sadd error", key, set, e);
        } finally {
            returnResource(jedis);
        }
    }
    public Set<byte[]> smembers(byte[] key){
        Jedis jedis = null ;
        Set<byte[]> result = null;
        try {
            jedis = getResource();
            result = jedis.smembers(key);
        } catch (Exception e) {
            logger.warn("smembers error", key, e);
        } finally {
            returnResource(jedis);
        }
        return result ;
    }
//==================================Hash=========================================
     public void hset(String key ,Map<String,Object> value){
        Jedis jedis = null ;
         try {
             jedis = getResource();
             for (Map.Entry<String,Object> entry : value.entrySet()){
                 jedis.hset(key,entry.getKey(),entry.getValue().toString());
             }
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             returnResource(jedis);
         }
     }
     public Map<byte[],byte[]> hgetAll(byte[] key){
         Jedis jedis = null ;
         Map<byte[],byte[]> result = new HashMap<>();
         try {
             jedis = getResource() ;
             result = jedis.hgetAll(key);
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             returnResource(jedis);
         }
         return result;
     }
     public List<String> hmget(String key, String...fields){
         Jedis jedis = null ;
         List<String> result = new LinkedList<>();
         try {
             jedis = getResource() ;
             result = jedis.hmget(key,fields);
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             returnResource(jedis);
         }
         return result;
     }
    /**
     * 清空数据库
      */
    public void flushDB() {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.flushDB();
        } catch (Exception e) {
            logger.warn("flushDB occurred error", e);
        } finally {
            returnResource(jedis);
        }
    }


}
