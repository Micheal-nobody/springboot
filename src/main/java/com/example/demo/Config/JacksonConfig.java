//package com.example.demo.Config;
//
//import com.example.demo.Serializer.SimpleGrantedAuthorityDeserializer;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//@Configuration
//public class JacksonConfig {
//
//    @Bean
//    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
//        return jackson2ObjectMapperBuilder -> jackson2ObjectMapperBuilder
//                        .deserializerByType(SimpleGrantedAuthority.class, new SimpleGrantedAuthorityDeserializer());
//    }
//
//
////    @Bean
////    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
////        return new Jackson2ObjectMapperBuilder()
////    }
//}