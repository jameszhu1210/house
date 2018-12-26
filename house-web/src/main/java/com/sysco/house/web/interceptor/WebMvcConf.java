package com.sysco.house.web.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConf extends WebMvcConfigurerAdapter {
    @Autowired
    private AutoInterceptor autoInterceptor;

    @Autowired
    private AutoActionInterceptor autoActionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //不需要拦截static
        registry.addInterceptor(autoInterceptor).excludePathPatterns("/static").addPathPatterns("/**");
        //只拦截需要登陆才能访问接口
        registry.addInterceptor(autoActionInterceptor).addPathPatterns("/accounts/profile");
        super.addInterceptors(registry);
    }
}
