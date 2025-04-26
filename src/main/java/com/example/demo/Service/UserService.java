package com.example.demo.Service;


import com.example.demo.Mapper.UserMapper;
import com.example.demo.pojo.CustomUser;
import com.example.demo.pojo.Result;
import com.example.demo.pojo.User;
import com.example.demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;


    public User getByAccountAndPassword(User user) {
        return userMapper.getByAccountAndPassword(user);
    }

//    public Result login(User user) {
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getAccount(), user.getPassword());
//        Authentication authenticate =authenticationManager.authenticate(token);
//        if(Objects.isNull(authenticate)){
//            throw new RuntimeException("用户名或密码错误");
//        }
//
////        校验成功，强转对象
//        CustomUser customUser = (CustomUser) authenticate.getPrincipal();
//        User user1 =customUser.getUser();
//
////        校验通过返回token
//        String tokenStr = JwtUtils.generateJwt(user1, "user");
//        Map<String,Object> map = new HashMap<>();
//        map.put("token",tokenStr);
//        return Result.success(map);
//    }
}
