package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//inject manually this is not spring boot
@PropertySource("classpath:application.properties")
@ComponentScan
public class MessageConfiguration {

    @Bean(name = "message")
    public String getMessage(@Value("${bean.message}") String message) {
        return message;
    }

}
