package com.example.springsample.annotation;

import com.example.springsample.core.FeignScannerRegistrar;
import com.example.springsample.core.SampleBeanDefinitionRegistrar;
import com.example.springsample.core.SimpleBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(FeignScannerRegistrar.class)
public @interface EnableSample {

    String basepackage() default "";

    RequestMethod mehtod() default RequestMethod.GET;

    Class<? extends Annotation> annotationClass() default Annotation.class;
    Class<?>[] basePackageClasses() default {};
}
