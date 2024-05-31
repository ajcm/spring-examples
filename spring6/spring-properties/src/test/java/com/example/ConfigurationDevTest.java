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
@ContextConfiguration(classes = {ApplicationBeanConfiguration.class})
@ActiveProfiles(profiles = "dev")
public class ConfigurationDevTest {

    @Autowired
    MessageBean messageBeanWireByClass;

    @Autowired
    MessageBean welcomeBean;

    @Autowired
    String welcome;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void test() {
        Assertions.assertEquals("this is dev", welcome);
    }

    @Test
    public void test2() {
        Assertions.assertEquals("this is dev", messageBeanWireByClass.getMessage());
        Assertions.assertEquals("this is dev", welcomeBean.getMessage());
    }


}
