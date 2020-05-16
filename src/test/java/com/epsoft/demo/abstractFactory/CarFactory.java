package com.epsoft.demo.abstractFactory;

public abstract class CarFactory {

    //汽车玻璃
    abstract Glass getGlass();
    
    //汽车轮胎
    abstract Shoe getShoe();

    //汽车引擎
    abstract Engine getEngin();



}
