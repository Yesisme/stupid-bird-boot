package com.epsoft.demo.pattern.factory.abstartFactory;

public class MotorElementFactory implements Element {

	@Override
	public Wheel getWheel() {
		
		return new MotorWheel();
	}

	@Override
	public Engine getEngine() {
		
		return new MotorEngine();
	}

}
