package com.example.cars;


import com.example.ApplicationConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
public class CarTest {

    @Autowired
    @Qualifier("ferrari")
    ICar car;


    @Test
    public void test(@Autowired  @Qualifier("opel") ICar o) {
        Assertions.assertNotNull(o);
        o.setName("opel");
        var name = o.getName();

    }



    @Test
    public void test2() {
        Assertions.assertNotNull(car);
        car.setName("mycar");
        var name = car.getName();

    }
}
