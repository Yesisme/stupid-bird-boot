package com.epsoft.demo.pattern.factory.abstartFactory;

public class CarElementFactory implements Element{

	@Override
	public Wheel getWheel() {
		
		return new CarWheel();
	}

	@Override
	public Engine getEngine() {
		
		return new CarEngine();
	}

	
}
