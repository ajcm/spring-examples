package com.example.extend.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanConfiguration {

    @Bean
    public BeanLifecycleAnnotations beanLifecycle() {
        return new BeanLifecycleAnnotations();
    }

    @Bean
    public BeanLifeCycleInterfaces beanLifeCycleInterfaces() {
        return new BeanLifeCycleInterfaces();
    }

    @Bean
    @Scope("prototype")
    public BeanLifeCyclePrototype beanLifeCyclePrototype() {
        return new BeanLifeCyclePrototype();
    }


    @Bean(initMethod = "init", destroyMethod = "destroy")
    public BeanLifeCycleMethods beanLifeCycleMethods() {
        return new BeanLifeCycleMethods();
    }


    @Bean
    @Scope("prototype")
    public BeanContextAware beanContextAware() {
        return new BeanContextAware();
    }

}
