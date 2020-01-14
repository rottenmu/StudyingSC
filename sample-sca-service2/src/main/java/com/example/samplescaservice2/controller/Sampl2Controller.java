package com.example.samplescaservice2.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.samplescaservice2.feign.Sample1Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class Sampl2Controller {

    @Autowired
    private Sample1Feign feign;


    @GetMapping("/hello/{name}")
    @SentinelResource("hello")
    public String hello(@PathVariable("name") String name){

        return  feign.hello(name);
    }
}
