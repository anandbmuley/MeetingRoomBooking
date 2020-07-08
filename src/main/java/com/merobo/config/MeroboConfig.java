package com.merobo.config;

import com.merobo.resources.interceptors.AdminAccessInterceptor;
import com.merobo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MeroboConfig implements WebMvcConfigurer {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminAccessInterceptor adminAccessInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(adminAccessInterceptor).addPathPatterns("/admin/rooms");
    }
}
