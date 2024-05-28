package com.example;

import com.example.cars.CarService;
import com.example.cars.CarStand;
import com.example.cars.ICar;
import com.example.fruits.FruitService;
import com.example.fruits.IFruit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class ApplicationConfigurationTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    IFruit banana;


    @Autowired
    @Qualifier("strawberry")
    IFruit strawberry;

    @Autowired
    CarService carService;


    @Autowired
    ICar bmw;

    @BeforeAll
    public static void init() {
        System.out.println("Init");
    }


    @Test
    public void testApplicationContext() {
        Assertions.assertNotNull(applicationContext);
    }


    @Test
    public void testBanana() {
        Assertions.assertNotNull(banana);
    }

    @Test
    public void testFruitService(@Autowired FruitService fruitService) {
        Assertions.assertNotNull(fruitService);
        Assertions.assertEquals("I am a banana", fruitService.getFruit().getMessage());
    }

    @Test
    public void testFruitService2() {
        Assertions.assertNotNull(strawberry);
        Assertions.assertEquals("I am a Strawberry", strawberry.getMessage());
    }

    @Test
    public void testCarService() {
        Assertions.assertNotNull(carService);
        Assertions.assertEquals("I am a Audi", carService.getCar().getMessage());
    }

    @Test
    public void testCar() {
        Assertions.assertNotNull(bmw);
        Assertions.assertEquals("I am a BMW", bmw.getMessage());
    }

    @Test
    public void testCar(@Autowired ICar carBean) {
        Assertions.assertNotNull(carBean);
        Assertions.assertEquals("I am a Opel", carBean.getMessage());
    }

    @Test
    public void testCarService2(@Autowired CarStand carStand) {
        Assertions.assertNotNull(carStand);
        Assertions.assertEquals("I am a Audi", carStand.getCarService().getCar().getMessage());
    }

}
