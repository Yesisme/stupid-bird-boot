package com.epsoft.demo.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerCAS {

    public static void main(String[] args) {
        AtomicInteger atomic = new AtomicInteger(5);
        System.out.println(atomic.compareAndSet(5, 2020)+"\t current value \t"+ atomic.get());
        System.out.println(atomic.compareAndSet(5, 1014)+"\t current value \t"+atomic.get());
        atomic.getAndIncrement();


        
    }
}
