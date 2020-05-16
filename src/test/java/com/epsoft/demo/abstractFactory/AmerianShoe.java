package com.epsoft.demo.abstractFactory;

public class AmerianShoe extends Shoe{
    @Override
    void produce() {
        System.out.println("生产美国汽车的轮胎");
    }
}
