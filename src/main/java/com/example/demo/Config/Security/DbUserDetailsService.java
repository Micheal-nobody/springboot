package com.example.demo.Config.Security;

import com.example.demo.Mapper.QuestionsMapper;
import com.example.demo.Mapper.UserMapper;
import com.example.demo.Mapper.UserPermissionMapper;
import com.example.demo.pojo.Entity.MyUser;
import com.example.demo.pojo.Entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class DbUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserPermissionMapper userPermissionMapper;
    @Autowired
    private QuestionsMapper questionsMapper;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userMapper.getByAccount(username);
        //如果 用户不存在，则抛出异常
        if(user == null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }else {

            //获得身份
            String permission = userPermissionMapper.getPermissionsByUserId(user.getId());

            //如果是ClubManager 获得允许访问的QuestionId
            List<Long> allowedQuestionIds = null;
            if(Objects.equals(permission, "club_manager")){
                allowedQuestionIds = questionsMapper.selectAllowedQuestionIds(user.getId());
            }

            //根据permission创建授权
            Collection<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(permission);

            // 创建MyUser
            MyUser myUser = new MyUser(user, allowedQuestionIds, authorityList);

             //将用户信息保存到Redis中
            redisTemplate.opsForValue().set("user-" + user.getId(), myUser);

            //返回MyUser
            return myUser;
        }
    }
}
