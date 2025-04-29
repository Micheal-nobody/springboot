package com.example.demo.Config.Security;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.util.HashMap;

//自定义认证失败处理器
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {

        String localizedMessage = exception.getLocalizedMessage();
        System.out.println(localizedMessage);


        HashMap<String, Object> result = new HashMap<>();
        result.put("status", "failed");
        result.put("message", localizedMessage);

        // 将结果转为json字符串
        String json = JSON.toJSONString(result);

        //返回json数据给前端
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.setStatus(200);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(json);
    }
}
