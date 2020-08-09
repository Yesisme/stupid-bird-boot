package com.epsoft.demo.thread;

public class ThreadMonitor {

    public static void main(String[] args) {
        Monitor monitor1 = new Monitor();
        Monitor monitor2 = new Monitor();

        new Thread(()->{
            monitor1.getOne();
        }).start();

        new Thread(()->{
            monitor1.getTwo();
        }).start();

        new Thread(()->{
            monitor2.getThree();
        }).start();




    }
}
class Monitor{

    public synchronized void getOne(){

        System.out.println("one");
    }

    public  synchronized void getTwo(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("two");
    }

    public synchronized void getThree(){
        System.out.println("three");
    }

}
