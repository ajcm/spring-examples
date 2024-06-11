package com.example;

import com.example.beans.MessageBean;
import com.example.beans.MessageConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MessageConfiguration.class)
public class MessageConfigurationTest {

    public static final String MESSAGE = "this is a bean";
    /**
     * Complex example with the use of annotations and autowire
     */
    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void test() {
        Assertions.assertNotNull(applicationContext);
    }


    @Test
    public void test1() {
        MessageBean messageBean = applicationContext.getBean(MessageBean.class);
        Assertions.assertEquals("hello world", messageBean.getMessage());
    }

    @Test
    public void testAliases() {
        MessageBean messageBean = (MessageBean) applicationContext.getBean("maria");
        Assertions.assertEquals("hello world", messageBean.getMessage());

        messageBean = (MessageBean) applicationContext.getBean("john");
        Assertions.assertEquals("hello world", messageBean.getMessage());

        messageBean = (MessageBean) applicationContext.getBean("sam");
        Assertions.assertEquals("hello world", messageBean.getMessage());
    }

    @Test
    public void testByCreateContext() {
        ConfigurableApplicationContext context
                = new AnnotationConfigApplicationContext(MessageConfiguration.class);
        MessageBean messageBean = context.getBean(MessageBean.class);
        Assertions.assertEquals("hello world", messageBean.getMessage());
    }

    @Test
    public void testGetMessage2() {
        ConfigurableApplicationContext context
                = new AnnotationConfigApplicationContext(MessageConfiguration.class);

        String message = context.getBean("message", String.class);
        Assertions.assertEquals("hello world", message);
    }


}
