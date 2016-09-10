package com.zheng.eventbus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import com.google.common.base.Charsets;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class UserThread extends Thread {

	private Socket socket = null;
	private EventBus bus = null;
	private BufferedReader reader = null;
	private PrintWriter writer = null;

	public UserThread(Socket socket, EventBus bus) throws IOException {
		this.socket = socket;
		this.bus = bus;

		reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charsets.UTF_8));
		writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
	}

	@Subscribe
	public void printMessage(String message) {
		if(writer != null) {
			System.out.println("收到服务器发送过来的消息：" + message);
			writer.println(message);
			writer.flush();
		}
	}
	
	@Override
	public void run() {
		String msg = null;
		try {
			while((msg = reader.readLine()) != null) { //去到消息
				bus.post(msg);
				if(msg.equals("bye")) {
					bus.post("you have logged out just now...");
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		bus.unregister(this);
		try {
			writer.close();
			reader.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
