package com.learning.redis.jedis;

import com.zbj.cache.spring.support.redis.cachecloud.exi.cluster.ClusterRedisCacheEntry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheCloudConfig {

    @Bean
    public ClusterRedisCacheEntry clusterRedisCacheEntry() {
        ClusterRedisCacheEntry clusterRedisCacheEntry = new ClusterRedisCacheEntry();
        clusterRedisCacheEntry.setAppId(105890);
        clusterRedisCacheEntry.setSoTimeout(200);
        return clusterRedisCacheEntry;
    }
}
