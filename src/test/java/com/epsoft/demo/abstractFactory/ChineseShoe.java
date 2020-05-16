package com.epsoft.demo.abstractFactory;

public class ChineseShoe extends Shoe{
    @Override
    void produce() {
        System.out.println("生产中国汽车的轮胎");
    }
}
