package com.epsoft.demo.reflect;


import com.epsoft.demo.annotation.LymAutowired;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class UserService {

    @LymAutowired
    private DefaultJob defaultJob;

    public DefaultJob getDefaultJob() {
        return defaultJob;
    }

    public void setDefaultJob(DefaultJob defaultJob) {
        this.defaultJob = defaultJob;
    }

    public static void newInstanceBySet() throws Exception{
        Class<UserService> clazz = UserService.class;

        UserService u = clazz.newInstance();

        Field defaultJob = UserService.class.getDeclaredField("defaultJob");

        defaultJob.setAccessible(true);

        String methodName = "set" + defaultJob.getName().substring(0, 1).toUpperCase() + defaultJob.getName().substring(1,defaultJob.getName().length());

        Method method = clazz.getMethod(methodName, DefaultJob.class);

        method.invoke(u, defaultJob.getType().newInstance());

        System.out.println(u.getDefaultJob());
    }

    public static void byAutowired() throws Exception{

        Class<UserService> clazz = UserService.class;
        UserService u = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            LymAutowired annotation = field.getAnnotation(LymAutowired.class);
            if(annotation!=null){
                Object o = field.getType().newInstance();

                field.set(u,o);
            }
        }

        System.out.println(u.getDefaultJob());
    }

    public static void main(String[] args) throws Exception {

        UserService.newInstanceBySet();

        UserService.byAutowired();

    }
}
