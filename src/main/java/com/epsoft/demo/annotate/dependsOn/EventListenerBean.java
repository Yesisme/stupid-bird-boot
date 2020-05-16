package com.epsoft.demo.annotate.dependsOn;
//事件监听者，可以增加监听器。
public class EventListenerBean {

	public void initialize() {
		System.out.println("EventListenerBean initialize");
		EventManager.getInstance().addListener(s->System.out.println("event received in EventListenerBean : " + s));
	}
}
