package com.zheng.eventbus;

public class OtherEvent {
	private String message;
	public OtherEvent(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
}
