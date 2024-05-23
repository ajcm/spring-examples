package com.example;

import com.example.salad.FruitSaladConstructor;
import com.example.salad.FruitSaladFieldInject;
import com.example.salad.FruitSaladSetters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class AutoWiringTypesTest {

    @Autowired
    @Qualifier("fruitSaladConstructor")
    FruitSaladConstructor fruitSaladConstructor;

    @Autowired
    @Qualifier("fruitSaladFieldInject")
    FruitSaladFieldInject fruitSaladFieldInject;

    @Autowired
    @Qualifier("fruitSaladSetters")
    FruitSaladSetters fruitSaladSetters;

    @Test
    public void fruitSaladConstructor() {
        Assertions.assertNotNull(fruitSaladConstructor);
        Assertions.assertEquals("fruitSaladConstructor",fruitSaladConstructor.getMessage());

        Assertions.assertNotNull(fruitSaladConstructor.getTomato());
        Assertions.assertNotNull(fruitSaladConstructor.getLettuce());
        Assertions.assertNotNull(fruitSaladConstructor.getDressing());

        Assertions.assertEquals("tomato",fruitSaladConstructor.getTomato().getMessage());
        Assertions.assertEquals("lettuce",fruitSaladConstructor.getLettuce().getMessage());
        Assertions.assertEquals("dressing",fruitSaladConstructor.getDressing().getMessage());

    }


    @Test
    public void fruitSaladFieldInject() {
        Assertions.assertNotNull(fruitSaladFieldInject);
        Assertions.assertEquals("fruitSaladFieldInject",fruitSaladFieldInject.getMessage());

        Assertions.assertNotNull(fruitSaladFieldInject.getTomato());
        Assertions.assertNotNull(fruitSaladFieldInject.getLettuce());
        Assertions.assertNotNull(fruitSaladFieldInject.getDressing());

        Assertions.assertEquals("tomato",fruitSaladFieldInject.getTomato().getMessage());
        Assertions.assertEquals("lettuce",fruitSaladFieldInject.getLettuce().getMessage());
        Assertions.assertEquals("dressing",fruitSaladFieldInject.getDressing().getMessage());

    }

    @Test
    public void fruitSaladSetters() {
        Assertions.assertNotNull(fruitSaladSetters);
        Assertions.assertEquals("fruitSaladSetters",fruitSaladSetters.getMessage());

        Assertions.assertNotNull(fruitSaladSetters.getTomato());
        Assertions.assertNotNull(fruitSaladSetters.getLettuce());
        Assertions.assertNotNull(fruitSaladSetters.getDressing());

        Assertions.assertEquals("tomato",fruitSaladSetters.getTomato().getMessage());
        Assertions.assertEquals("lettuce",fruitSaladSetters.getLettuce().getMessage());
        Assertions.assertEquals("dressing",fruitSaladSetters.getDressing().getMessage());

    }


}
