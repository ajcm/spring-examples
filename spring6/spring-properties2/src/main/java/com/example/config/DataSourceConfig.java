package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:database.properties")
public class DataSourceConfig {

    @Bean("databaseName")
    public String getName(@Value("${database.name}") String dbname) {
        return dbname;
    }
}
