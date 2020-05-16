package com.epsoft.demo.factory;

public class Test {
    public static void main(String[] args) {
        CarFactory cdaf = new ChineseDefaultAbstartctFactory();
        Car cdafCar = cdaf.getCar();
        cdafCar.createGlass();
        cdafCar.createShoe();
        cdafCar.createEngine();

        CarFactory gdad = new GermanyDefaultAbstractFactory();
        Car gdadCar = gdad.getCar();
        gdadCar.createShoe();
        gdadCar.createEngine();
        gdadCar.createGlass();

    }
}
