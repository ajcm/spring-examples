package com.example;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplicationMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext context
                = new ClassPathXmlApplicationContext(
                "classpath:context.xml");

        MessageBean messageBean = context.getBean(MessageBean.class);
        System.out.println(messageBean.getMessage());

        context.registerShutdownHook();

    }
}
