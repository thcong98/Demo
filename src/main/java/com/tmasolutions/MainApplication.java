package com.tmasolutions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.time.Duration;

@SpringBootApplication
@EnableCaching
public class MainApplication {
    public static void main(String[] args){
        SpringApplication.run(MainApplication.class,args);
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> builder
                .withCacheConfiguration("AppUser", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(90)))
                .withCacheConfiguration("Book", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(30)));
    }
}
