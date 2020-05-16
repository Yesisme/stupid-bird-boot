package com.epsoft.demo.factory;

public class ChineseCar extends Car{

    @Override
    void createGlass() {
        System.out.println("中国汽车的台玻玻璃");
    }

    @Override
    void createShoe() {
        System.out.println("中国汽车的浦林轮胎");
    }

    @Override
    void createEngine() {
        System.out.println("中国汽车的东方发动机");
    }
}
