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

    public void myProd() throws Exception {
        String data = null;
        boolean reValue;
        while (FLAG) {
            data = atomic.incrementAndGet()+"";
            reValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
                if (reValue) {
                    System.out.println(Thread.currentThread().getName() + "\t" + "生产者生产" + data + "成功");
                } else {
                    System.out.println(Thread.currentThread().getName() + "\t" + "生产者生产" + data + "失败");
                }
                Thread.sleep(1000);
            }
            System.out.println("\t 大老板叫停了,停止生产!");
        }

    public void myConsumer() throws Exception {
        String result = null;
        while (FLAG) {
                result = blockingQueue.poll(2L, TimeUnit.SECONDS);
                if(null==result || result.equalsIgnoreCase("")){
                    FLAG = false;
                    System.out.println(Thread.currentThread().getName()+"\t 超过两秒钟没有消费超时退出");
                    System.out.println();
                    System.out.println();
                    return;
                }
                System.out.println(Thread.currentThread().getName()+"\t 消费者消费"+result+"成功！");
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
            try {
                System.out.println("\t 生产者线程启动");
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"prod").start();

        new Thread(()->{
            System.out.println();
            System.out.println();
            System.out.println("\t 消费者线程启动");
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }

        },"comsumer").start();

        try { Thread.sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("\t 5秒中结束,main大老板说活动结束了");
        myResource.stop();
    }
}
