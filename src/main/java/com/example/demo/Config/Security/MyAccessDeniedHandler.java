package com.example.demo.Config.Security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

//用户已认证，但没有足够权限访问某个资源。通常是 403（禁止访问）。
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        System.out.println("MyAccessDeniedHandler is called 这代表用户没有权限访问该资源");
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
    }
}
