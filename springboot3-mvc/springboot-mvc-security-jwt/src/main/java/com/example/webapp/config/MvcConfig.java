package com.example.webapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/user").setViewName("user");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/access-denied").setViewName("access-denied");

        //jwt
        registry.addViewController("/rest/login").setViewName("rest/login");
        registry.addViewController("/rest/userinfo").setViewName("rest/userinfo");
        registry.addViewController("/rest/public").setViewName("rest/public");
    }

}
