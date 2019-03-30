package com.sc.rw;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
//该注解为复合注解，里面包含了熔断器注解，注册中心客户端注解
@EnableFeignClients
@EnableHystrix
@SpringCloudApplication
public class CloudHystixServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudHystixServerApplication.class, args);
	}

}
