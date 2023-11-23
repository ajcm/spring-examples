package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource("classpath:example.properties")
@ComponentScan
public class ApplicationConfiguration {

    @Value("${spring.profiles.active:}")
    private String activeProfile;

    @Value("#{systemProperties['java.home']}")
    private String javahome;

    @Profile("!dev")
    @Bean(name = "messageBean")
    public MessageBean getWelcomeBean() {
        return new WelcomeBean();
    }

    @Profile("dev")
    @Bean(name = "messageBean")
    public MessageBean getMessageBean() {
        return new HelloWorldBean();
    }

    @Bean(name = "message")
    public String messageString(@Value("${message}") String message) {
        return message;
    }

    @Bean(name = "javahome")
    public String getJavahome() {
        return javahome;
    }

    @Bean(name = "activeProfile")
    public String getMessage() {
        return activeProfile;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator();
    }

    @Bean
    public String key(@Value("#{keyGenerator.key}") String key) {
        return key;
    }
}
