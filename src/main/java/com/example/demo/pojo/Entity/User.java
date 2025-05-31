package com.example.demo.pojo.Entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("users")
public class User {
    private Long id;             // 编号
    private String username;        // 姓名
    private Integer studentId;     // 学生信息
    private String cover;           // 封面
    private String major;           // 专业
    private String college;         // 学院
    private String gender;          // 性别
    private String account;         // 账号
    private String password;        // 密码

    public User(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.studentId = user.getStudentId();
        this.cover = user.getCover();
        this.major = user.getMajor();
        this.college = user.getCollege();
        this.gender = user.getGender();
        this.account = user.getAccount();
        this.password = user.getPassword();
    }
}
