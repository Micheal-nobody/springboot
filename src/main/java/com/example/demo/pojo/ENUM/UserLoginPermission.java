package com.example.demo.pojo.ENUM;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserLoginPermission {
    normal("这是一段很长的密钥，用来生成normal权限的token，被放在jwt的payload中"),
    admin("这是一段很长的密钥，用来生成admin权限的token，被放在jwt的payload中"),
    premium("这是一段很长的密钥，用来生成premium权限的token，被放在jwt的payload中"),
    guest("这是一段很长的密钥，用来生成guest权限的token，被放在jwt的payload中");

    private final String value;
}
