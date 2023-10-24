package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class ApplicationConfigurationManualTest {

    @Test
    public void testGetMessage() {
        ConfigurableApplicationContext context
                = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        MessageBean messageBean = context.getBean(MessageBean.class);
        Assertions.assertEquals("this is my message",messageBean.getMessage());
    }
}
