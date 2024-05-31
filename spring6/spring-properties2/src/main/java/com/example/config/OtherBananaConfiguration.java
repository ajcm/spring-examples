package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration("fakeBanana")
@PropertySource("classpath:banana.properties")
public class OtherBananaConfiguration {
    @Value("${bean.message2}")
    String message;

    @Bean(name = "message")
    public String getMessage() {
        return message;
    }
}
