package com.example;

import com.example.cars.ICar;
import com.example.fruits.Banana;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.example.**")
@PropertySource("classpath:example.properties")
public class ApplicationConfiguration {


    @Bean
    @Primary
    public Banana get() {
        return new Banana();
    }

    @Bean("carBean")
    public ICar car(@Qualifier("opel") ICar car) {
        return car;
    }


}
