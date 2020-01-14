package com.example.samplescaservice2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class SampleScaService2Application {

    public static void main(String[] args) {
        SpringApplication.run(SampleScaService2Application.class, args);
    }

}
