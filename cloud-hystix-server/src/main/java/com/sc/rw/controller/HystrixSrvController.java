package com.sc.rw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sc.rw.feign.HystriCliFeign;

@RestController
public class HystrixSrvController {

	
	@Autowired
	private HystriCliFeign feign;
	
	@PostMapping("/hello/{name}")
	public String hello(@PathVariable("name") String name) {
		
		
		return "hello" + name +",I am hystrix 熔断的server";
	}
	
	
	@GetMapping("/back2/{name}")
	public String back2(@PathVariable("name") String name) {
		
		return feign.back(name);
	}
}
