package com.epsoft.demo.pattern.factory.abstartFactory;

public class Test {

	public static void main(String[] args) {
	
		Element car = new CarElementFactory();
		System.out.println(car.getEngine().produce());
		System.out.println(car.getWheel().produce());
	
		Element motor = new MotorElementFactory();
		System.out.println(motor.getEngine().produce());
		System.out.println(motor.getWheel().produce());
	}
}
