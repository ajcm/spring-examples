package com.example;

import com.example.config.DataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource("classpath:profiles.properties")
@Profile("profileb")
@Import(DataSourceConfig.class)
public class SwapConfiguration2 {

    @Bean(name = "configMessage")
    public String getMessage(@Value("${bean.message2}") String message) {
        return message;
    }

    @Autowired
    public DataSourceConfig getDatabaseConfig(@Autowired DataSourceConfig dataSourceConfig) {
        return dataSourceConfig;
    }

}
