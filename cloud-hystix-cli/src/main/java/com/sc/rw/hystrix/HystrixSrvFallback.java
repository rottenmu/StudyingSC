package com.sc.rw.hystrix;

import org.springframework.stereotype.Component;

import com.sc.rw.feign.HystrixSrvFeign;

import feign.hystrix.FallbackFactory;

@Component
public class HystrixSrvFallback implements HystrixSrvFeign{

	@Override
	public String hello(String name) {
		return name + " is unknow";
	}


}
