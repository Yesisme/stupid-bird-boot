package com.epsoft.demo.pattern.singleton.staticInnerSingleton;

import com.alibaba.druid.sql.visitor.functions.Char;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;

import java.io.*;

/**
 *静态单例
 */
@Slf4j
public class StaticSingleton implements Serializable{

    private static class innerClass{
        private static StaticSingleton instance = new StaticSingleton();
    }

    public static StaticSingleton getInstance(){
        return innerClass.instance;
    }

    private StaticSingleton(){

    }

    //解决序列化破坏单例的情况
    private Object readResolve() {
        return innerClass.instance;
    }

    public static void main(String[] args) throws Exception {
        //线程安全测试完毕
       /* for (int i = 0; i < 20; i++) {
            new Thread(()->{
                System.out.println(StaticSingleton.getInstance().hashCode());
            }).start();
        }*/

        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                try {
                    StaticSingleton staticSingleton = StaticSingleton.class.newInstance();
                    System.out.println(staticSingleton.hashCode());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        //测试是否会被序列化破坏
        StaticSingleton instance = StaticSingleton.getInstance();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("F:\\singleton_static.txt")));
        oos.writeObject(instance);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("F:\\singleton_static.txt"));
        StaticSingleton newInstance = (StaticSingleton) ois.readObject();
        log.info("instance:{}\t",instance.hashCode());
        log.info("newInstance{}\t",newInstance.hashCode());
        log.info("equals?:{}",instance==newInstance);
        ois.close();
    }

}
