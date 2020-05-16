package com.epsoft.demo.pattern.singleton.enumSingleton;

import com.epsoft.demo.bean.entity.User;

import java.io.*;

public enum EunmSingleton {
    USER_INSTANCE;

    private User user;

    private EunmSingleton(){
        User user = new User();
    }
    public User getUser(){
        return user;
    }

    public static void main(String[] args) throws Exception {
        com.epsoft.demo.bean.entity.User user1 = EunmSingleton.USER_INSTANCE.getUser();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("F:\\singleton_enmu.txt"));
        oos.writeObject(user1);

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("F:\\singleton_enmu.txt"));
        com.epsoft.demo.bean.entity.User user2 = (com.epsoft.demo.bean.entity.User) ois.readObject();

        System.out.println(user1==user2);

        Class<EunmSingleton> eunmSingletonClass = EunmSingleton.class;
        EunmSingleton eunmSingleton = eunmSingletonClass.getDeclaredConstructor(String.class, int.class).newInstance();
        System.out.println(eunmSingleton);
    }
}
