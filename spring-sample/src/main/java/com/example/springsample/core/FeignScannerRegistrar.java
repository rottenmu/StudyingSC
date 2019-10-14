package com.example.springsample.core;

import com.example.springsample.annotation.EnableSample;
import com.example.springsample.annotation.Sample;
import com.example.springsample.mapper.SampleMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.ClassPathMapperScanner;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FeignScannerRegistrar implements ResourceLoaderAware, ImportBeanDefinitionRegistrar {

    private ResourceLoader resourceLoader;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableSample.class.getName()));
        ClassPathSimpleScanner scanner = new ClassPathSimpleScanner(registry);
        AnnotationTypeFilter annotationTypeFilter = new AnnotationTypeFilter(EnableSample.class);
        final Class[] clients = attributes == null ? null
                : (Class[]) attributes.get("basePackageClasses");
        if (clients == null || clients.length == 0) {
            scanner.addIncludeFilter(annotationTypeFilter);
        }
        scanner.setBasePackageClasses(clients);
        String basepackage = attributes.getString("basepackage");
        List<String> basePackages = new ArrayList<String>();
        basePackages.add(basepackage);
        for (Class<?> clazz : attributes.getClassArray("basePackageClasses")) {
            basePackages.add(ClassUtils.getPackageName(clazz));
        }

        Class<? extends Annotation> annotationClass = attributes.getClass("annotationClass");
        if (!Annotation.class.equals(annotationClass)) {
            scanner.setAnnotationClass(annotationClass);
        }
        scanner.registerFilters();
        Set<BeanDefinitionHolder> beanDefinitionHolders = scanner.doScan(StringUtils.toStringArray(basePackages));
        System.out.println("holders - >" + beanDefinitionHolders.toString());
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(SampleFactoryBean.class);
        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(SampleMapper.class);
        registry.registerBeanDefinition("sampleMapper", beanDefinition);


    }
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {

        this.resourceLoader = resourceLoader;
    }


    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
