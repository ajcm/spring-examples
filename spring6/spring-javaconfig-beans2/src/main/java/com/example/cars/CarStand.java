package com.example.cars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarStand {

    public CarService carService;

    // auto wired
    public CarStand(CarService carService) {
        this.carService = carService;
    }

    public CarService getCarService() {
        return carService;
    }

    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @Autowired
    void check(ICar bmw) {
        System.out.println("got car ->" + bmw.getMessage());
    }


}
