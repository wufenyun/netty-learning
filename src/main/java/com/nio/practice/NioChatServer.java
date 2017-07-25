/**
 * 
 */
package com.nio.practice;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import org.junit.Test;

/**
 * @Description: 
 * @author wufenyun
 * @date 2017年7月22日 下午12:13:49
 */

public class NioChatServer {
	
	public void start() {
		Selector selector = null;
		try {
			selector = Selector.open();
			ServerSocketChannel ssc = ServerSocketChannel.open();
			ssc.configureBlocking(false);
			ssc.socket().bind(new InetSocketAddress(9999));
			ssc.register(selector, SelectionKey.OP_ACCEPT);
			while(true) {
				selector.select();
				Iterator<SelectionKey> it = selector.selectedKeys().iterator();
				while(it.hasNext()) {
					SelectionKey skey = it.next();
					it.remove();
					if(skey.isAcceptable()) {
						handleAccept(skey);
					}
					if(skey.isReadable()) {
						handleRead(skey);
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(null != selector) {
				try {
					selector.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void handleAccept(SelectionKey skey) throws IOException {
		ServerSocketChannel ssc = (ServerSocketChannel)skey.channel();
		SocketChannel sc = ssc.accept();
		sc.configureBlocking(false);
		sc.register(skey.selector(), SelectionKey.OP_READ);
	}
	
	private void handleRead(SelectionKey skey) throws IOException {
		SocketChannel sc = (SocketChannel) skey.channel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		if(-1 != sc.read(buffer)) {
			System.out.println("接收到客户端消息：" + new String(buffer.array()));
		}
		//sc.write(ByteBuffer.wrap("hello,this is server".getBytes()));
	}
	
	@Test
	public void test() {
		new NioChatServer().start();
	}
	
}
