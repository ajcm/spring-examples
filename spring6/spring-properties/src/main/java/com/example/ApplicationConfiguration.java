package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ApplicationConfiguration {

    @Bean(name = "activeProfile")
    public String getActiveProfile(@Value("${spring.profiles.active:}") String activeProfile) {
        return activeProfile;
    }

    @Bean(name = "javahome")
    public String getJavaHome(@Value("#{systemProperties['java.home']}") String javahome) {
        return javahome;
    }

}
