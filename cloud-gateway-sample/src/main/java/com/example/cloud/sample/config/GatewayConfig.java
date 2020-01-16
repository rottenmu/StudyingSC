package com.example.cloud.sample.config;


import com.example.cloud.sample.component.filter.MyGatewayFilter;
import com.example.cloud.sample.controller.FallBackContronller;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    /**
     * 根据远程服务访问路径断言
     * @param builder
     * @return
     */
//    @Bean
//    public RouteLocator  routeLocator(RouteLocatorBuilder builder){
//
//        return  builder.routes()
//                .route("sample-sca", r ->r.path("/sca/**")
//                       // .filters(f ->f.filter(new MyGlobalFilter()))
//                        .uri("lb://sample-sca")).build();
//    }

    /**
     *  通过网关host进行断言
     * @param builder
     * @return
     */
//    @Bean
//    public RouteLocator  hostRouteLocator(RouteLocatorBuilder builder){
//        return  builder.routes()
//                .route("sample-sca", r ->r.host("127.0.0.1:8088")
//                        // .filters(f ->f.filter(new MyGlobalFilter()))
//                        .uri("lb://sample-sca")).build();
//    }

    /**
     *  根据请求方法断言
     * @param builder
     * @return
     */
//    @Bean
//    public  RouteLocator requestMethodRouteLocator(RouteLocatorBuilder builder){
//        return  builder.routes()
//                .route("method_route", r ->r.method("GET").uri("lb://sample-sca"))
//                .build();
//    }


    /**
     *  断言拼装【通过 or 或者 and】
     * @param builder
     * @return
     */
//    @Bean
//    public  RouteLocator querRouteLocator(RouteLocatorBuilder builder){
//        return builder.routes()
//                .route("queyr_route", r ->r.query("name","jack").or().method("GET").uri("lb://sample-sca"))
//                .build();
//    }

//    @Bean
//    public  RouteLocator reponseHeaderRouteLocator(RouteLocatorBuilder builder){
//
//        return  builder.routes()
//                .route("add_response_header",r ->r.path("/sca/hello/{name}")
//                        .filters(f -> f.addRequestHeader("X-Response-Foo","Bar")).uri("lb://sample-sca"))
//                .build();
//    }

    /**
     *  hystrix 网关熔断
     * @param builder
     * @return
     */
    @Bean
    public  RouteLocator hystrixRouteLocator(RouteLocatorBuilder builder ){
        return builder.routes()
                .route("hystrix_route",r ->r.path("/sca/**")
                      .filters(f -> f.filter(new MyGatewayFilter()))
                      //  .filters(f -> f.hystrix(config -> config.setFallbackUri("forward:/fallback")))
                        .uri("lb://sample-sca"))
                .route("sca2-server",r -> r.host("127.0.0.1:8088").filters(f ->f.filter(new MyGatewayFilter()))
                        .uri("lb://sample-sca2"))
                        .build();
    }
}
