package com.example;

import com.example.service.KeyGeneratorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class KeyGeneratorConfiguration {

    @Bean
    public KeyGeneratorService keyGenerator() {
        return new KeyGeneratorService();
    }

    @Bean("key")
    public String key(@Value("#{keyGenerator.key}") String key) {
        return key;
    }
}
