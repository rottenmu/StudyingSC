package com.example.springsample.conf;

import com.example.springsample.annotation.EnableSample;
import com.example.springsample.mapper.FooMapper;
import com.example.springsample.mapper.SampleMapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@Configuration
//@MapperScan("com.example.springsample.mapper")
@EnableSample(basepackage = "com.example.springsample.mapper")
public class AppConf {

    @Bean
    public DataSource dataSource(){

        DriverManagerDataSource managerDataSource = new DriverManagerDataSource();

        managerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        managerDataSource.setUsername("root");
        managerDataSource.setPassword("123456");
        managerDataSource.setUrl("jdbc:mysql://106.12.215.254:3306/test?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8");
        return  managerDataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return  sqlSessionFactoryBean;
    }

}
