package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan
public class ApplicationBeanConfiguration {

    //methods
    @Profile("!dev")
    @Bean(name = "welcome")
    public String getWelcomeBean() {
        return "this is not dev";
    }

    @Profile("dev")
    @Bean(name = "welcome")
    public String getWelcomeBean2() {
        return "this is dev";
    }


}
