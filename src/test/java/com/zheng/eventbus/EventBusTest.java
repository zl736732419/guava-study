package com.zheng.eventbus;

import java.util.concurrent.Executors;

import org.junit.Test;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

public class EventBusTest {

	@Test
	public void test() {
		EventBus bus =  new AsyncEventBus(Executors.newFixedThreadPool(3));
		
		EventListener listener = new EventListener();
		bus.register(listener);
		bus.register(new DeadEventListener());
		
		bus.post(new TestEvent("hello world"));
		bus.post(new TestEvent("hello guava"));
		
		bus.post(new OtherEvent("hello dead"));
		
		
		
	}
	
}
