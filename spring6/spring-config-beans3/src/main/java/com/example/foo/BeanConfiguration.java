package com.example.foo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {


    @Bean
    public MyBean bean1() {
        return new MyBeanImpl("bean1");
    }

    @Bean
    public MyBean bean2() {
        return new MyBeanImpl("bean2");
    }

    @Bean
    public MyBean bean3() {
        return new MyBeanImpl("bean3");
    }


    @Bean("setBean")
    @Autowired
    public MyBean setBean(@Qualifier("bean2") MyBean bean1) {

        return bean1;


    }

}
