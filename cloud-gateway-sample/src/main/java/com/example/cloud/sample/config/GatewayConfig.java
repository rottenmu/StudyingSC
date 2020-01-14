package com.example.cloud.sample.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator  routeLocator(RouteLocatorBuilder builder){

        return  builder.routes()
                .route("sample-sca", r ->r.path("/sca/**")
                       // .filters(f ->f.filter(new MyGlobalFilter()))
                        .uri("lb://sample-sca")).build();
    }
}
