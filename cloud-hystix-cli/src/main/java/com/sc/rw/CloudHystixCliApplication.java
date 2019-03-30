package com.sc.rw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableHystrix
@EnableFeignClients
@SpringCloudApplication
public class CloudHystixCliApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudHystixCliApplication.class, args);
	}

}
