package com.example.demo.Service;

import com.example.demo.Mapper.UserMapper;
import com.example.demo.pojo.CustomUser;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getByAccount(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

//        向返回值中添加更多信息

        return new CustomUser(user, Collections.emptySet());
    }
}
