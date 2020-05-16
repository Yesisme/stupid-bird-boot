package com.epsoft.demo.abstractFactory;

public class ChineseGlass extends Glass{
    @Override
    void produce() {
        System.out.println("生产中国玻璃");
    }
}
