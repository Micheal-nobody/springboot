package com.example.demo.Config.Security;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.HashMap;

//访问一个需要认证之后才能访问的接口的时，如果没有认证，则会调用此类中的commence方法，返回json数据给前端
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint
{
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        System.out.println("MyAuthenticationEntryPoint commence");

        String localizedMessage = authException.getLocalizedMessage();
        System.out.println(localizedMessage);


        HashMap<String, Object> result = new HashMap<>();
        result.put("status", "failed");
        result.put("message", localizedMessage);

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
