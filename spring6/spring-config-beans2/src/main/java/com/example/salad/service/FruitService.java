package com.example.salad.service;

import com.example.salad.fruits.IFruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// component scanned but wired dependency comes from a @Bean
@Component
public class FruitService implements IFruitService {

    @Autowired
    IFruit fruit;

    public IFruit getFruit() {
        return fruit;
    }

    public void setFruit(IFruit fruit) {
        this.fruit = fruit;
    }

    @Override
    public String getName() {
        return "FruitService";
    }
}
