package com.example.cars.german.old.whatever;

import com.example.cars.ICar;
import org.springframework.stereotype.Component;

@Component("opel")
public class Opel implements ICar {

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
