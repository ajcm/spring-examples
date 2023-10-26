package com.example.beanprocessors;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

// default scope is singleton
@Component
public class BeanWithLifecycleExtended implements InitializingBean, DisposableBean {


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("BeanWithLifecycleExtended: afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("BeanWithLifecycleExtended: destroy");
    }
}
