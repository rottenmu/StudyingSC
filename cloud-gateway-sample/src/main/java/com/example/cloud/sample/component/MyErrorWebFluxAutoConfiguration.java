package com.example.cloud.sample.component;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class MyErrorWebFluxAutoConfiguration implements ErrorWebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        return Mono.fromRunnable(() ->{
             exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
        });
    }
}
