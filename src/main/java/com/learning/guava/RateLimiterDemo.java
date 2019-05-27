package com.learning.guava;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

/**
 * Created by Administrator on 2019/4/10.
 */
public class RateLimiterDemo {

    private RateLimiter rateLimiter = RateLimiter.create(1);

    @Test
    public void test() {
        for (int i=0;i<20;i++) {
            acquire();
        }
    }

    private void acquire() {
        if (rateLimiter.tryAcquire()) {
            System.out.println("true");
        } else {
            System.out.println(false);
        }
    }
}
