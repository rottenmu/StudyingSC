package com.example.samplescaservice2.hystrix;

import com.example.samplescaservice2.feign.Sample1Feign;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 熔斷方式1
 */
@Component
public class SampleFallbackFactory implements FallbackFactory<Sample1Feign> {
    @Override
    public Sample1Feign create(Throwable throwable) {
        return new Sample1Feign() {
            @Override
            public String hello(String name) {
                return throwable.getMessage().concat(" 服务熔断");
            }
        };
    }
}
