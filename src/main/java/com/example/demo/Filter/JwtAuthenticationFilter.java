package com.example.demo.Filter;


import com.example.demo.pojo.Entity.MyUser;
import com.example.demo.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;



//过滤所有请求
@Component
//@WebFilter(urlPatterns = "/api/*")
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        logger.info("JwtAuthenticationFilter doFilterInternal");

        //如果是登录或注册请求，则直接放行
        if(request.getRequestURI().contains("/login") || request.getRequestURI().contains("/register")){
            doFilter(request, response, filterChain);
        }else {

            //请求资源时，验证jwtToken
            try {
                //提取jwtToken
                String Authorization = request.getHeader("Authorization");
                if (Authorization == null || !Authorization.startsWith("Bearer ")) {
                    throw new RuntimeException("无效的jwt令牌");
                }

                // 提取JWT主体部分并解析
                String jwtBody = Authorization.substring("Bearer ".length());
                Map<String, Object> objectMap = JwtUtils.parseJwt(jwtBody);

                logger.info("jwtToken解析结果："+objectMap);

                // 从redis中获取 用户信息
                MyUser myUser =( MyUser ) redisTemplate.opsForValue().get("user-" + objectMap.get("userId"));

                logger.info("从redis中获取的用户信息："+myUser);

                //创建Authentication对象,并设置GrantedAuthority（设置后自动super.setAuthenticated(true)）
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        myUser,                     // principal:主体， 封装了用户信息
                        null,                       // credentials：凭证，用于认证principal为真的信息（密码之类的）
                        myUser.getAuthorities()     // authorities: 权限， 封装了用户权限信息
                );


                //放入SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(authentication);

                //放行
                doFilter(request, response, filterChain);
            }catch (Exception e){
                logger.error("验证jwtToken失败"+e.getMessage());

                //重定向！
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write("{\"error\": \"无效的jwt令牌\", \"redirect\": \"/login\"}");
            }
        }
    }


//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        logger.info("JwtAuthenticationFilter doFilterInternal");
//
//        //如果是登录或注册请求，则直接放行
//        if(request.getRequestURI().contains("/login") || request.getRequestURI().contains("/register")){
//            doFilter(request, response, filterChain);
//        }else {
//
//            //请求资源时，验证jwtToken
//            try {
//                //提取jwtToken
//                String Authorization = request.getHeader("Authorization");
//                if (Authorization == null || !Authorization.startsWith("Bearer ")) {
//                    throw new RuntimeException("无效的jwt令牌");
//                }
//
//                // 提取JWT主体部分并解析
//                String jwtBody = Authorization.substring("Bearer ".length());
//                Map<String, Object> objectMap = JwtUtils.parseJwt(jwtBody);
//
//
//                //? 有趣的是objectMap.get("authorities")是不是 String，而是一个 ArrayList<String>，
//                List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList((Collection<String>)objectMap.get("authorities"));
//
//
//                // 从redis中获取 用户信息
////                RedisTemplate< String, Object> redisTemplate =new  RedisTemplate<>();
////                MyUser myUser =( MyUser ) redisTemplate.opsForValue().get("user-" + objectMap.get("userId"));
//
//                //TODO:这里的allowedQuestionIds
//                //? 我也不知道为什么，必须将objectMap.get("userId")先用Integer借助，再用long转换，否则会ClassCastException
//                MyUser myUser = new MyUser((long) (Integer)objectMap.get("userId"),null ,authorities);//这是一个实现了UserDetails接口的类，用于封装用户信息
//
//                //创建Authentication对象,并设置GrantedAuthority（设置后自动super.setAuthenticated(true)）
//                Authentication authentication = new UsernamePasswordAuthenticationToken(
//                        myUser,                     // principal:主体， 封装了用户信息
//                        null,                       // credentials：凭证，用于认证principal为真的信息（密码之类的）
//                        authorities                 // authorities: 权限， 封装了用户权限信息
//                );
//
//                //? 我不知道为什么，如果这行代码注释的话，会被认为没有认证成功，导致后续的请求被拦截
//                //? authentication.setAuthenticated(true);
//
//                //放入SecurityContextHolder
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//
//                //放行
//                doFilter(request, response, filterChain);
//
//            }catch (Exception e){
//                logger.error("验证jwtToken失败"+e.getMessage());
//
//                //返回错误信息
//                response.sendError(401, "无效的jwt令牌");
//            }
//        }
//    }
}
