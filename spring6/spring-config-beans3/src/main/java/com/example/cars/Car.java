package com.example.cars;

import com.example.cars.parts.Driver;
import com.example.cars.parts.Engine;
import com.example.cars.parts.Wheels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {

    private Engine engine;
    private Wheels wheels;
    private Driver driver;

    public Car() {

    }

    public Car(Engine engine) {
        this.engine = engine;
    }


    @Autowired
    public Car(Engine engine, Wheels wheels) {
        this.engine = engine;
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

    public Driver getDriver() {
        return driver;
    }

    @Autowired
    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
