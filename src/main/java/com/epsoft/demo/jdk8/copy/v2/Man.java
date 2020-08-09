package com.epsoft.demo.jdk8.copy.v2;

public class Man {

    public static void get(Cook cook){
        cook.doCook();
    }

    public static void main(String[] args) {
        get(()->{
            System.out.println("厨子做饭");
        });


    }
}
