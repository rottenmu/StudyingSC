package com.example.springsample.core;

import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Set;

public class ClassPathSimpleScanner extends ClassPathBeanDefinitionScanner {

    private String basepackage;

    private RequestMethod mehtod;

    private Class<? extends Annotation> annotationClass;

    private Class<?>[] basePackageClasses;
    private SampleFactoryBean factoryBean = new SampleFactoryBean();


    public ClassPathSimpleScanner(BeanDefinitionRegistry registry) {
        super(registry,false);
    }

    @Override
    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);

        if (beanDefinitions.isEmpty()) {
            logger.warn("No sample component was found in '" + Arrays.toString(basePackages) + "' package. Please check your configuration.");
        } else {
            processBeanDefinitions(beanDefinitions);
        }
        return beanDefinitions;
    }

    private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
        GenericBeanDefinition definition;
        for (BeanDefinitionHolder holder : beanDefinitions) {
            definition = (GenericBeanDefinition) holder.getBeanDefinition();
            definition.getConstructorArgumentValues().addGenericArgumentValue(definition.getBeanClassName());
            definition.setBeanClass(this.factoryBean.getClass());
        }
    }

    public void registerFilters() {
        boolean acceptAllInterfaces = true;

        // if specified, use the given annotation and / or marker interface
        if (this.annotationClass != null) {
            addIncludeFilter(new AnnotationTypeFilter(this.annotationClass));
            acceptAllInterfaces = false;
        }
        if (acceptAllInterfaces) {
            // default include filter that accepts all classes
            addIncludeFilter(new TypeFilter() {
                @Override
                public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                    return true;
                }
            });
        }

        // exclude package-info.java
        addExcludeFilter(new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                String className = metadataReader.getClassMetadata().getClassName();
                return className.endsWith("package-info");
            }
        });
    }


    public void setFactoryBean(SampleFactoryBean factoryBean) {
        this.factoryBean = factoryBean != null ? factoryBean : new SampleFactoryBean();
    }

    public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }


    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        boolean independent = beanDefinition.getMetadata().isIndependent();
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }

    public void setBasepackage(String basepackage) {
        this.basepackage = basepackage;
    }

    public String getBasepackage() {
        return basepackage;
    }

    public Class<?>[] getBasePackageClasses() {
        return basePackageClasses;
    }

    public void setBasePackageClasses(Class<?>[] basePackageClasses) {
        this.basePackageClasses = basePackageClasses;
    }

    public void setMehtod(RequestMethod mehtod) {
        this.mehtod = mehtod;
    }

    public RequestMethod getMehtod() {
        return mehtod;
    }
}
