package com.example.demo.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;             // 编号
    private String username;        // 姓名
    private Integer student_id;     // 学生信息
    private String cover;           // 封面
    private String major;           // 专业
    private String college;         // 学院
    private String gender;          // 性别
    private String account;         // 账号
    private String password;        // 密码

    public User(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.student_id = user.getStudent_id();
        this.cover = user.getCover();
        this.major = user.getMajor();
        this.college = user.getCollege();
        this.gender = user.getGender();
        this.account = user.getAccount();
        this.password = user.getPassword();
    }


}
