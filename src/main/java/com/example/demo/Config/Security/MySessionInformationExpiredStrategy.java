package com.example.demo.Config.Security;

import jakarta.servlet.ServletException;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import java.io.IOException;

//自定义session过期策略
public class MySessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
//        返回一个错误的json信息
        event.getResponse().getWriter().write("{\"code\":401,\"message\":\"Session expired, please login again\"}");
    }
}
