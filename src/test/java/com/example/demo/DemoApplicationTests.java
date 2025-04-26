package com.example.demo;

import com.example.demo.Mapper.*;
import com.example.demo.pojo.ENUM.QuestionType;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Key;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FormMapper formMapper;
    @Autowired
    private AnswersMapper answersMapper;
    @Autowired
    private QuestionsMapper questionsMapper;
    @Autowired
    private ClubMapper clubMapper;
    @Autowired
    private OptionsMapper optionsMapper;
    @Autowired
    private FileMapper fileMapper;

    @Test
    void selectTest() {
//        userMapper.getAllUsers();
//        formMapper.getAllForms();
//        answersMapper.getAllAnswers();
//        questionsMapper.getQuestionsByFormId(1L);
//        clubMapper.getAllClubs();
//        optionsMapper.getAllOptions();
//        List<Option>   result = optionsMapper.getOptionByQuestionId(2L);
//        System.out.println(result.toArray().length);
//        clubMapper.getClubById(1L);
//        questionsMapper.getQuestionsByFormId(1L);
//        questionsMapper.getAllQuestions();
//        clubMapper.getClubById(1L);
//        fileMapper.getImageById(1L);
        formMapper.getByClubId(1L);
    }



    @Test
    void insertTest() {
//        User user = new User();
//        user.setUsername("test");
//        user.setStudent_id(999);
//        user.setCover("test");
//        user.setMajor("test");
//        user.setCollege("test");
//        user.setGender("男");
//        user.setAccount("test");
//        user.setPassword("test");
//        userMapper.insertUser(user);

        System.out.println("insert success");
//        System.out.println("userid="+user.getId());
    }

    String genJwt(){

        Key key =Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String secretKeyString = "这是一个非常安全的密钥字符串，且长度必须足够";
        Key specificKey = Keys.hmacShaKeyFor(secretKeyString.getBytes());

        String compact = Jwts.builder()
                .header()
                .add("header", "头部！")
                .and()
                .subject("这是subject添加的内容")
                .claim("role", "admin")
                .signWith(SignatureAlgorithm.HS256, specificKey)
                .compact();

        System.out.println(compact);
        return compact;
    }


    @Test
    void genJwtTest() {
        // 生成jwt
        String compact = genJwt();

        //构建解析器
        JwtParser parser = Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor("这是一个非常安全的密钥字符串，且长度必须足够".getBytes()))
                .build();
        //解析jwt
        Jws<Claims> claimsJws = parser.parseClaimsJws(compact);

        // 如果你想查看claims的内容，可以使用claimsJws.getBody()
        System.out.println(claimsJws.getBody());
    }


    @Test
    void test(){
//        System.out.println(Arrays.toString(QuestionType.values()));;
        System.out.println(QuestionType.MULTI_SELECT.name());
        System.out.println(QuestionType.MULTI_SELECT.getValue());
    }



}
