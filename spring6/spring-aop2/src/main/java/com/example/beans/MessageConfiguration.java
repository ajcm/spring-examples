package com.example.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:example.properties")
public class MessageConfiguration {

    @Bean
    @Primary
    public MessageBean getMessageBean() {
        return new HelloWorldBean();
    }

    @Bean({"john", "maria", "carl"})
    public MessageBean getAliasBean() {
        return new HelloWorldBean();
    }

    @Bean(name = {"louis", "sam"})
    public MessageBean getAliasBean2() {
        return new HelloWorldBean();
    }

    @Bean(name = "message")
    public String getMessage(@Value("${bean.message}") String message) {
        return message;
    }

}
