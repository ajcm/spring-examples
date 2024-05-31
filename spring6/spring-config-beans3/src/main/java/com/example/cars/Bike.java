package com.example.cars;

import com.example.cars.parts.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Bike {
    private Driver driver;

    public Driver getDriver() {
        return driver;
    }

    @Autowired
    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
