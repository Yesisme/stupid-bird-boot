package com.epsoft.demo.factory;

public class ChineseDefaultAbstartctFactory extends CarFactory{

    @Override
    Car getCar() {
        return new ChineseCar();
    }
}
