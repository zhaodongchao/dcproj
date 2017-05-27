package org.dongchao.web.security.redis;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Created by zhaodongchao on 2017/5/22.
 */
public class ShardedJedisManager {
    private ShardedJedisPool shardedJedisPool ;
    public synchronized ShardedJedis getShardedJedis(){
        ShardedJedis shardedJedis = null ;
        try {
            shardedJedis = shardedJedisPool.getResource();
        } catch (Exception e) {
           if ( null != shardedJedis ){
               shardedJedis.close();
           }
           throw  e ;
        }
        return  shardedJedis ;
    }


    public ShardedJedisManager(ShardedJedisPool shardedJedisPool) {
        this.shardedJedisPool = shardedJedisPool;
    }
}
