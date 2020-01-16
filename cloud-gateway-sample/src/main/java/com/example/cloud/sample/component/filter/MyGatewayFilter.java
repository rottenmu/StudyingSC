package com.example.cloud.sample.component.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class MyGatewayFilter implements GatewayFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        return chain.filter(exchange).then(
                Mono.fromRunnable(() ->{
                    String token = exchange.getRequest().getQueryParams().getFirst("token");
                    if(StringUtils.isEmpty(token)){
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    }

                    System.out.println(exchange.getRequest().getPath());
                    System.out.println(exchange.getRequest().getURI().toString());
                })
        );
    }

    @Override
    public int getOrder() {
        return -2;
    }
}
