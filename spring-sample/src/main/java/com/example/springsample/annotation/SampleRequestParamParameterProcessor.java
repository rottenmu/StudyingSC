package com.example.springsample.annotation;


import feign.MethodMetadata;
import feign.Util;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import static feign.Util.checkState;
import static feign.Util.emptyToNull;

public class SampleRequestParamParameterProcessor implements SampleAnnotatedParamProcessor {


    private static final Class<RequestParam> ANNOTATION = RequestParam.class;

    @Override
    public Class<? extends Annotation> getAnnotationType() {

        return ANNOTATION;
    }

    @Override
    public boolean processArgument(AnnotatedParameterContext context, Annotation annotation, Method method) {
        System.out.println("--------------------------------------------------");
        int parameterIndex = context.getParameterIndex();
        Class<?> parameterType = method.getParameterTypes()[parameterIndex];
        MethodMetadata methodMetadata = context.getMethodMetadata();
        if (Map.class.isAssignableFrom(parameterType)) {
            checkState(methodMetadata.queryMapIndex() == null, "Query map can only be present once.");
            methodMetadata.queryMapIndex(parameterIndex);
            return  true;
        }

        RequestParam requestParam = ANNOTATION.cast(annotation);
        String name = requestParam.value();
        /***
         *  检测 结果是否满足要求
         */
        checkState(emptyToNull(name) != null,
                "RequestParam.value() was empty on parameter %s",
                parameterIndex);

        context.setParameterName(name);
        Collection<String> query = context.setTemplateParameter(name, methodMetadata.template().queries().get(name));
        methodMetadata.template().query(name,query);
        return true;
    }
}
