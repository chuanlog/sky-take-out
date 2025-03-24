package com.sky.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Slf4j
public class RedisConfiguration {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        log.info("开始创建RedisTemplate对象");

        // 创建RedisTemplate实例，指定键和值的类型
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        try {
            // 设置连接工厂
            redisTemplate.setConnectionFactory(redisConnectionFactory);
        } catch (RedisConnectionFailureException e) {
            log.error("Redis连接失败，请检查Redis服务是否启动", e);
        }

        // 设置序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer());  // 推荐使用StringRedisSerializer来序列化键
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer()); // 使用JDK序列化器处理值

        return redisTemplate;
    }
}
