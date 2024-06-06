package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan
@PropertySource("prop.properties")
public class ApplicationConfiguration {

    @Bean(name = "activeProfile")
    public String getActiveProfile(@Value("${spring.profiles.active:}") String activeProfile) {
        return activeProfile;
    }

    @Bean(name = "javahome")
    public String getJavaHome(@Value("#{systemProperties['java.home']}") String javahome) {
        return javahome;
    }

    @Bean(name = "javahome2")
    public String getJavaHome2(@Value("${java.home}") String javahome) {
        return javahome;
    }


    @Value("${app.message}")
    private String message;

    @Bean(name = "message")
    public String getMessage() {
        return message;
    }

}
