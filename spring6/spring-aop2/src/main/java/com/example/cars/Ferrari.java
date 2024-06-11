package com.example.cars;

import org.springframework.stereotype.Component;

@Component("ferrari")
public class Ferrari implements ICar {

    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
