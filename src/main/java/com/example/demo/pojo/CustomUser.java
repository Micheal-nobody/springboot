package com.example.demo.pojo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


@Getter
@Setter
public class CustomUser extends User {
    private com.example.demo.pojo.User user;

    public CustomUser(com.example.demo.pojo.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getAccount(), user.getPassword(), authorities);
        this.user = user;
    }

}
