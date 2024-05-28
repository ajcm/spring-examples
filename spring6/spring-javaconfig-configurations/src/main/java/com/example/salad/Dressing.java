package com.example.salad;


import org.springframework.stereotype.Component;

@Component("dressing")
public class Dressing implements Ingredient {
    @Override
    public String getMessage() {
        return "dressing";
    }

}
