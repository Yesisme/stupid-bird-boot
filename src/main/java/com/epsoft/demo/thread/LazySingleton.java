package com.epsoft.demo.thread;

//DCL DOUBLE CHECK LOCK
public class LazySingleton {

    private volatile static LazySingleton instance = null;

    private LazySingleton(){
        System.out.println(Thread.currentThread().getName()+" \t 我是构造方法被调用了");
    }

    private static LazySingleton getInstance(){
        if(instance==null){
            synchronized (LazySingleton.class){
                if(instance==null){

                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i <10 ; i++) {
            new Thread(()->{
                LazySingleton.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
