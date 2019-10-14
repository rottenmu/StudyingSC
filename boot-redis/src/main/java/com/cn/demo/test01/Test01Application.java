package com.cn.demo.test01;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.alibaba.druid.pool.DruidDataSource;

@SpringBootApplication
@MapperScan("com.cn.demo.test01.dao")
@ComponentScan(basePackages = {"com.cn.demo.test01.*"})
@ServletComponentScan
public class Test01Application {

    public static void main(String[] args) {
        SpringApplication.run(Test01Application.class, args);
    }

    @Bean("druidDataSource")
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource druidDataSource() { return new DruidDataSource(); }
}

