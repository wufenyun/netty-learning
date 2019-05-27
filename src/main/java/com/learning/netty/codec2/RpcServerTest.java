package com.learning.netty.codec2;

import org.junit.Test;

/**
 * @description:
 * @author: wufenyun
 * @date: 2018-07-04 13
 **/
public class RpcServerTest {

    @Test
    public void start() throws InterruptedException {
        RpcServer rpcServer = new RpcServer();
        rpcServer.init();
        rpcServer.start(8888);
        Thread.sleep(10000000);
    }
}