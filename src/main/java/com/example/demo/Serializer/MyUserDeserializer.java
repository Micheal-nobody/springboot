//package com.example.demo.Serializer;
//
//import com.example.demo.pojo.Entity.MyUser;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MyUserDeserializer extends JsonDeserializer<MyUser> {
//
//
//    //{
//    // "@class":"com.example.demo.pojo.Entity.MyUser",
//    // "id":33,
//    // "username":"test",
//    // "password":"{noop}test",
//    // "allowedQuestionIds":["java.util.ArrayList",[189,190,187,185,191,192,null]],
//    // "authorities":["java.util.ArrayList",
//    //   [{"@class":"org.springframework.security.core.authority.SimpleGrantedAuthority","authority":"club_manager"}]],
//    // "enabled":true,
//    // "credentialsNonExpired":true,
//    // "accountNonExpired":true,
//    // "accountNonLocked":true
//    // }
//    @Override
//    public MyUser deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
//        ObjectNode node = p.getCodec().readTree(p);
//
//        MyUser user = new MyUser();
//
//        user.setId(node.get("id").asLong());
//        user.setUsername(node.get("username").asText());
//        user.setPassword(node.get("password").asText());
//
//        // Deserialize allowedQuestionIds
//        List<Long> allowedQuestionIds = new ArrayList<>();
//        node.withArray("allowedQuestionIds").forEach(item -> allowedQuestionIds.add(item.asLong()));
//        user.setAllowedQuestionIds(allowedQuestionIds);
//
//        // Deserialize authorities
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        node.withArray("authorities").forEach(item -> authorities.add(new SimpleGrantedAuthority(item.get("authority").asText())));
//        user.setAuthorities(authorities);
//
//        user.setEnabled(node.get("enabled").asBoolean());
//        user.setCredentialsNonExpired(node.get("credentialsNonExpired").asBoolean());
//        user.setAccountNonExpired(node.get("accountNonExpired").asBoolean());
//        user.setAccountNonLocked(node.get("accountNonLocked").asBoolean());
//
//        return user;
//    }
//}
