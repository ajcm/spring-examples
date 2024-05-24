package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:example.properties")
@ComponentScan
public class ApplicationConfiguration {

    /**
     * Complex example with the use of annotations and autowire
     */
    @Autowired
    SomeService someService;

    @Bean
    public MessageBean getMessageBean() {
        return new HelloWorldBean();
    }

    @Bean
    public Writer myWriter(MessageBean messageBean){ //no need for @Autowired
        return  new MyWriter(messageBean);
    }

    /**
     * autowired beans are always called after bean creation
     */
    @Autowired
    public void whatever(){
        System.out.println("whatever called x2 *****");
    }

}
