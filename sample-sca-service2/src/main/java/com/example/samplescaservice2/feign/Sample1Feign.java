package com.example.samplescaservice2.feign;

import com.example.samplescaservice2.hystrix.SampleFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "sample-sca",fallbackFactory = SampleFallbackFactory.class)
public interface Sample1Feign {

    @GetMapping(value = "/hello/{name}")
    String hello(@PathVariable("name") String name);

}
