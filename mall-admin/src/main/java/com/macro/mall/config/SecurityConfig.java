package com.macro.mall.config;

import com.macro.mall.component.JwtAuthenTokenFilter;
import com.macro.mall.component.RestAccessDeniedHandler;
import com.macro.mall.component.RestAuthenticationEntryPoint;
import com.macro.mall.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 对SpringSecurity的配置的扩展，支持自定义白名单资源路径和查询用户逻辑
 *
 */
@Configuration
@EnableWebSecurity//启用Security
@EnableGlobalMethodSecurity(prePostEnabled = true)//启用controller方法验证权限
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UmsAdminService umsAdminService;
    //不需要jwt拦截的路径
    @Value("${secure.ignored.urls}")
    private String urls;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
                .authorizeRequests();
        /*设置可以公开访问的地址*/
        for (String url: urls.split("- ")) {
            registry.antMatchers(url.trim()).permitAll();
        }
        /*允许OPTIONS跨域请求*/
        registry.antMatchers(HttpMethod.OPTIONS).permitAll();

        //设置任何请求需要身份认证
        registry.and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()

                //关闭HttpSession
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                //添加未登录和没有权限的结果返回
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())//添加自定义没有权限的结果处理类
                .authenticationEntryPoint(authenticationEntryPoint())//添加自定义未登录和登录超时的结果处理类

                .and()//添加过滤器
                .addFilterBefore(jwtAuthenTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(encoder());
    }


    @Bean//返回UserDetailsService实现类
    public UserDetailsService userDetailsService(){
        return  username ->  umsAdminService.loadUserByUsername(username);
    }

    @Bean//登录和注册使用的加密和解密
    public BCryptPasswordEncoder encoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean//返回权限不足的结果处理类
    public AccessDeniedHandler accessDeniedHandler(){
        return new RestAccessDeniedHandler();
    }
    @Bean//返回未登录和超时的结果处理类
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return  new RestAuthenticationEntryPoint();
    }
    @Bean
    public JwtAuthenTokenFilter jwtAuthenTokenFilter(){
        return  new JwtAuthenTokenFilter();
    }
}
