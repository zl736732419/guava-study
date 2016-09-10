package com.zheng.eventbus;

/**
 * 事件
 * @author zhenglian
 *
 */
public class TestEvent {

	private String message;

	public TestEvent(String msg) {
		this.message = msg;
	}

	public String getMessage() {
		return message;
	}

}
