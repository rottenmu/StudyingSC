package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignServerController {

	
	
	
	@GetMapping("/helloClient")
	public String helloClient() {
		
		return "hello client ,I am feign server";
	}
}
