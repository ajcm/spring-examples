package com.example.salad;

import com.example.salad.fruits.Banana;
import com.example.salad.fruits.IFruit;
import com.example.salad.service.IFruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan("com.example.salad.**")
public class SaladConfiguration {

    @Bean("banana")
    @Primary
    public IFruit getBanana() {
        return new Banana();
    }

    @Bean("BeanFruitService")
    public IFruitService fruitService(@Autowired @Qualifier("strawberry") IFruit fruit) {

        return new IFruitService() {
            @Override
            public IFruit getFruit() {
                return fruit;
            }

            @Override
            public String getName() {
                return "BeanFruitService";
            }
        };

    }

}
