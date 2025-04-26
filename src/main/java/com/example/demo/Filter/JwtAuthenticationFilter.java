package com.example.demo.Filter;


import com.example.demo.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import java.io.IOException;
import java.util.Map;

//过滤所有请求
@WebFilter(urlPatterns = "/*")
public class JwtAuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("JwtAuthenticationFilter is executed");
        filterChain.doFilter(servletRequest, servletResponse);
    }


//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("DemoFilter is executed");
//
////        验证jwt令牌
//        try{
//            String jwtToken = (String) servletRequest.getAttribute("jwtToken");
//            String permission = (String) servletRequest.getAttribute("permission");
//            Map<String, Object> results = JwtUtils.parseJwt(jwtToken, permission);
//            if(results.isEmpty()){
//                throw new RuntimeException("无效的jwt令牌");
//
//            }
//
////            注册Authentication
//            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(results.get("username"), results.get("password"));
//            Authentication authentication = authenticationManager.authenticate(authenticationToken);
//
////            设置authentication的details
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            System.out.println("jwt令牌验证成功");
////            放行
//            filterChain.doFilter(servletRequest, servletResponse);
//
//        } catch (Exception e) {
////            返回错误信息
//            System.out.println("jwt令牌验证失败");
//
////            401 未授权
//            ((HttpServletResponse) servletResponse).sendError(401, "无效的jwt令牌");
//        }
//    }
}
