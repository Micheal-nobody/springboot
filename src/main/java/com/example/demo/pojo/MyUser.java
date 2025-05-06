package com.example.demo.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

//User其实才是与数据库中的User表对应的实体类，MyUser实现了UserDetails接口，用在Spring Security中。
public class MyUser extends User implements UserDetails {

    private final Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    //构造方法
    public MyUser(User user, Collection<? extends GrantedAuthority> authorities){
        super(user);
        this.authorities = authorities;
    }

    public MyUser(Long id,Collection<? extends GrantedAuthority> authorities){
        this.setId(id);
        this.authorities = authorities;
    }

}
