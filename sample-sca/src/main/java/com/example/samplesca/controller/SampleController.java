package com.example.samplesca.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;

import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping(value = "/sca")
public class SampleController {


    @Value("${spring.application.name}")
     private   String name;


    @GetMapping("/hello/{name}")
    @SentinelResource("hello")
    public Mono<String> hello(@PathVariable("name") String name){

        return  Mono.just("hello ".concat(name));
    }

    @GetMapping("/readConfig")
    public String readConfig(){

        return  name;
    }
}
