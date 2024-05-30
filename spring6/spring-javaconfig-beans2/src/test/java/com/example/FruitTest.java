package com.example;

import com.example.salad.SaladConfiguration;
import com.example.salad.fruits.IFruit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SaladConfiguration.class)
public class FruitTest {

    /**
     * Tests autowired with @Beans and @Components
     * Qualifiers
     */
    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    IFruit defaultWire;

    @Autowired
    @Qualifier("banana")
    IFruit banana;

    @Autowired
    @Qualifier("pear")
    IFruit pear;

    @Autowired
    @Qualifier("strawberry")
    IFruit strawberry;


    @Test
    public void test() {
        Assertions.assertNotNull(applicationContext);
    }


    @Test
    public void test2() {
        Assertions.assertEquals(defaultWire.getMessage(), "I am a banana");
        Assertions.assertEquals(banana.getMessage(), "I am a banana");
        Assertions.assertEquals(pear.getMessage(), "I am a pear");
        Assertions.assertEquals(strawberry.getMessage(), "I am a Strawberry");
    }

    @Test
    public void test3() {
        IFruit b = applicationContext.getBean("banana", IFruit.class);
        Assertions.assertEquals(b.getMessage(), "I am a banana");

        IFruit b1 = applicationContext.getBean("pear", IFruit.class);
        Assertions.assertEquals(b1.getMessage(), "I am a pear");
    }

}
