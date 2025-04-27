//package com.example.demo.Controller;
//
//
//import com.example.demo.Service.UserService;
//import com.example.demo.pojo.ENUM.UserLoginPermission;
//import com.example.demo.pojo.Result;
//import com.example.demo.pojo.User;
//import com.example.demo.utils.JwtUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//! 改用Security框架实现登录功能
//@RestController
//@RequestMapping("/login")
//@CrossOrigin(origins = "*")
//public class LoginController {
//
//    @Autowired
//    UserService userService;
//
//    @PostMapping("/{type}")
//    public Result login(@PathVariable UserLoginPermission type, @RequestBody User user) {
//        User res = userService.getByAccountAndPassword(user);
//        if (res != null) {
//            Map<String, Object> claims = new HashMap<>();
//            claims.put("id", res.getId());
//            claims.put("username", res.getAccount());
//            String token = JwtUtils.generateJwt(claims, type.getValue()); // 确保密钥一致
//            return Result.success(token);
//        }
//
//        return Result.error("用户名或密码错误");
//    }
//
//}
