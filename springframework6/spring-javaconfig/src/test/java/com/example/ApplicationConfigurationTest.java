package com.example;

import com.example.qualifier.MyBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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


    //Test Qualifiers with bean
    @Autowired
    @Qualifier("firstBean")
    MyBean myBean;

    @Autowired
    @Qualifier("secondBean")
    MyBean mySecondBean;



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
    public void testGetMessage() {
        ConfigurableApplicationContext context
                = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        MessageBean messageBean = context.getBean(MessageBean.class);
        Assertions.assertEquals("this is a bean", messageBean.getMessage());
    }

    @Test
    public void testGetMessage2() {
        ConfigurableApplicationContext context
                = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        String message = context.getBean("message", String.class);
        Assertions.assertEquals("this is a bean", message);
    }


    @Test
    public void testMyBean() {
        Assertions.assertEquals("FirstMyBean", myBean.getMessage());
        Assertions.assertEquals("SecondMyBean", mySecondBean.getMessage());
    }

}
