package com.learning.netty.codec2;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.pool.ChannelPool;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.concurrent.ThreadFactory;

/**
 * @description:
 * @author: wufenyun
 * @date: 2018-07-04 11
 **/
public class RpcServer {

    private ChannelFuture channelFuture;
    private ServerBootstrap serverBootstrap;
    private final EventLoopGroup bossGroup = new NioEventLoopGroup(1);

    private final EventLoopGroup workerGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors());

    public void init() {

        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)
                .handler(new ServerHandler())
                .childOption(ChannelOption.TCP_NODELAY,true)
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline channelPipeline = ch.pipeline();
                        channelPipeline.addLast("decoder",new DefaultDecoder())
                                .addLast("encoder",new DefaultEncoder())
                                .addLast(new ReceiveHandler());
                    }
                });

    }

    public boolean start(int port) throws InterruptedException {
        channelFuture = serverBootstrap.bind(port).sync();
        return channelFuture.isSuccess();
    }
}