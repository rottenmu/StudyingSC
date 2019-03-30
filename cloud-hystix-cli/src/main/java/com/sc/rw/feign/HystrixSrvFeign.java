package com.sc.rw.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sc.rw.hystrix.HystrixSrvFallback;

@FeignClient(name="hystrix-srv",fallback = HystrixSrvFallback.class)
public interface HystrixSrvFeign {

	
	@RequestMapping(value="/hello/{name}",method = RequestMethod.POST)
	public String  hello(@PathVariable String name);
}
