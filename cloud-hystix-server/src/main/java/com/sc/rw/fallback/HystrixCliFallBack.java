package com.sc.rw.fallback;

import org.springframework.stereotype.Component;

import com.sc.rw.feign.HystriCliFeign;

@Component
public class HystrixCliFallBack  implements HystriCliFeign{

	@Override
	public String back(String name) {
		return  name +",Sorry I dont't know who are you!";
	}

}
