package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.Mapper.ClubMapper;
import com.example.demo.Mapper.QuestionsMapper;
import com.example.demo.Mapper.UserPermissionMapper;
import com.example.demo.pojo.DTO.QuestionDTO;
import com.example.demo.pojo.Entity.Form.Question;
import com.example.demo.pojo.Entity.MyUser;
import com.example.demo.pojo.Entity.User;
import com.example.demo.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;


@Slf4j
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    ClubMapper clubMapper;

    @Autowired
    QuestionsMapper questionsMapper;

    @Autowired
    RedisTemplate< String, Object> redisTemplate;

    @Test
    void MapperTest() {
        LambdaQueryWrapper<Question> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Question::getFormId, 103L);


        //TODO:为什么数据为空？！！！！
        List<QuestionDTO> result = questionsMapper.selectQuestionDTOList(wrapper);
        //这个正常！
        List<Question> questions = questionsMapper.selectList(wrapper);

        log.info("wrapper:{}",wrapper.getCustomSqlSegment());
        log.info("Result content:{}",result);
        log.info("Questions content:{}",questions);
    }

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
        String permissions = userPermissionMapper.getPermissionsByUserId(1L);

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


    @Test
    void redisTest() {
        User user = new User(33L, "test", 123, "cover", "major", "college", "gender", "account", "password");
        redisTemplate.opsForValue().set("user-33", new MyUser(user, List.of(1L, 2L, 3L), List.of()));
        Object object = redisTemplate.opsForValue().get("user-33");



        if (object instanceof MyUser) {
            MyUser myUser = (MyUser) object;
            System.out.println(myUser);
        } else {
            // 处理意外类型
            System.err.println("Unexpected type: " + object.getClass());
        }
    }

}
