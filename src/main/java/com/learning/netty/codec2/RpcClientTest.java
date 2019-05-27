package com.learning.netty.codec2;

import io.netty.channel.Channel;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @description:
 * @author: wufenyun
 * @date: 2018-07-04 14
 **/
public class RpcClientTest {

    public static void main(String[] args) throws InterruptedException, IOException {
        RpcClient client = new RpcClient();
        client.init();
        Channel channel = client.connect("127.0.0.1",8888);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            String msg = reader.readLine();
            if(StringUtils.isBlank(msg)) {
                continue;
            }
            RpcInvocation invocation = new RpcInvocation();
            invocation.setApi("UserService.login");
            invocation.setArgs("111");
            channel.writeAndFlush(invocation);
        }
    }
}