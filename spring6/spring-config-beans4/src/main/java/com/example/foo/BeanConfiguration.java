package com.example.foo;

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
    @Qualifier("superbean")
    public MyBean bean2() {
        return new MyBeanImpl("bean2");
    }

    @Bean
    public MyBean bean3() {
        return new MyBeanImpl("bean3");
    }

    @Bean
    public MyBean bean4() {
        return new MyBeanImpl("bean4");
    }

    @Bean(name = {"beanA", "beanB", "beanC"})
    public MyBean beanX() {
        return new MyBeanImpl("beanX");
    }


    @Bean
    public ComposeBean composeBean() {
        return new ComposeBean();
    }


}
