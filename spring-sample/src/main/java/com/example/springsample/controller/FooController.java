package com.example.springsample.controller;

import com.example.springsample.annotation.SamplRequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FooController {


    @SamplRequestMapping( value = "/hello",method = RequestMethod.GET)
    public  String hello(){

        return "hello";
    }
}
