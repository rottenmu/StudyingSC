package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.componet.RedisComponet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasicRedisApplicationTests {

	@Autowired
	private RedisComponet compent;
	@Test
	public void contextLoads() {
		
		compent.publish("msg", "hello redis quence");
	}

}

