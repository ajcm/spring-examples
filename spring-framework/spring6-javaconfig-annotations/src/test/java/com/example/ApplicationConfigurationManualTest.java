package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


@SpringJUnitConfig(classes = ApplicationConfiguration.class)
public class ApplicationConfigurationManualTest {

    @Test
    public void testGetMessage() {
        ConfigurableApplicationContext context
                = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        MessageBean messageBean = context.getBean(MessageBean.class);
        Assertions.assertEquals("this is a bean", messageBean.getMessage());
    }
}
