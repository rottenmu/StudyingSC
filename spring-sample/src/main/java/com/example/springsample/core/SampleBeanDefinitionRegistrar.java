package com.example.springsample.core;

import com.example.springsample.annotation.EnableSample;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SampleBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        System.out.println("className - >" + importingClassMetadata.getClassName());
        Set<String> annotationTypes = importingClassMetadata.getAnnotationTypes();
        MultiValueMap<String, Object> allAnnotationAttributes = importingClassMetadata.getAllAnnotationAttributes(EnableSample.class.getName());
        System.out.println(" allAnnotationAttributes - >"+allAnnotationAttributes);

        Set<MethodMetadata> annotatedMethods = importingClassMetadata.getAnnotatedMethods(EnableSample.class.getName());
        System.out.println("annotatedMethods - >"+annotatedMethods);
        Set<String> metaAnnotationTypes = importingClassMetadata.getMetaAnnotationTypes(EnableSample.class.getName());
        System.out.println("metaAnnotationTypes - >" + metaAnnotationTypes);
        boolean result = importingClassMetadata.hasAnnotation(EnableSample.class.getName());
        System.out.println(" result - >"+result);
        Package aPackage = importingClassMetadata.getAnnotationTypes().getClass().getPackage();
        System.out.println("package - >" +aPackage.toString());
        System.out.println("annotationTypes ->"+annotationTypes.toString());

        Package aPackage1 = importingClassMetadata.getClass().getPackage();
        String enclosingClassName = importingClassMetadata.getEnclosingClassName();
        Set<String> basePackages = new HashSet<>();
        basePackages.add(
                ClassUtils.getPackageName(importingClassMetadata.getClassName()));
        System.out.println("aPackage1 - > " + basePackages.toString());
      //   getBasePackages(importingClassMetadata);
    }
    protected Set<String> getBasePackages(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> attributes = importingClassMetadata
                .getAnnotationAttributes(EnableSample.class.getCanonicalName());

        Set<String> basePackages = new HashSet<>();
        for (String pkg : (String[]) attributes.get("value")) {
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg);
            }
        }
        for (String pkg : (String[]) attributes.get("basePackages")) {
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg);
            }
        }
        for (Class<?> clazz : (Class[]) attributes.get("basePackageClasses")) {
            basePackages.add(ClassUtils.getPackageName(clazz));
        }

        if (basePackages.isEmpty()) {
            basePackages.add(
                    ClassUtils.getPackageName(importingClassMetadata.getClassName()));
        }
        return basePackages;
    }

}
