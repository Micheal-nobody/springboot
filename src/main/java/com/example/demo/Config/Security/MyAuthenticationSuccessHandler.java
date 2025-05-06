package com.example.demo.Config.Security;

import com.example.demo.pojo.MyUser;
import com.example.demo.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

//自定义认证成功处理器
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {


//        返回一个JWT token给前端
        String generatedJwt = JwtUtils.generateJwt((MyUser) authentication.getPrincipal(), authentication.getAuthorities());
        System.out.println(generatedJwt);


        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(generatedJwt);
    }
}
