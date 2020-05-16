package com.epsoft.demo.thread.produceConsumer.ProduceConsumer2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource {
    private volatile boolean FLAG = true;

    private AtomicInteger atomic = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() {
        String data = null;
        boolean reValue;
        while (FLAG) {
            data = atomic.incrementAndGet()+"";
            try {
                reValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
                if (reValue) {
                    System.out.println(Thread.currentThread().getName() + "\t" + "生产者生产" + data + "成功");
                } else {
                    System.out.println(Thread.currentThread().getName() + "\t" + "生产者生产" + data + "失败");
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\t 大老板叫停了,停止生产!");
    }

    public void myConsumer() {
        String result = null;
        while (FLAG) {
            try {
                result = blockingQueue.poll(2L, TimeUnit.SECONDS);
                if(null==result || result.equalsIgnoreCase("")){
                    FLAG = false;
                    System.out.println(Thread.currentThread().getName()+"\t 超过两秒钟没有消费超时退出");
                    return;
                }
                System.out.println(Thread.currentThread().getName()+"\t 消费者消费"+result+"成功！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        this.FLAG = false;
    }

}

public class ProdConsumer_BlockingQueueDemo {

    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<String>(10));

        new Thread(()->{
            System.out.println("\t 生产者线程启动");
            myResource.myProd();
        },"prod").start();

        new Thread(()->{
            System.out.println();
            System.out.println();
            System.out.println("\t 消费者线程启动");
            myResource.myConsumer();

        },"comsumer").start();

        try { Thread.sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("\t 5秒中结束,main大老板说活动结束了");
        myResource.stop();
    }
}
