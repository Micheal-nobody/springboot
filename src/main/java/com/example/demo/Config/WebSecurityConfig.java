package com.example.demo.Config;

import com.example.demo.Config.Security.*;
import com.example.demo.Filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig{


//   实际上是在配置FilterChainProxy中的 securityFilterChain bean
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 禁用默认的/logout端点
        http.csrf(AbstractHttpConfigurer::disable); // 禁用CSRF
        http.cors(withDefaults());                  // 允许跨域请求

        // 配置session管理策略，禁用session，因为要使用JWT
        http.sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 配置认证
        http.authorizeHttpRequests(auth -> auth
                    .requestMatchers("/login/**").permitAll() // 允许登录接口公开访问
                    .anyRequest().authenticated() // 其他请求需要认证
                );


        // 配置登录，默认使用UsernamePasswordAuthenticationFilter
        http.formLogin(form -> {
                    form.loginProcessingUrl("/login") // 登录接口地址
                            .successHandler(new MyAuthenticationSuccessHandler())//自定义登录成功处理器
                            .failureHandler(new MyAuthenticationFailureHandler())//自定义登录失败处理器
                    ;
                });


        // 配置退出登录
        http.logout(
            AbstractHttpConfigurer::disable // 禁用默认的/logout端点
        );

        //使用lambda表达式配置异常处理器
        // 没有认证下请求需要认证的网址时，自动跳转到这里配置的异常处理器)
        http.exceptionHandling(exceptionHandling ->{
            exceptionHandling.authenticationEntryPoint(new MyAuthenticationEntryPoint());
            exceptionHandling.accessDeniedHandler(new MyAccessDeniedHandler());
            }
        );


        // 添加以下配置，禁用默认的登录页面和重定向
        http.httpBasic(AbstractHttpConfigurer::disable); // 禁用HTTP Basic认证

        // 添加JWT过滤器到UsernamePasswordAuthenticationFilter之前
        http.addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

//    自动调用DBUserDetailsManager的loadUserByUsername方法，从数据库中获取用户信息
//    本质是配置了DaoAuthenticationProvider的bean，相当于：new DaoAuthenticationProvider().setUserDetailsService(userDetailsService());
    public UserDetailsService userDetailsService() {
        return new DbUserDetailsService(); // 使用数据库用户详情服务
    }

//    调用AuthenticationManager的authenticate方法，进行登录验证
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}