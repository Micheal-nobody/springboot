package com.example.demo.Interceptor;


import com.example.demo.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        String token = request.getHeader("token");
//        if (token == null) {
//            response.sendRedirect("/login/admin");
//        }
//
////        解析token
//        try{
//            //TODO: 这里应该使用UserLoginPrimission类封装权限验证
//            JwtUtils.parseJwt(token, "admin");
//        }catch (Exception e){
//            log.error(e.getMessage());
//            return false;
//        }
//
//        return true;
//    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("Request Parameters:");
        request.getParameterMap().forEach((key, values) -> {
            System.out.println(key + ": " + String.join(", ", values));
        });
        return true;
    }

}
