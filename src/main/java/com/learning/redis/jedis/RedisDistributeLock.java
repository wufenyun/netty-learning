package com.learning.redis.jedis;

import com.learning.util.AssertUtil;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.params.set.SetParams;

import java.util.Collections;

public class RedisDistributeLock {

    private static final String LOCK_SUCCESS = "OK";
    private static final Long RELEASE_SUCCESS = 1L;

    public static boolean tryLock(JedisCluster jedisCluster,String lockKey, String threadUniqueId, int expireSeconds,boolean reentrant) {
        AssertUtil.notNull(jedisCluster);
        AssertUtil.notBlank(lockKey);
        AssertUtil.notBlank(threadUniqueId);

        if (reentrant) {
            jedisCluster.get(lockKey);
        }
        SetParams params = SetParams.setParams()
                .ex(expireSeconds)
                .nx();
        String result = jedisCluster.set(lockKey,threadUniqueId,params);
        return LOCK_SUCCESS.equals(result);
    }

    public static boolean unLock(JedisCluster jedisCluster,String lockKey, String threadUniqueId) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedisCluster.eval(script, Collections.singletonList(lockKey), Collections.singletonList(threadUniqueId));
        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }
}
