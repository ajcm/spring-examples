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
public class ConfigurationTest {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void test() {
        Assertions.assertNotNull(applicationContext);
    }

    @Test
    public void test1() {
        Assertions.assertFalse(applicationContext.containsBean("welcomeBean"));
    }

    @Test
    public void test2(@Autowired String javahome) {
        Assertions.assertTrue(javahome.length() > 1);
    }

    @Test
    public void test3(@Autowired String javahome2) {
        Assertions.assertTrue(javahome2.length() > 1);
    }

    @Test
    public void test4(@Autowired String message) {
        Assertions.assertEquals(message,"this is a message");
    }


}
