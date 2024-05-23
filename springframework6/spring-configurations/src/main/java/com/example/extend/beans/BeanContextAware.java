package com.example.extend.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/*
    Configured in BeanConfiguration using init and destroy configuration
 */
public class BeanContextAware extends BaseBean implements BeanNameAware, ApplicationContextAware {
    public String name;
    public ApplicationContext contex;

    @Override
    public void setBeanName(String name) {
        this.name = name;

        addMethod("setBeanName");

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.contex = applicationContext;

        addMethod("setApplicationContext");

    }
}
