package com.example.cloud.sample.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


//
/**
 * {"timestamp":"2020-01-13T07:11:58.760+0000","path":"/hello/jack","status":500,
 * "error":"Internal Server Error","message":"The Mono returned by the supplier is null"}
 */
@Component
public class MyGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

      return  chain.filter(exchange).then(Mono.fromRunnable(() ->{
          String token = exchange.getRequest().getQueryParams().getFirst("token");
          if(StringUtils.isEmpty(token)){
              exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
          }
      }));
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
