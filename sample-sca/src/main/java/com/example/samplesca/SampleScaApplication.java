package com.example.samplesca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class SampleScaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleScaApplication.class, args);
    }

}
