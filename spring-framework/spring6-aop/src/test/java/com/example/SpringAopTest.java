package com.example;

import com.example.message.MessageBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class SpringAopTest {

    @Qualifier("hello")
    @Autowired
    MessageBean helloBean;


    @Test
    public void test() {
        var msg = helloBean.getMessage();
        System.out.println("hello: " + msg);

    }


}
