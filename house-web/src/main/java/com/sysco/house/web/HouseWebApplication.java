package com.sysco.house.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.StringUtils;

@SpringBootApplication
@MapperScan("com.sysco.house.biz.mapper")
@ComponentScan(basePackages={"com.sysco.house.biz","com.sysco.house.web","com.sysco.house.common"})
public class HouseWebApplication {

    public static void main(String[] args) {
        String env = "dev";
        // read from the environment variables passed from GO CD
        if (!StringUtils.isEmpty(System.getenv("APPLICATION_ENV"))) {
            env = System.getenv("APPLICATION_ENV");
        }
        new SpringApplicationBuilder(HouseWebApplication.class).profiles(env).run(args);
    }

}

