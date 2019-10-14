package com.example.springsample.core;

import com.example.springsample.annotation.EnableSample;
import com.example.springsample.conf.AppConf;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SimpleBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {


    private ResourceLoader resourceLoader;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableSample.class.getName());//获取注解的属性

        AnnotationAttributes attributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableSample.class.getName()));

        ClassPathSimpleScanner simpleScanner = new ClassPathSimpleScanner(registry);
        simpleScanner.setBasepackage(attributes.getString("basepackage"));
        if (resourceLoader != null) {
            simpleScanner.setResourceLoader(resourceLoader);
        }
        List<String> basePackages = new ArrayList<>();
        for (String pkg : attributes.getStringArray("basepackage")) {
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg);
            }
        }
        simpleScanner.doScan(StringUtils.toStringArray(basePackages));
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


}
