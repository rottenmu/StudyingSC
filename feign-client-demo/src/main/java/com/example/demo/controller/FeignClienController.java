package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.feign.HelloFeignService;

@RestController
public class FeignClienController {

	
	@Autowired
	private HelloFeignService feignService;
	
	@GetMapping("/hello")
	public String hello() {
		
		return feignService.helloClient();
	}
}
