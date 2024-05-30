package com.example;

import com.example.cars.Bike;
import com.example.cars.Car;
import com.example.cars.Truck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@ComponentScan
public class ApplicationTest {

    @Autowired
    ApplicationContext applicationContext;


    @Test
    public void test() {
        Car car = applicationContext.getBean(Car.class);
        Assertions.assertNotNull(car);

        Assertions.assertNotNull(car.getDriver());
        Assertions.assertNotNull(car.getEngine());
        Assertions.assertNotNull(car.getWheels());
    }

    @Test
    public void test2() {
        Bike bike = applicationContext.getBean(Bike.class);
        Assertions.assertNotNull(bike);

        Assertions.assertNotNull(bike.getDriver());
    }

    @Test
    public void test3() {
        Truck truck = applicationContext.getBean(Truck.class);

        Assertions.assertNotNull(truck);
        Assertions.assertNotNull(truck.getEngine());
        Assertions.assertNotNull(truck.getEngine2());
    }


}
