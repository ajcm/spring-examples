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
@ContextConfiguration(classes = ApplicationConfiguration.class)
@ActiveProfiles(profiles = "dev")
public class ApplicationConfigurationTest {

    public static final String MESSAGE = "i think this is dev";
    /**
     * Complex example with the use of annotations and autowire
     */
    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    MessageBean messageBean;


    @Test
    public void testApplicationContext() {
        Assertions.assertNotNull(applicationContext);
        MessageBean messageBean = applicationContext.getBean(MessageBean.class);
        Assertions.assertEquals(MESSAGE, messageBean.getMessage());
    }


    @Test
    public void testMessageBean() {
        Assertions.assertEquals(MESSAGE, messageBean.getMessage());
    }

}
