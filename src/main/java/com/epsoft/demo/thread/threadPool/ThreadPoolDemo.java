package com.epsoft.demo.thread.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThreadPoolDemo {

    public static void main(String[] args) {

        //固定线程线程池
        //ExecutorService executor = Executors.newFixedThreadPool(10);
        //单个线程的线程池
        //ExecutorService executor = Executors.newSingleThreadExecutor();

        //多个线程的线程池
        ExecutorService executor = Executors.newCachedThreadPool();

        try {
            for (int i = 1; i <=20 ; i++) {
                final int tempInt = i;
                executor.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"\t"+tempInt);
                });
            }
        } catch (Exception e) {

        } finally {
            executor.shutdown();
        }


    }
}
