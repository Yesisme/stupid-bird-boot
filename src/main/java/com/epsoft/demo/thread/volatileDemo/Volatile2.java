package com.epsoft.demo.thread.volatileDemo;

class Number{

    protected volatile int num;

    public synchronized void add(){
        num++;
    }
}
public class Volatile2 {

    public static void main(String[] args) {
        Number number = new Number();
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                for (int j = 0; j <100 ; j++) {
                    number.add();
                }

            }).start();
        }

        for (int i = 0; i <100 ; i++) {
            new Thread(()->{
                    for (int j = 0; j <100 ; j++) {
                        number.add();
                    }
                }).start();
        }

        while (Thread.activeCount()>2)Thread.yield();

        System.out.println(number.num);




    }
}
