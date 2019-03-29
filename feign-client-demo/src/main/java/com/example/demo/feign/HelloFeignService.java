package com.example.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "feign-server")
public interface HelloFeignService {

	
	@RequestMapping(value = "/helloClient",method = RequestMethod.GET )
	String helloClient();
}
