//package com.example.cloud.sample.config;
//
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.cloud.gateway.support.NotFoundException;
//import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//
////@Component
///**
// * {"timestamp":"2020-01-13T07:11:58.760+0000","path":"/hello/jack","status":500,
// * "error":"Internal Server Error","message":"The Mono returned by the supplier is null"}
// */
//public class MyGlobalFilter implements GlobalFilter, GatewayFilter {
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//
//
//      return  null;
//    }
//
//    @Override
//    public ShortcutType shortcutType() {
//        return null;
//    }
//}
