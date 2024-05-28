package com.example.fruits;

import org.springframework.stereotype.Component;

@Component("strawberry")
public class Strawberry implements IFruit {
    @Override
    public String getMessage() {
        return "I am a Strawberry";
    }
}
