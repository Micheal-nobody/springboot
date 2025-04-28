package com.example.demo.Config.Security;

import com.example.demo.Mapper.UserMapper;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class DbUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
//这里是测试git的数据

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("DbUserDetailsService.loadUserByUsername: "+username);

        User user = userMapper.getByAccount(username);

        if(user == null){
            System.out.println("User not found with username: " + username);
            throw new UsernameNotFoundException("User not found with username: " + username);
        }else {
            System.out.println("User found with username: " + username);

            Collection<? extends GrantedAuthority> authorities=new ArrayList<>();

            return new org.springframework.security.core.userdetails.User(
                    user.getAccount(),      //username
                    //TODO:临时方案，后续需要加密密码
                    "{noop}"+user.getPassword(),     //password
                    true,                  //enabled
                    true,                  //用户账号是否未过期
                    true,                  //用户凭证（密码）是否未过期
                    true,                  //账户是否未锁定
                    authorities            //权限列表
            );
        }
    }
}
