package com.example.cars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CarService {

    @Autowired
    @Qualifier("audi")
    ICar car;

    public ICar getCar() {
        return car;
    }

    public void setCar(ICar car) {
        this.car = car;
    }
}
