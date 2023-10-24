package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:example.properties")
public class ApplicationConfiguration {

    @Bean
    public MessageBean getMessageBean(){
        return  new HelloWorldBean();
    }
}
