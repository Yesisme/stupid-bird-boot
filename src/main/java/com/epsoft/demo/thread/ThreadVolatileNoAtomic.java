package com.epsoft.demo.thread;

import java.util.concurrent.atomic.AtomicInteger;

class MyData{

    int num =0;

    public void addPlus(){
        num++;
    }

    AtomicInteger integer = new AtomicInteger();

    public void addAtomicInterPlus(){
        integer.getAndIncrement();
    }
}
public class ThreadVolatileNoAtomic {

    public static void main(String[] args) {
        MyData my = new MyData();
        for (int i = 1; i <= 20; i++) {
                new Thread(()->{
                    for (int j = 1; j <=1000 ; j++) {
                        //无法保证原子性
                        my.addPlus();
                        //保证了原子性
                        my.addAtomicInterPlus();
                    }
                },String.valueOf(i)).start();
        }

        while (Thread.activeCount()>2){
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName()+"\t int type finally values is："+my.num);
        System.out.println(Thread.currentThread().getName()+"\t juc integer finally values is："+my.integer);

    }
}
