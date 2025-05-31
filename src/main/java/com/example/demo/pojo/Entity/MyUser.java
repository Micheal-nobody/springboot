package com.example.demo.pojo.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
//User其实才是与数据库中的User表对应的实体类，MyUser实现了UserDetails接口，用在Spring Security中。
public class MyUser implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private List<Long> allowedQuestionIds;
    private final Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    //构造方法
    public MyUser(User user, List<Long> allowedQuestionIds ,Collection<? extends GrantedAuthority> authorities){
        this.username = user.getUsername();
        this.password = user.getPassword();

        this.allowedQuestionIds = allowedQuestionIds;
        this.authorities = authorities;
    }

    public MyUser(Long id,List<Long> allowedQuestionIds,Collection<? extends GrantedAuthority> authorities){
        this.setId(id);
        this.allowedQuestionIds = allowedQuestionIds;
        this.authorities = authorities;
    }
}
