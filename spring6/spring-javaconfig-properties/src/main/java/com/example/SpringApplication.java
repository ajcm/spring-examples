package com.example;

import com.example.beans.MessageBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApplication {
    /**
     * VM Options: -Dspring.profiles.active=dev
     *
     * @param args
     */

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        String profile = (String) applicationContext.getBean("activeProfile");
        System.out.println("profile ->" + profile);


        MessageBean messageBean = (MessageBean) applicationContext.getBean("messageBean");
        System.out.println("message ->" + messageBean.getMessage());

        String message = (String) applicationContext.getBean("message");
        System.out.println("message ->" + message);

        String key = (String) applicationContext.getBean("key");
        System.out.println("key ->" + key);

        String javahome = (String) applicationContext.getBean("javahome");
        System.out.println("javahome ->" + javahome);

    }
}
