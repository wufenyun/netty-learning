package com.learning.netty.p2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientDemo {

    public void connect(String ip,int port) {
        Bootstrap client = new Bootstrap();
        NioEventLoopGroup worker = new NioEventLoopGroup(1);
        client.group(worker)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        //字符串解码和编码
                        pipeline.addLast("decoder", new StringDecoder());
                        pipeline.addLast("encoder", new StringEncoder());
                        pipeline.addLast(new MyChannelHandler());
                    }
                });

        try {
            Channel channel = client.connect(ip,port).sync().channel();

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while(true) {
                String msg = reader.readLine();
                if(msg == null) {
                    continue;
                } else {
                    channel.writeAndFlush(msg);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ClientDemo demo = new ClientDemo();
        demo.connect("localhost",8888);
    }
}
