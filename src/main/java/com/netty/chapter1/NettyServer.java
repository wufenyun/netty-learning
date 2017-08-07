/**
 * 
 */
package com.netty.chapter1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Description: 
 * @author wufenyun
 * @date 2017年7月12日 下午11:16:09
 */
public class NettyServer {
	
	/**
	 * 启动方法
	 */
	public void startUp() {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		ServerBootstrap strap = new ServerBootstrap();
		strap.group(bossGroup, workerGroup)
		.channel(NioServerSocketChannel.class)
		.option(ChannelOption.SO_BACKLOG, 1000)
		.childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new ReadHandler());
			}
		});
		
		try {
			ChannelFuture future = strap.bind(8888).sync();
			//等待关闭
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
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
		new NettyServer().startUp();
	}
}
