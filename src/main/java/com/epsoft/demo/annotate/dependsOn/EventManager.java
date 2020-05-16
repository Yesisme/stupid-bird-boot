package com.epsoft.demo.annotate.dependsOn;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

//事件管理类，维护监听器列表，通过单例方法获取事件管理器，可以增加监听器或发布事件
public class EventManager {

	private final List<Consumer<String>> listeners = new ArrayList<>();
	
	private EventManager() {};
	
	public static EventManager getInstance() {
		return SingletonHodler.INSTANCE;
	}
	
	private static class SingletonHodler {
		private static final EventManager INSTANCE = new EventManager();
	}
	
	public void publish(final String message) {
		listeners.forEach(l->l.accept(message));
	}
	
	public void addListener(Consumer<String> eventConsumer) {
		listeners.add(eventConsumer);
	}
}
