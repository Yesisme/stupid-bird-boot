package com.epsoft.demo.thread.notifyWait;

public class Thread1 extends Thread{

    private Object obj;

    public Thread1(Object obj){
        this.obj=obj;
    }
    @Override
    public void run() {
        synchronized (obj){
            System.out.println(Thread.currentThread().getName()+"线程1开始了");
            try{
                obj.wait();
            }catch(Exception e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"线程1结束了");
        }
    }

}
