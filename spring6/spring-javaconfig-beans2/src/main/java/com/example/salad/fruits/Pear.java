package com.example.salad.fruits;

import org.springframework.stereotype.Component;

@Component
public class Pear implements IFruit {

    @Override
    public String getMessage() {
        return "I am a pear";
    }
}