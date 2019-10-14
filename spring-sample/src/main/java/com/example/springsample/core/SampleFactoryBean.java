package com.example.springsample.core;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

public class SampleFactoryBean implements FactoryBean {

    Class aClass;

    public  SampleFactoryBean(){

    }
    public SampleFactoryBean(Class aClass) {
        this.aClass = aClass;
    }

    @Override
    public Object getObject() throws Exception {

        Class[] classes = new Class[]{aClass};
        return   Proxy.newProxyInstance(SampleFactoryBean.class.getClassLoader(),classes,new SampleHandler());
    }

    @Override
    public Class<?> getObjectType() {
        return this.aClass;
    }
}
