package com.example.cloud.sample;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.factory.HystrixGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.DispatcherHandler;

@EnableDiscoveryClient
@SpringBootApplication
public class CloudGatewaySampleApp {

    public static void main(String[] args) {
        SpringApplication.run(CloudGatewaySampleApp.class, args);
    }




}
