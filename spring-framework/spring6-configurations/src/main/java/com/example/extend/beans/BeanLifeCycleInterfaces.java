package com.example.extend.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/*
    Configured in BeanConfiguration using init and destroy configuration
 */
public class BeanLifeCycleInterfaces extends BaseBean implements InitializingBean, DisposableBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("BeanLifeCycleInterfaces: afterPropertiesSet");
        addMethod("afterPropertiesSet");
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("BeanLifeCycleInterfaces: destroy");
        addMethod("destroy");
    }


}
