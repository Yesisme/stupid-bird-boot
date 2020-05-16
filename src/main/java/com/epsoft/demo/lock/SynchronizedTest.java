package com.epsoft.demo.lock;


import java.util.concurrent.locks.ReentrantLock;
//可重入锁
class phone implements Runnable
{
    public synchronized void sendMsg(){
        System.out.println(Thread.currentThread().getName()+"\t 发送短信");
        sendEmail();
    }

    public synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getName()+"\t ########发送email");
    }

    ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        lock.lock();
        try {
            get();
        }finally {
            lock.unlock();
        }
    }

    public void get(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t #####invoke get");
            set();
        }finally {
            lock.unlock();
        }
    }

    public void set(){
        System.out.println(Thread.currentThread().getName()+"\t #####invoke set");
    }
}
//synchronized:默认可重入锁也叫递归锁
public class SynchronizedTest {

    public static void main(String[] args) {
        System.out.println("=============synchronized=================");

        phone p = new phone();

        new Thread(()->{
            p.sendMsg();
        },String.valueOf("t1")).start();

        new Thread(()->{
            p.sendMsg();
        },String.valueOf("t2")).start();


        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }


        System.out.println("=============RentrenLock=================");

        Thread t3 = new Thread(p,"t3");
        t3.start();
        Thread t4 = new Thread(p,"t4");
        t4.start();
    }
}
