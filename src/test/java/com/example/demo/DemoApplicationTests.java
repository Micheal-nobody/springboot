package com.example.demo;

import com.example.demo.mapper.UserPermissionMapper;
import com.example.demo.utils.JwtUtils;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;


//@SpringBootTest
class DemoApplicationTests {

    @Test
    void jwtparser() {
        String token = "eyJhbGciOiJIUzM4NCJ9.eyJ1c2VySWQiOjMzLCJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dfQ.iELTJAQTUWHr-PEX3rM6vm2MbUFRPshIe7mYvGba5S8tfPQCb-ojvGC_Griw00fE";

        Map<String, Object> objectMap = JwtUtils.parseJwt(token);
        System.out.println(objectMap);
    }

//    @Autowired
    UserPermissionMapper userPermissionMapper;

    @Test
    void mybatisTest(){

//        UserPermission userPermission = userPermissionMapper.selectById(1L);
//        System.out.println(userPermission);
        List<String> permissions = userPermissionMapper.getPermissionsByUserId(1L);


        System.out.println(permissions);


    }

    @Test
    void contextLoads() {
        Integer integer = 123;
        System.out.println(integer.getClass());
        Long longObj = (long) integer; // 简洁且高效

        System.out.println(longObj);
        System.out.println(longObj.getClass());
    }

}
