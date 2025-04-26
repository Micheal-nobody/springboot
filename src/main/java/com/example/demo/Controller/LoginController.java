package com.example.demo.Controller;


import com.example.demo.Service.UserService;
import com.example.demo.pojo.ENUM.UserLoginPermission;
import com.example.demo.pojo.Result;
import com.example.demo.pojo.User;
import com.example.demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/{type}")
    public Result login(@PathVariable UserLoginPermission type, @RequestBody User user) {
        return Result.success();

//        User  res= userService.getByAccountAndPassword(user);
//        if (res!= null) {
//            Map<String, Object> claims=new HashMap<>();
//            claims.put("id", res.getId());
//
//            return Result.success(JwtUtils.generateJwt(claims, type.getValue()));
//        }
//        return Result.error("用户名或密码错误");
    }

//    @PostMapping("/")
//    public Result login(@RequestBody User user) {
//        return userService.login(user);
//    }
}
