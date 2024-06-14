package com.example;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/*
    Configured in BeanConfiguration using init and destroy configuration
 */
public class BeanLifeCycle  implements InitializingBean, DisposableBean  {

    @PostConstruct
    private void postConstruct() {
        System.out.println("BeanLifeCycle: postConstruct");
    }

    @PreDestroy
    private void preDestroy() {
        System.out.println("BeanLifeCycle: preDestroy");
    }


    private void initMethod() {
        System.out.println("BeanLifeCycle: initMethod");
    }

    private void destroyMethod() {
        System.out.println("BeanLifeCycle: destroyMethod");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("BeanLifeCycle: InitializingBean.afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("BeanLifeCycle: DisposableBean.destroy");
    }

}
