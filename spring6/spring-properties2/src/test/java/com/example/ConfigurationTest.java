package com.example;

import com.example.beans.MessageBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MessageConfiguration.class)
@ActiveProfiles("dev")
public class ConfigurationTest {

    @Autowired
    ApplicationContext applicationContext;


    @Autowired
    String message;

    @Test
    public void test() {
        Assertions.assertNotNull(applicationContext);
    }


    @Test
    public void test1() {
        MessageBean messageBean = applicationContext.getBean(MessageBean.class);
        Assertions.assertEquals("hello banana", message);
    }


}
