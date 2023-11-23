package com.example.salad;


import org.springframework.stereotype.Component;

@Component("lettuce")
public class Lettuce implements Ingredient {

    @Override
    public String getMessage() {
        return "lettuce";
    }

}


