package com.example.cars;

import org.springframework.stereotype.Component;


@Component("bmw")
public class BMW implements ICar {
    @Override
    public String getMessage() {
        return "I am a BMW";
    }
}
