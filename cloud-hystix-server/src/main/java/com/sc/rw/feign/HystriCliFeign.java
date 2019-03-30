package com.sc.rw.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sc.rw.fallback.HystrixCliFallBack;

@FeignClient(name="hystrix-cli",fallback = HystrixCliFallBack.class)
public interface HystriCliFeign {

	
	@RequestMapping(value = "/back/{name}",method = RequestMethod.GET)
	public String back(@PathVariable("name") String name);
}
