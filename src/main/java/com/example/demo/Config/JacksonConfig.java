//package com.example.demo.Config;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
//
//@Configuration
//public class JacksonConfig {
//
//    @Bean
//    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
//
//        //这个设置之后，他就把我的时间拆开了？
//        return new Jackson2ObjectMapperBuilder()
//                // 配置空集合序列化为 []（而不是 null）
//                .featuresToEnable(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS);
//    }
//
//    @Bean
//    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
//        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
//        // 全局配置：所有 null 集合字段序列化为空数组 []
//        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL)
//                    .setSerializationInclusion(JsonInclude.Include.NON_NULL);
//
//        return objectMapper;
//    }
//}