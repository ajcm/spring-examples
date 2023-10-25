package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationConfigurationManualTest {

    @Test

    public void testGetMessage() {
        ConfigurableApplicationContext context
                = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        context.getEnvironment().addActiveProfile("dev");

        MessageBean messageBean = context.getBean(MessageBean.class);
        Assertions.assertEquals("Welcome", messageBean.getMessage());
    }
}
