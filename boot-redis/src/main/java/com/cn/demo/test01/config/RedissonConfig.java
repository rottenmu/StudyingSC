package com.cn.demo.test01.config;

import java.io.IOException;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

@Primary
@Configuration
public class RedissonConfig {

	
	@Bean(destroyMethod="shutdown")
	public RedissonClient redisson() {
		Config config = new Config();
    	try {
			config = Config.fromYAML(new ClassPathResource("redisson.yml").getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RedissonClient redissonClient = Redisson.create(config); 
		return  redissonClient;
	}
	
	
	
}
