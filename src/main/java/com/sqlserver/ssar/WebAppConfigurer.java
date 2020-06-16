package com.sqlserver.ssar;

import com.sqlserver.ssar.interceptor.LoginInterceptor;
import com.sqlserver.ssar.interceptor.RightsInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    @Autowired
    RightsInterceptor rightsInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
        // 此处表示需要拦截程序的所有请求
        registration.addPathPatterns("/**")
                // 指定请求不拦截
                .excludePathPatterns("/",
                        "/login",
                        "/error",
                        "/css/**",
                        "/easyui/**",
                        "/font-awesome/**",
                        "/js/**",
                        "/lib/**");

        InterceptorRegistration rightsRegistration = registry.addInterceptor(rightsInterceptor);

        rightsRegistration.addPathPatterns("/**")
                // 指定请求不拦截
                .excludePathPatterns("/",
                        "/login",
                        "/logout",
                        "/error",
                        "/reject",
                        "/menus",
                        "/css/**",
                        "/easyui/**",
                        "/font-awesome/**",
                        "/js/**",
                        "/lib/**");
    }
}
