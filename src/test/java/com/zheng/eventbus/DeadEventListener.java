package com.zheng.eventbus;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

/**
 * 处理没有被消费者监听的事件
 * @author zhenglian
 *
 */
public class DeadEventListener {

	@Subscribe
	public void listen(DeadEvent event) {
		System.out.println(event);
		OtherEvent other = (OtherEvent) event.getEvent();
		System.out.println(other.getMessage());
	}
	
	
}
