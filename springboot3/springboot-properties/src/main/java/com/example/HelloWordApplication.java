package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;

@ComponentScan
@SpringBootConfiguration
@EnableConfigurationProperties(DbConfig.class) // or Configuration in class
@PropertySource("classpath:application.properties")
public class HelloWordApplication {

    @Value("${bean.message}")
    private String message;

    public static void main(String[] args) {
        SpringApplication.run(HelloWordApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Hello World.");

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }

}
