package com.example.extend.components;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/*
    Configured in BeanConfiguration using init and destroy configuration
 */
@Component
public class ComponentContextInterfaces extends BaseComponent implements BeanNameAware, ApplicationContextAware, InitializingBean, DisposableBean {

    @Override
    public void setBeanName(String name) {
        addMethod("setBeanName");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        addMethod("setApplicationContext");
    }


    @Override
    public void destroy() throws Exception {
        addMethod("destroy");

        System.out.println("ComponentContextInterfaces: destroy");

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        addMethod("afterPropertiesSet");
    }
}
