package com.example.qualifier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:example.properties")
public class QualifiersConfiguration {

//    @Bean
//    public MessageBean getMessageBean() {
//        return new HelloWorldBean();
//    }
//
//    @Bean(name = "message")
//    public String getMessage(@Value("${bean.message}") String message) {
//        return message;
//    }

    @Bean("firstBean")
    public MyBean myBean1() {
        return new FirstMyBean();
    }

    @Bean("secondBean")
    public MyBean myBean2() {
        return new SecondMyBean();
    }

}
