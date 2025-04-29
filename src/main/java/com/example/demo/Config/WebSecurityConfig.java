package com.example.demo.Config;

import com.example.demo.Config.Security.*;
import com.example.demo.Filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

//    配置SecurityFilterChain，作用是设置安全策略
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 禁用默认的/logout端点
        http.csrf(AbstractHttpConfigurer::disable); // 禁用CSRF

        // 配置认证
        http.authorizeHttpRequests(auth -> auth
                    .requestMatchers("/login/**").permitAll() // 允许登录接口公开访问
                    .requestMatchers("/register/**").hasAuthority("ROLE_ADMIN") // 允许注册接口公开访问
                        .requestMatchers("/api/club/**").hasRole("ADMIN")
                    .anyRequest().authenticated() // 其他请求需要认证
                );

        // 配置登录
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
        http.exceptionHandling(exceptionHandling ->
                exceptionHandling.authenticationEntryPoint(new MyAuthenticationEntryPoint())
        );

        // 配置session管理策略
        http.sessionManagement(httpSecuritySessionManagementConfigurer -> {
            httpSecuritySessionManagementConfigurer.maximumSessions(1).expiredSessionStrategy(new MySessionInformationExpiredStrategy());
        });


        // 添加以下配置，禁用默认的登录页面和重定向
        http.httpBasic(AbstractHttpConfigurer::disable) // 禁用HTTP Basic认证
                // 添加JWT过滤器到UsernamePasswordAuthenticationFilter之前
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        ;

        // 允许跨域请求
        http.cors(withDefaults());


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