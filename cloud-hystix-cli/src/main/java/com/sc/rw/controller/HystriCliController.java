package com.sc.rw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sc.rw.feign.HystrixSrvFeign;


@RestController
public class HystriCliController {

	
	@Autowired
	private HystrixSrvFeign feign;
	
	@PostMapping("/hello2/{name}")
	public String hello(@PathVariable("name") String name) {
		
		return feign.hello(name);
		
	}
	
	@GetMapping("/back/{name}")
	public String back(@PathVariable("name") String name) {
		return "hello  " +name +", I am hystrix 熔断的  cli";
	}
}
