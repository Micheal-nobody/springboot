package com.example.demo.Filter;


import com.example.demo.pojo.MyUser;
import com.example.demo.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;



//过滤所有请求
@Component
@WebFilter(urlPatterns = "/api/*")
public class JwtAuthenticationFilter extends OncePerRequestFilter {

//        //TODO:生产环境下，请注释掉
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
////        打印请求参数
//        System.out.println("----------------------------------------------");
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        if ("/login".equals(req.getRequestURI())) {
//            System.out.println("Login Request Parameters:");
//            System.out.println("Username: " + req.getParameter("username"));
//            System.out.println("Password: " + req.getParameter("password"));
//        }
//        System.out.println("----------------------------------------------");
//
//        filterChain.doFilter(servletRequest, servletResponse);
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        logger.info("JwtAuthenticationFilter doFilterInternal");

        //如果是登录请求，则直接放行
        if(request.getRequestURI().contains("/login") || request.getRequestURI().contains("/register")){
            doFilter(request, response, filterChain);
        }else {
            //请求资源时，验证jwtToken
            try {
                //提取jwtToken
                String jwtToken = request.getHeader("token");
                if(jwtToken == null) {
                    throw new RuntimeException("无效的jwt令牌");
                }

                //解析jwtToken
                Map<String, Object> objectMap = JwtUtils.parseJwt(jwtToken);

                //? 有趣的是objectMap.get("authorities")是不是 String，而是一个 ArrayList<String>，
                List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList((Collection<String>) objectMap.get("authorities"));


                //? 我也不知道为什么，必须将objectMap.get("userId")先用Integer借助，再用long转换，否则会ClassCastException
                //? MyUser myUser = new MyUser((long) objectMap.get("userId"), authorities);
                MyUser myUser = new MyUser((long) (Integer)objectMap.get("userId"), authorities);//这是一个实现了UserDetails接口的类，用于封装用户信息


                //创建Authentication对象,并设置GrantedAuthority（设置后自动super.setAuthenticated(true)）
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        myUser,
                        null,
                        authorities
                );

                //? 我不知道为什么，如果这行代码注释的话，会被认为没有认证成功，导致后续的请求被拦截
                //? authentication.setAuthenticated(true);

                //放入SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(authentication);

                //放行
                doFilter(request, response, filterChain);
            }catch (Exception e){
                logger.error("验证jwtToken失败"+e.getMessage());

                //返回错误信息
                response.sendError(401, "无效的jwt令牌");
            }
        }
    }
}
