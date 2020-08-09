package com.epsoft.demo.thread;

import java.util.concurrent.CountDownLatch;
//生产bug修改标记
public class CountDownlatchDemo1 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(3);


        new Thread(()->{
            System.out.println("挂号");
            cdl.countDown();
        }).start();


        new Thread(()->{
            System.out.println("看病");
            cdl.countDown();
        }).start();

        new Thread(()->{
            System.out.println("拿药");
            cdl.countDown();
        }).start();

        cdl.await();

        System.out.println("回家");

    }
}
