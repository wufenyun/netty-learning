package com.learning.netty.codec2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @description:
 * @author: wufenyun
 * @date: 2018-07-04 11
 **/
public class RpcClient {
    private ChannelFuture channelFuture;
    private Bootstrap bootstrap;

    private final EventLoopGroup workerGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors());

    public void init() {

        bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)
                .option(ChannelOption.TCP_NODELAY,true)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline channelPipeline = ch.pipeline();
                        channelPipeline.addLast("decoder",new DefaultDecoder());
                        channelPipeline.addLast("encoder",new DefaultEncoder());
                    }
                });

    }

    public Channel connect(String ip,int port) throws InterruptedException {
        channelFuture = bootstrap.connect(ip,8888).sync();
        return channelFuture.channel();
    }
}