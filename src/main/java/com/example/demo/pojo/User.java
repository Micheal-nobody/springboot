package com.example.demo.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;             // 编号
    private String username;        // 姓名
    private Integer student_id;     // 学生信息
    private String cover;           // 封面
    private String major;           // 专业
    private String college;         // 学院
    private String gender;          // 性别
    private String account;         // 账号
    private String password;        // 密码

}
