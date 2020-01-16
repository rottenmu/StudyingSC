package com.example.cloud.sample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackContronller {

    @GetMapping(value = "/fallback")
    public String fallback(){

        return "该 uri不存在";
    }
}
