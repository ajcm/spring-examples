package com.example.extend.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/*
    Configured in BeanConfiguration using init and destroy configuration
 */
public class BeanLifeCyclePrototype extends BaseBean implements InitializingBean, DisposableBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        addMethod("afterPropertiesSet");
    }


    @Override
    public void destroy() throws Exception {
        addMethod("destroy");
        throw new RuntimeException("Should not reach here: destroy");
    }

}
