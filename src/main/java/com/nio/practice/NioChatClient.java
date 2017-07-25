/**
 * 
 */
package com.nio.practice;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

import org.junit.Test;

/**
 * @Description: 
 * @author wufenyun
 * @date 2017年7月22日 下午12:51:11
 */

public class NioChatClient {
	
	private Selector selector = null;
	private SocketChannel sc = null;
	
	private void init() {
		try {
			sc = SocketChannel.open();
			sc.configureBlocking(false);
			sc.connect(new InetSocketAddress("127.0.0.1", 9999));
			
			selector = Selector.open();
			sc.register(selector, SelectionKey.OP_CONNECT);
		} catch (IOException e) {
			if(null != sc) {
				try {
					sc.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} 
	}
	
	class Listener implements Runnable {
		
		private Selector selector;
		
		public Listener(Selector selector) {
			this.selector = selector;
		}
		
		public void listen() {
			try {
				while(true) {
					selector.select();
					Iterator<SelectionKey> it = selector.selectedKeys().iterator();
					while(it.hasNext()) {
						SelectionKey skey = it.next();
						it.remove();
						if(skey.isConnectable()) {
							handleConnect(skey);
						}
						if(skey.isReadable()) {
							handleRead(skey);
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				if(null != selector) {
					try {
						selector.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		private void handleConnect(SelectionKey skey) throws IOException {
			SocketChannel sc = (SocketChannel)skey.channel();
			System.out.println("客户端已经连接");
			//如果正在连接，则完成连接
			if(sc.isConnectionPending()){
				sc.finishConnect();
			}
			
			sc.configureBlocking(false);
			sc.write(ByteBuffer.wrap("hello,this is client".getBytes()));
			sc.register(skey.selector(), SelectionKey.OP_READ);
		}
		
		private void handleRead(SelectionKey skey) throws IOException {
			SocketChannel sc = (SocketChannel)skey.channel();
			ByteBuffer dst = ByteBuffer.allocate(1024);
			if(-1 != sc.read(dst)) {
				System.out.println(new String(dst.array()));
			}
		}

		public void run() {
			listen();
		}
		
	}
	
	
	class Writer implements Runnable {
		private SocketChannel sc;
		
		public Writer(SocketChannel sc) {
			this.sc = sc;
		}
		
		private void enterMsg() {
			while(true) {
				Scanner scaner = new Scanner(System.in);
				String s = scaner.nextLine();
				ByteBuffer buf = ByteBuffer.wrap(s.getBytes());
				try {
					sc.write(buf);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		public void run() {
			enterMsg();
		}
	}

	@Test
	public void test() throws InterruptedException {
		NioChatClient client = new NioChatClient();
		client.init();
		
		Thread t1 = new Thread(new Listener(client.selector));
		t1.start();
		
		Thread t2 = new Thread(new Writer(client.sc));
		t2.start();
		while(true) {
			Thread.sleep(3000);;
		}
	}
}
