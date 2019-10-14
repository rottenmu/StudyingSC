package com.example.springsample.test;

import com.example.springsample.conf.AppConf;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.support.SpringFactoriesLoader;
import sun.reflect.misc.ReflectUtil;

import java.net.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeignTest {

    public static void main(String[] args) {
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
//        applicationContext.start();

        System.out.println(HashMap.class.isAssignableFrom(Map.class));

        String factoriesResourceLocation = SpringFactoriesLoader.FACTORIES_RESOURCE_LOCATION;
        List<String> list =  SpringFactoriesLoader.loadFactoryNames(EnableAutoConfiguration.class,FeignTest.class.getClassLoader());
        list.stream().forEach(s -> {
            try {
                Class<?> aClass = FeignTest.class.getClassLoader().loadClass(s);
                if ("com.example.springsample.test.SampleAutoConfig".equals(s)) {
                    SampleAutoConfigInterface x = (SampleAutoConfigInterface)aClass.newInstance();
                    x.say();
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        });
        System.out.println(list.toString());
    }
}
