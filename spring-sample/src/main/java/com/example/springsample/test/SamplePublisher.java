package com.example.springsample.test;

import com.example.springsample.conf.AppConf;
import com.example.springsample.mapper.SampleMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SamplePublisher {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConf.class);
        SampleMapper sample = applicationContext.getBean("sampleMapper", SampleMapper.class);
        System.out.println(sample.select());
        applicationContext.start();
    }
}
