package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class ComponentScanTest {

    /**
     * Complex example with the use of annotations and autowire
     */
    @Autowired
    SomeService someService;

    @Test
    public void testMessageBean() {
        Assertions.assertEquals("this is a stereotype", someService.getMessage());
    }

}
