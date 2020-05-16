package com.epsoft.demo.thread;

import lombok.SneakyThrows;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo1 {

    static class Car extends Thread{

        private int num;

        private Semaphore semaphore;

        public Car(int num,Semaphore semaphore){
            this.num=num;
            this.semaphore=semaphore;
        }

        @SneakyThrows
        @Override
        public void run() {
            semaphore.acquire();
            System.out.println("第"+num+"辆车获取到车位");
            Thread.sleep(2000);
            System.out.println("第"+num+"辆车开走回家吃饭了");
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        Semaphore se  = new Semaphore(5);
        for(int i=1;i<=10;i++){
            new Car(i,se).start();
        }
    }
}
