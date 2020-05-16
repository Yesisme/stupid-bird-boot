package com.epsoft.demo.thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo2 {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 国被灭");
                countDownLatch.countDown();
            },CountDownLatchEnum.getName(i)).start();
        }

        countDownLatch.await();

        System.out.println(Thread.currentThread().getName()+"\t 秦国一统中原");
    }
}
