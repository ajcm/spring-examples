package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationConfigurationTest {


    @Test
    public void testGetMessage() {
        ConfigurableApplicationContext context
                = new ClassPathXmlApplicationContext(
                "classpath:context.xml");


        MessageBean messageBean = context.getBean(MessageBean.class);

        Assertions.assertEquals("this is my message", messageBean.getMessage());

    }

    @Test
    public void testRegisterShutdownHook() {
        ConfigurableApplicationContext context
                = new ClassPathXmlApplicationContext(
                "context.xml");
        context.registerShutdownHook();

    }

}
