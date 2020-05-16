package com.epsoft.demo.annotate.dependsOn;

//事件发布类，通过EventManager类发布事件
public class EventPublisherBean {

	public void initialize() {
		System.out.println("EventPublisherBean initializing");
		EventManager.getInstance().publish("event published from EventPublisherBean");
	}
}
