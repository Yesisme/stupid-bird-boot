package com.epsoft.demo.thread.threadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {
        ThreadPoolExecutor executors = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        try {
            for (int i = 1; i <= 10; i++) {
                final int tempInt = i;
                executors.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t"+"办理业务"+tempInt);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executors.shutdown();
        }
    }
}
