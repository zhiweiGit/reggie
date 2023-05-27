package com.reggie.config;

import com.reggie.controller.lnterceptor.LoginRequiredInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginRequiredInterceptor loginRequiredInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginRequiredInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/backend/page/login/login.html","/employee/logout",
                        "/employee/login","/front/index.html","/front/page/login.html",
                        "/user/sendMsg","/user/login",
                        "/error",
                        "/**/*.css", "/**/*.js", "/**/*.png",
                        "/**/*.jpg", "/**/*.jpeg","/**/*.ttf","/**/*.woff*","/**/*.ico",
                        "/**/*.otf","/**/*.map");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/backend/**")
                .addResourceLocations("classpath:backend/");
        registry.addResourceHandler("/front/**")
                .addResourceLocations("classpath:front/");
    }


}
