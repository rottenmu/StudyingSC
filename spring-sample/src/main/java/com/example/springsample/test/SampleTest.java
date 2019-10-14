package com.example.springsample.test;

import com.alibaba.fastjson.JSONObject;
import com.example.springsample.conf.AppConf;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.ext.bridge.BridgeOptions;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.eventbus.bridge.tcp.TcpEventBusBridge;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.Date;

public class SampleTest {
    public static void main(String[] args) throws InterruptedException {

        RestTemplate template = new RestTemplate();
        JSONObject forObject = template.getForObject("http://106.12.215.254:8848/nacos/v1/ns/instance/list?serviceName=procuder-sample&healthyOnly=true", JSONObject.class);
        System.out.println("obj - >" +forObject.toJSONString());
        Vertx vertx = Vertx.vertx();
        EventBus eb = vertx.eventBus();

        MessageConsumer<String> consumer = eb.consumer("eventConsumer");
        MessageConsumer<String> handler = consumer.handler(message -> {
            System.out.println("I have received a message: " + message.body());
        });
        System.out.println(consumer.isRegistered());
        Thread.sleep(3000000L);

       // eventBus.publisher()
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConf.class);
//
//        applicationContext.start();
    }
}
