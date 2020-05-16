package com.epsoft.demo.abstractFactory;

public class Test {
    public static void main(String[] args) {
        CarFactory ccFactory = new ChineseCarFactory();
        ccFactory.getEngin().produce();
        ccFactory.getGlass().produce();
        ccFactory.getShoe().produce();

        CarFactory acFactory = new AmerianCarFactory();
        acFactory.getEngin().produce();
        acFactory.getGlass().produce();
        acFactory.getShoe().produce();
    }
}
