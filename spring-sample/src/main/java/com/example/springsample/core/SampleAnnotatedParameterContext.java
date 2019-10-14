package com.example.springsample.core;

import com.example.springsample.annotation.SampleAnnotatedParamProcessor;
import feign.MethodMetadata;

import java.util.Collection;

public class SampleAnnotatedParameterContext implements SampleAnnotatedParamProcessor.AnnotatedParameterContext {


    private MethodMetadata methodMetadata;

    private Integer parameterIndex;

    public SampleAnnotatedParameterContext() {

    }

    public SampleAnnotatedParameterContext(MethodMetadata methodMetadata, Integer parameterIndex) {

        this.methodMetadata = methodMetadata;
        this.parameterIndex = parameterIndex;
    }

    @Override
    public MethodMetadata getMethodMetadata() {
        return methodMetadata;
    }

    @Override
    public int getParameterIndex() {
        return parameterIndex;
    }

    @Override
    public void setParameterName(String name) {

    }

    @Override
    public Collection<String> setTemplateParameter(String name, Collection<String> rest) {
        return null;
    }
}
