package com.example.demo.Config.Security;

import com.example.demo.mapper.UserMapper;
import com.example.demo.mapper.UserPermissionMapper;
import com.example.demo.pojo.MyUser;
import com.example.demo.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Slf4j
@Component
public class DbUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserPermissionMapper userPermissionMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userMapper.getByAccount(username);

        if(user == null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }else {
//            log.info("User found with username: {}", username);

            List<String> permissions = userPermissionMapper.getPermissionsByUserId(user.getId());

            Collection<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(permissions);
            MyUser myUser = new MyUser(user, authorityList);


            //TODO:临时方案，之后数据库中密码加密
            myUser.setPassword("{noop}"+user.getPassword());
            return myUser;
        }
    }
}
