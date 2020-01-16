package com.example.cloud.sample.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

public interface ScaFeign {

    @GetMapping("/hello/{name}")
     Mono<String> hello(@PathVariable("name") String name);

    @GetMapping("/readConfig")
    String readConfig();
}
