package com.example.demo.controller;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cache")
public class CacheController {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	
	@GetMapping("/hello/{name}")
	public String hello(@PathVariable("name") String name) {
		String key = MD5Encoder.encode(name.getBytes());
		redisTemplate.opsForValue().set("name", name);
		
		return "hello "+ redisTemplate.opsForValue().get("name");
		
	}
}
