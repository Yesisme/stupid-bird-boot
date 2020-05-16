package com.epsoft.demo.factory;

public class GermanyCar extends Car{


    @Override
    void createGlass() {
        System.out.println("德国汽车的德国玻璃");
    }

    @Override
    void createShoe() {
        System.out.println("德国汽车的德国轮胎");
    }

    @Override
    void createEngine() {
        System.out.println("德国汽车的德国发动机");
    }
}
