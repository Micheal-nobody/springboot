package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration //配置类
//@EnableWebSecurity //开启SpringSecurity的自定义配置（SpringBoot自动添加）
public class WebSecurityConfig {

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        //创建基于内存的用户信息管理器
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//
//        //使用manager管理UserDetails对象
//        manager.createUser(
//                //创建UserDetails对象，用于管理用户名、用户密码、用户角色、用户权限等内容
//                User.withDefaultPasswordEncoder()
//                        .username("huan").password("password").roles("USER")
//                        .build() );
//
//        return manager;
//
//    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        //创建基于数据库的用户信息管理器
//        DBUserDetailsManager manager = new DBUserDetailsManager();
//
//        return manager;
//
//    }

}