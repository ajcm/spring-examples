package com.example;

import com.example.beans.MessageBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


@SpringJUnitConfig
@ContextConfiguration(classes = HelloWordApplication.class)
//
public class PropsApplication2Test {

    @Autowired
    ApplicationContext applicationContext;


    @Test
    public void test() {
        Assertions.assertNotNull(applicationContext);
    }


    @Test
    public void test1(@Autowired  MessageBean message) {
        Assertions.assertNotNull(message);
     //   Assertions.assertEquals(message.getMessage(),"");
    }



}
