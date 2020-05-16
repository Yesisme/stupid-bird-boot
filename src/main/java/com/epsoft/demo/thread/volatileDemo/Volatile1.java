package com.epsoft.demo.thread.volatileDemo;

//volatile保证可见性演示
public class Volatile1 {
    private static boolean flag = false;

    public static void main(String[] args) {


        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = true;
            System.out.println(Thread.currentThread().getName() + "\t" + flag);

        }, "A").start();

        while (true) {
            //此处加同步锁也可以强制从主内存中刷新缓存
            //synchronized (Volatile1.class){
                if (flag) {
                    System.out.println(Thread.currentThread().getName() + "flag\t" + flag);
                    break;
                //}
            }

        }
    }
}
