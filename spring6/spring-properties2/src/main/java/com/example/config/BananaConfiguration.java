package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration("banana")
@PropertySource("classpath:banana.properties")
public class BananaConfiguration {
    @Value("${bean.message}")
    String message;

    @Bean(name = "message")
    public String getMessage() {
        return message;
    }
}
