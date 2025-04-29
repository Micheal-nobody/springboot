package com.example.demo.Filter;


import com.example.demo.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.springframework.stereotype.Component;


import java.io.IOException;


//过滤所有请求
@Component
@WebFilter(urlPatterns = "/api/*")
public class JwtAuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
////        打印请求参数
//        System.out.println("----------------------------------------------");
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        if ("/login".equals(req.getRequestURI())) {
//            System.out.println("Login Request Parameters:");
//            System.out.println("Username: " + req.getParameter("username"));
//            System.out.println("Password: " + req.getParameter("password")); // 注意：生产环境不要打印密码！
//        }
//        System.out.println("----------------------------------------------");

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
