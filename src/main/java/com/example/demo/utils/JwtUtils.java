package com.example.demo.utils;


import com.example.demo.pojo.MyUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
// JwtUtils.java (修正后)
public class JwtUtils {
    private static final String FIXED_SECRET = "这是一个很长很长的密钥，它需要满足长度：64，并且必须是Base64编码的，就像这样"; // 从配置读取
    private static final SecretKey secretKey =Keys.hmacShaKeyFor(FIXED_SECRET.getBytes());


    public static String generateJwt(MyUser principal, Collection<? extends GrantedAuthority> authorities) {

        //设置载荷
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", principal.getId());

        //设置权限，使用GrantedAuthority的getAuthority()方法获取权限字符串
        claims.put("authorities", authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        return Jwts.builder()
                .claims(claims)
                .signWith(secretKey)
                .compact();
    }

    public static Map<String, Object> parseJwt(String jwtToken) throws JwtException,IllegalArgumentException {
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(jwtToken)
                    .getPayload();
    }
}
