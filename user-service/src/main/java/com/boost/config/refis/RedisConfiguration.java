package com.boost.config.refis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableCaching // ön bellekleme işlemlerini aktif etmek için kulllanılır.
@EnableRedisRepositories
public class RedisConfiguration {

    @Value("${myredis.host}")
    String host;
    @Value("${myredis.port}")
    Integer port;


    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {

        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(host, port));
    }
}