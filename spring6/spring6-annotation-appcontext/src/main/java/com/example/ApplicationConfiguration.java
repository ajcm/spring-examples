package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public MessageBean getMessageBean(){
        return  new HelloWorldBean();
    }
}
