package com.zheng.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * 事件监听器
 * 也就是作为消费者，监听TestEvent事件
 * @author zhenglian
 */
public class EventListener {

	@Subscribe
	public void listen(TestEvent event) {
		String msg = event.getMessage();
		System.out.println("listen 收到TestEvent消息内容: " + msg);
	}
	
	@Subscribe
	public void listen1(TestEvent event) {
		String msg = event.getMessage();
		System.out.println("listen2 收到TestEvent消息内容: " + msg);
	}
	
}
