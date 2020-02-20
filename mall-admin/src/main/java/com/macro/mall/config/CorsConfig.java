package com.macro.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 设置允许跨域访问
 */
@Configuration
public class CorsConfig{

    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration cors=new CorsConfiguration();
        cors.addAllowedOrigin("*");//设置所有请求允许跨域请求
        cors.setAllowCredentials(true);//设置cookie可以跨域发送
        cors.addAllowedHeader("*");//放行全部头信息
        cors.addAllowedMethod("*");//允许所有请求可以跨域调用
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",cors);
        return new CorsFilter(source);
    }
}
