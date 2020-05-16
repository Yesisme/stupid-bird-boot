package com.epsoft.demo.thread;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.plaf.TableHeaderUI;
//上传验证
public class ThreadVolatileTest {
    int a=0;
    boolean flag =false;

    public void read(){
        a=1; //1
        flag = true; //2
        System.out.println(a+":"+flag);
    }

    public void write(){
        if(flag){ //3
            int i = a+1;  //4
            System.out.println("i:"+i);
        }
    }

    public static void main(String[] args) {
        ThreadVolatileTest t = new ThreadVolatileTest();
        new Thread(t::read).start();
        new Thread(t::write).start();
    }
}
