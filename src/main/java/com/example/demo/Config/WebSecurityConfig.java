package com.example.demo.Config;

import com.example.demo.Config.Security.MyAuthenticationFailureHandler;
import com.example.demo.Config.Security.MyAuthenticationSuccessHandler;
import com.example.demo.Config.Security.DbUserDetailsService;
import com.example.demo.Filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

//    配置SecurityFilterChain，作用是设置安全策略，并添加JWT过滤器到UsernamePasswordAuthenticationFilter之前
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 禁用默认的/logout端点
        http
                .csrf(AbstractHttpConfigurer::disable) // 禁用CSRF
                    .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login/**").permitAll() // 允许登录接口公开访问
                        .anyRequest().authenticated() // 其他请求需要认证
                )
                .formLogin(form -> {
                    form.loginProcessingUrl("/login") // 登录接口地址
                            .usernameParameter("username")
                            .passwordParameter("password")
                            .successHandler(new MyAuthenticationSuccessHandler())//自定义登录成功处理器
                            .failureHandler(new MyAuthenticationFailureHandler())//自定义登录失败处理器
                    ;

                })


                // 添加以下配置，禁用默认的登录页面和重定向
                .httpBasic(AbstractHttpConfigurer::disable) // 禁用HTTP Basic认证
                .logout(AbstractHttpConfigurer::disable)
                // 添加JWT过滤器到UsernamePasswordAuthenticationFilter之前
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        ;

        return http.build();
    }

//    自动调用DBUserDetailsManager的loadUserByUsername方法，从数据库中获取用户信息
    public UserDetailsService userDetailsService() {
        return new DbUserDetailsService(); // 使用数据库用户详情服务
    }

//    调用AuthenticationManager的authenticate方法，进行登录验证
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}