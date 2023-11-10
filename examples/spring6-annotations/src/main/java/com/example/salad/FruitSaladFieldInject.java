package com.example.salad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("fruitSaladFieldInject")
public class FruitSaladFieldInject implements FruitSalad {

    @Autowired
    Ingredient lettuce;

    @Autowired
    Ingredient tomato;

    @Autowired
    Ingredient dressing;


    @Override
    public String getMessage() {
        return "fruitSaladFieldInject";
    }


    public Ingredient getLettuce() {
        return lettuce;
    }

    public Ingredient getTomato() {
        return tomato;
    }

    public Ingredient getDressing() {
        return dressing;
    }
}
