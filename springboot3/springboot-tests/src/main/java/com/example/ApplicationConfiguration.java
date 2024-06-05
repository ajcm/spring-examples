package com.example;

import com.example.items.domain.Item;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public Item create() {

        var i = new Item();
        i.setId(1L);
        i.setDescription("description");
        return i;
    }

}
