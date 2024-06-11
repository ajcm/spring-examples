package com.example;

import com.example.beans.MessageBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApplicationMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        var bean = applicationContext.getBean(MessageBean.class);
        var message = bean.getMessage();

        applicationContext.close();
    }


}
