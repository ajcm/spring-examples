package com.example.cars;

import org.springframework.stereotype.Component;

@Component
public class Audi implements ICar {

    @Override
    public String getMessage() {
        return "I am a Audi";
    }
}
