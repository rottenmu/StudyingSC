package com.example.cloud.sample.component;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  过滤器定义模型
 */
@Data
public class GatewayFilterDefinition {


    private  String name;

    /**
     * 对应路由规则
     */
    private Map<String,String> args = new LinkedHashMap<>();

}
