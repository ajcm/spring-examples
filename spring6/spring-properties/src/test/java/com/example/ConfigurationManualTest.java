package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationManualTest {

    @Test
    public void test() {
        ConfigurableApplicationContext context
                = new AnnotationConfigApplicationContext(
                ApplicationConfiguration.class,
                ApplicationBeanConfiguration.class,
                KeyGeneratorConfiguration.class);

        context.getEnvironment().addActiveProfile("dev");

        String key = context.getBean("key", String.class);
        Assertions.assertNotNull(key);
        Assertions.assertEquals("generated key", key);

    }
}
