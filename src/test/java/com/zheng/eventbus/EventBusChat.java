package com.zheng.eventbus;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

/**
 * 基于事件总线的消息聊天系统实现
 * @author zhenglian
 *
 */
public class EventBusChat {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		EventBus bus = new AsyncEventBus(Executors.newFixedThreadPool(3));
//		EventBus bus = new EventBus();
		ServerSocket serverSocket = null;
		serverSocket = new ServerSocket(2000);
		Socket socket = null;
		UserThread thread = null;
		while(true) {
			socket = serverSocket.accept();
			thread = new UserThread(socket, bus);
			bus.register(thread);
			thread.start();
		}
	}
}
