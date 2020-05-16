package com.epsoft.demo.thread;
class MyLockDemo implements Runnable{
    private String lockA;

    private String lockB;

    public MyLockDemo(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"：持有锁"+lockA+"\t想获取"+lockB);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"：持有锁"+lockB+"\t想获取"+lockA);
            }
        }
    }

}
public class DeadLockDemo2 {

    public static void main(String[] args) {

        String lockA="lockA";
        String lockB="lockB";
        new Thread(new MyLockDemo(lockA,lockB),"A线程").start();
        new Thread(new MyLockDemo(lockB,lockA),"B线程").start();

    }

}
