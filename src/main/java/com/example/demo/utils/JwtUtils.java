package com.example.demo.utils;


import com.example.demo.pojo.ENUM.UserLoginPermission;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Map;


@Slf4j
public class JwtUtils {

    public static String generateJwt(Map<String,Object> map,String key) {
        SecretKey secretKey=Keys.hmacShaKeyFor(key.getBytes());

        return Jwts.builder()
                .claims(map)
                .signWith(secretKey).compact();
    }

    public static Map<String,Object> parseJwt(String jwtToken,String permission) {
        try {
            // 根据权限获取密钥，枚举类中定义
            String key = UserLoginPermission.valueOf(permission).getValue();
            SecretKey secretKey=Keys.hmacShaKeyFor(key.getBytes());

            // 创建解析器并设置签名密钥
            Jws<Claims> jws = Jwts.parser()
                    .verifyWith(secretKey)    // HMAC密钥
                    .build()
                    .parseSignedClaims(jwtToken);

            // 获取Claims（载荷）
            return jws.getPayload();
        } catch (Exception e) {
            log.error("解析JWT失败", e);
        }
        return Map.of();
    }

}
