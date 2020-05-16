package com.epsoft.demo.thread;

public class DeadLock {

    static Object obj1 = new Object();
    static Object obj2 = new Object();
    public static void main(String[] args) {

        Thread t1 = new Thread(()->{
            synchronized (obj1){
                System.out.println("ob1获取了锁");
                try{
                    Thread.sleep(10);

                }catch (Exception e){
                    e.printStackTrace();
                }
                synchronized (obj2){
                    System.out.println("ob2获取了锁");
                }
            }

        });

        t1.start();

        Thread t2 = new Thread(()->{
            synchronized (obj2){
                System.out.println("ob2获取了锁");
                try{
                    Thread.sleep(10);

                }catch (Exception e){
                    e.printStackTrace();
                }
                synchronized (obj1){
                    System.out.println("ob1获取了锁");
                }
            }

        });

        t2.start();
    }
}
