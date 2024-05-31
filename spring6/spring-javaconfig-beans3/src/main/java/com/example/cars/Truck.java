package com.example.cars;

import com.example.cars.parts.Driver;
import com.example.cars.parts.Engine;
import com.example.cars.parts.Wheels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Truck {
    private Engine engine;
    private Engine engine2;
    private Wheels wheels;
    private Driver driver;


    public Truck(Engine engine, Engine engine2) {
        this.engine = engine;
        this.engine2 = engine2;
    }

    @Autowired
    public void set(Wheels wheels) {
        this.wheels = wheels;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Wheels getWheels() {
        return wheels;
    }

    public void setWheels(Wheels wheels) {
        this.wheels = wheels;
    }

    public Engine getEngine2() {
        return engine2;
    }

    public void setEngine2(Engine engine2) {
        this.engine2 = engine2;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
