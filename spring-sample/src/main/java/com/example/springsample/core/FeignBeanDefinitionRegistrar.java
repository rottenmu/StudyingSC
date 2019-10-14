package com.example.springsample.core;

import com.example.springsample.annotation.EnableSample;
import com.example.springsample.mapper.SampleMapper;
import com.example.springsample.service.FooService;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;

public class FeignBeanDefinitionRegistrar   implements ResourceLoaderAware , ImportBeanDefinitionRegistrar {

    private  ResourceLoader resourceLoader;
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableSample.class.getName()));
        String basepackage = attributes.getString("basepackage");

        ClassPathSimpleScanner scanner = new ClassPathSimpleScanner(registry);
        Set<BeanDefinitionHolder> beanDefinitionHolders = scanner.doScan(basepackage);
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(SampleFactoryBean.class);
        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(SampleMapper.class);
        registry.registerBeanDefinition("sample",beanDefinition);
//        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(SampleFactoryBean.class);
//        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
//        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue("com.example.demo.dao.TestDao");
//        registry.registerBeanDefinition("test", beanDefinition);

    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {

        this.resourceLoader = resourceLoader;
    }


    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
