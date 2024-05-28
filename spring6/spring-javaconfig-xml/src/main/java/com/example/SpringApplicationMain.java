package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplicationMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        MessageBean messageBean = (MessageBean) applicationContext.getBean("messageBean");
        System.out.println("->" + messageBean.getMessage());
    }
}
