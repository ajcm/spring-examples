package com.baeldung.lsd.config;

import com.github.javafaker.Faker;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.baeldung.lsd.**")
@EntityScan("com.baeldung.lsd.**")
public class ApplicationConfiguration {


    @Bean
    public Faker faker() {
        return new Faker();
    }

}
