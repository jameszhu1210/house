package com.sysco.house.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.sysco.house.biz.mapper")
@ComponentScan(basePackages={"com.sysco.house.biz","com.sysco.house.web","com.sysco.house.common"})
public class HouseWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(HouseWebApplication.class, args);
    }

}

