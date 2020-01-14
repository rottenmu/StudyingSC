package com.example.cloud.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CloudGatewaySampleApp {

    public static void main(String[] args) {
        SpringApplication.run(CloudGatewaySampleApp.class, args);
    }




}
