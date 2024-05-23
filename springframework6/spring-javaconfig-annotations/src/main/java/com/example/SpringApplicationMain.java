package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApplicationMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        MessageBean messageBean = applicationContext.getBean(MessageBean.class);
        System.out.println("->" + messageBean.getMessage());
    }
}
