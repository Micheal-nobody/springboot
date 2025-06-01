package com.example.demo.Config;

import com.example.demo.Serializer.SimpleGrantedAuthorityDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        // 创建安全的多态类型验证器
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfSubType("com.example.demo.pojo.Entity") // 允许您的实体包
                .allowIfSubType("java.util") // 允许Java集合
                .allowIfSubType("org.springframework.security.core.authority") // 允许权限类
                .build();

        // 创建并配置自定义 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        // 关键：启用多态类型处理
        mapper.activateDefaultTyping(
                ptv,
                ObjectMapper.DefaultTyping.NON_FINAL
        );

        // 注册自定义反序列化器
        SimpleModule module = new SimpleModule();
        module.addDeserializer(
                SimpleGrantedAuthority.class,
                new SimpleGrantedAuthorityDeserializer() // 使用您之前创建的
        );
        mapper.registerModule(module);

        // 使用自定义 ObjectMapper 创建序列化器
        GenericJackson2JsonRedisSerializer serializer =
                new GenericJackson2JsonRedisSerializer(mapper);

        // 设置序列化器
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }
}
