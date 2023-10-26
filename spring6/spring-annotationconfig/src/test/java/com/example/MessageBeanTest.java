package com.example;

import com.example.message.MessageBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class MessageBeanTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    @Qualifier("hello")
    MessageBean helloBean;

    @Autowired
    @Qualifier("welcome")
    MessageBean welcomeBean;


    @Test
    public void load() {
        Assertions.assertNotNull(applicationContext);
    }

    @Test
    public void messages() {
        Assertions.assertNotNull(helloBean);
        Assertions.assertEquals("this is hello!", helloBean.getMessage());

        Assertions.assertNotNull(welcomeBean);
        Assertions.assertEquals("welcome", welcomeBean.getMessage());
    }


}
