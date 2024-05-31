package com.example.salad.service;

import com.example.salad.fruits.IFruit;
import org.springframework.stereotype.Component;

// component scanned but wired dependency comes from a @Bean
@Component
public interface IFruitService {
    IFruit getFruit();

    String getName();
}
