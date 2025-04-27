package com.example.demo.Config.Security;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.HashMap;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("Authentication failed");

        String principal = authentication.getPrincipal().toString();


        HashMap result = new HashMap();
        result.put("status", "failed");
        result.put("message", principal);

        // 将结果转为json字符串
        String json = JSON.toJSONString(result);

        //返回json数据给前端
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setStatus(200);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(json);
    }
}
