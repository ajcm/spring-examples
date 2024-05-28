package com.example;

import com.example.beans.MessageBean;
import com.example.beans.SomeService;
import com.example.beans.Writer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class ApplicationConfigurationTest {

    public static final String MESSAGE = "this is a bean";
    /**
     * Complex example with the use of annotations and autowire
     */
    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    MessageBean messageBean;

    @Autowired
    Writer writer;
    @Autowired
    SomeService someService;

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

    @Test
    public void testWriter() {
        Assertions.assertEquals("this is a bean", writer.write());
    }

    @Test
    public void testService() {
        Assertions.assertEquals("this is a stereotype", someService.getMessage());
    }


}
