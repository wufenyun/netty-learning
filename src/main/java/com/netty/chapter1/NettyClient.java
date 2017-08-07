/**
 * 
 */
package com.netty.chapter1;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Description: 
 * @author wufenyun
 * @date 2017年7月12日 下午11:16:09
 */
public class NettyClient {
	
	/**
	 * 启动方法
	 */
	public void startUp() {
		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap strap = new Bootstrap();
		strap.group(group)
		.channel(NioSocketChannel.class)
		.option(ChannelOption.TCP_NODELAY,true)
		.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new ReadHandler());
			}
		});
		
		try {
			//发起异步连接
			ChannelFuture future = strap.connect("127.0.0.1", 8888).sync();
			future.channel().writeAndFlush(Unpooled.copiedBuffer("hello!".getBytes()));
			//等待关闭
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}
	}
	
	/**
	* @Description: 读消息处理器
	*/
	class ReadHandler extends ChannelHandlerAdapter {
		
		@Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
			ctx.close();
	    }
		
		@Override
	    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
	        ByteBuf buf = (ByteBuf) msg;
	        byte[] dist = new byte[buf.readableBytes()];
	        buf.readBytes(dist);
	        System.out.println(new String(dist));
	    }
	}
	
	public static void main(String[] args) {
		new NettyClient().startUp();
	}
}
