package com.example.mvc;

import com.example.mvc.model.Orders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

    /**
     * The bean logging post-processor from the bean lifecycle slides.
     */
    @Bean
    public Orders orders() {
        return new Orders();
    }
}
