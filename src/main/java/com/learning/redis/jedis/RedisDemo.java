package com.learning.redis.jedis;

import com.learning.util.TestBase;
import com.zbj.cache.spring.support.redis.cachecloud.exi.cluster.ClusterRedisCacheEntry;
import org.junit.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.params.set.SetParams;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RedisDemo extends TestBase {

    private static final Long RELEASE_SUCCESS = 1L;

    private ClusterRedisCacheEntry clusterRedisCacheEntry;
    private JedisCluster jedisCluster;

    @Before
    public void before() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.learning.redis.jedis");
        clusterRedisCacheEntry = context.getBean(ClusterRedisCacheEntry.class);
        jedisCluster = clusterRedisCacheEntry.getRedisCluster();
    }

    @Test
    public void string() throws InterruptedException {
        String key = "lock";
        print(getClouster().set(key,"111"));
        print(getClouster().setnx(key,"222"));
        print(getClouster().set(key,"222"));
        print(getClouster().get(key));

        print(getClouster().setex(key,1,"333"));
        Thread.sleep(1000);
        print(getClouster().get(key));

        print(getClouster().del(key));
        print(getClouster().get(key));

        SetParams params = SetParams.setParams()
                .ex(1)
                .nx();
        getClouster().set(key,"111");
        System.out.println(getClouster().set(key,"111",params));
        Thread.sleep(1000);
        print(getClouster().get(key));
        print(jedisCluster.del(key));
    }

    @Test
    public void hash() {
        String key1 = "hashKey";
        Map<String,String> map = new HashMap<>();
        map.put("hk1","hv1");
        print(jedisCluster.hmset(key1,map));
        print(jedisCluster.exists(key1));
        print(jedisCluster.hgetAll(key1));
        print(jedisCluster.hget(key1,"hk1"));
        print(jedisCluster.hset(key1,"hk1","hv11"));
        print(jedisCluster.hgetAll(key1));
        print(jedisCluster.hkeys(key1));

        print(jedisCluster.hdel(key1,"hk1"));
        print(jedisCluster.hgetAll(key1));
        print(jedisCluster.hset(key1,"hk1","hv11"));
        print(jedisCluster.hgetAll(key1));

        print(jedisCluster.hincrBy(key1,"hk2",2));
        print(jedisCluster.hgetAll(key1));

        print(jedisCluster.del(key1));
    }

    @Test
    public void list() {
        String lkey = "lk";
        print(jedisCluster.lpush(lkey,"lv1"));
        print(jedisCluster.lpush(lkey,"lv2","lv3"));
        //print(jedisCluster.lpop(lkey));
        print(jedisCluster.brpop(1,lkey));
        print(jedisCluster.brpop(1,lkey));
        print(jedisCluster.brpop(1,lkey));

        print(jedisCluster.lpush(lkey,"lv1","lv2","lv3"));
        print(jedisCluster.llen(lkey));
        print(jedisCluster.lrange(lkey,0,2));
        print(jedisCluster.del(lkey));
    }

    @Test
    public void set() {
        String skey = "skey";
        String skey2 = "skey2";
        print(jedisCluster.sadd(skey,"a","b","c"));
        print(jedisCluster.sadd(skey2,"e","d","c"));
        print(jedisCluster.scard(skey));
        print(jedisCluster.sismember(skey,"a"));
        print(jedisCluster.smembers(skey));

        //print(jedisCluster.spop(skey));
        print(jedisCluster.srem(skey,"a"));
        print(jedisCluster.smembers(skey));

        print(jedisCluster.srandmember(skey));
        print(jedisCluster.smembers(skey));
        //print(jedisCluster.sdiff(skey,skey2));
        print(jedisCluster.del(skey));
        print(jedisCluster.del(skey2));
    }

    @Test
    public void zset() {
        String zskey = "zskey";
        print(jedisCluster.zadd(zskey,1,"a"));
        print(jedisCluster.zadd(zskey,1,"b"));
        print(jedisCluster.zadd(zskey,1,"c"));
        print(jedisCluster.zadd(zskey,1,"d"));

        print(jedisCluster.zincrby(zskey,1,"b"));
        print(jedisCluster.zincrby(zskey,2,"c"));
        print(jedisCluster.zincrby(zskey,3,"d"));

        print(jedisCluster.zrange(zskey,0,1));

        print(jedisCluster.zrangeByScore(zskey,0,3));
        print(jedisCluster.del(zskey));

    }

    @Test
    public void pubsub() {
        String channel = "channel";
        print(jedisCluster.publish(channel,"msg1"));
        jedisCluster.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                super.onMessage(channel, message);
                print(channel + "  " + message);
            }
        },channel);
    }

    @Test
    public void bitmap() {
        String bkey = "bkey";
        print(jedisCluster.setbit(bkey,0,true));
        print(jedisCluster.getbit(bkey,0));
        print(jedisCluster.getbit(bkey,1));

        print(jedisCluster.setbit(bkey,1,true));
        print(jedisCluster.setbit(bkey,2,true));
        print(jedisCluster.setbit(bkey,3,false));
        print(jedisCluster.bitcount(bkey));
        print(jedisCluster.del(bkey));
    }

    @Test
    public void tx() {
        print(jedisCluster.getClusterNodes());

        Transaction tx = new Jedis().multi();
        tx.set("","");
        tx.exec();
        tx.discard();
    }

    @Test
    public void lua() {
        String luakey = "luakey";
        String lua = "if redis.call('get', KEYS[1]) =='lv' then return 1 else return 0 end";
        print(jedisCluster.eval(lua,1,luakey,"lv"));
        print(jedisCluster.get(luakey));
    }

    @Test
    public void lock() {
        String key = "distribute-lock";
        String threadUniqueId = Thread.currentThread().getName();
        try {
            boolean lock = RedisDistributeLock.tryLock(getClouster(),key,threadUniqueId,1,false);
            print(getClouster().get(key));
            if (lock) {
                print("获取锁成功");
            } else {
                print("获取锁失败");
            }
        } finally {
            boolean unlock = RedisDistributeLock.unLock(getClouster(),key,threadUniqueId);
            print(getClouster().get(key));
            if (unlock) {
                print("释放锁成功");
            } else {
                print("释放锁失败");
            }
        }

    }

    private JedisCluster getClouster() {
        return clusterRedisCacheEntry.getRedisCluster();
    }
}
