package com.example;

import com.example.message.MessageBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApplicationMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);


        var bean = applicationContext.getBean("hello", MessageBean.class);

        System.out.println("-> " + bean.getMessage());
        applicationContext.close();
    }


}
