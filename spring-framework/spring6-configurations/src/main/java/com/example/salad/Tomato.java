package com.example.salad;

import org.springframework.stereotype.Component;

@Component("tomato")
public class Tomato implements Ingredient {

    @Override
    public String getMessage() {
        return "tomato";
    }

}
