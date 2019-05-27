package com.learning.netty.p2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.junit.Test;

public class ServerDemo {

    public void open() {
        ServerBootstrap server = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup worker = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors()+1);
        server.group(boss,worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        //字符串解码和编码
                        pipeline.addLast("decoder", new StringDecoder());
                        pipeline.addLast("encoder", new StringEncoder());
                        pipeline.addLast(new ServerHandler());
                    }
                });

        try {
            ChannelFuture future = server.bind(8888);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static class ServerHandler extends SimpleChannelInboundHandler {

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("RemoteAddress : " + ctx.channel().remoteAddress() + " active !");
            ctx.writeAndFlush("连接成功！");
            super.channelActive(ctx);
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("收到client消息：" + msg);
        }

    }

    public static void main(String[] args) {
        ServerDemo serverDemo = new ServerDemo();
        serverDemo.open();
    }
}
