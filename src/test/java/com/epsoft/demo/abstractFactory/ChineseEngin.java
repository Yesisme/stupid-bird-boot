package com.epsoft.demo.abstractFactory;

public class ChineseEngin extends Engine{

    @Override
    void produce() {
        System.out.println("生产中国汽车的引擎");
    }
}
