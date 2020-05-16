package com.epsoft.demo.thread.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class SychronousQueueDemo1 {

    public static void main(String[] args) {
        BlockingQueue<String> queue = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"\t 放入1");
                queue.put("1");

                System.out.println(Thread.currentThread().getName()+"\t 放入2");
                queue.put("2");

                System.out.println(Thread.currentThread().getName()+"\t 放入3");
                queue.put("3");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        },"A").start();

        new Thread(()->{
            try {
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName()+"\t 取走1");
            queue.take();

            try { Thread.sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName()+"\t 取走2");
            queue.take();

            try { Thread.sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName()+"\t 取走2");
            queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
    }
}
