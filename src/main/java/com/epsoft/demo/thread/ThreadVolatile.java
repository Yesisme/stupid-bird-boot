package com.epsoft.demo.thread;

public class ThreadVolatile {

    public static boolean flag =false;//需要加volatile

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while(!flag){

            }
            System.out.println("当前线程执行完毕");
        }).start();

        Thread.sleep(100);

        new Thread(()->{
            flag=true;
            System.out.println("第二个线程执行完毕");
        }).start();
    }
}
