package com.example.samplescaservice2.hystrix;

import com.example.samplescaservice2.feign.Sample1Feign;
import org.springframework.stereotype.Component;

@Component
public class SampleFallBackStrategy2 implements Sample1Feign {
    @Override
    public String hello(String name) {
        return "sample-sca servcer is down";
    }
}
