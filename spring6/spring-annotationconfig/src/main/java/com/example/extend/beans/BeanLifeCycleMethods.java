package com.example.extend.beans;

/*
    Configured in BeanConfiguration using init and destroy configuration
 */
public class BeanLifeCycleMethods extends BaseBean {


    private void init() {
        System.out.println("BeanLifeCycleMethods: initMethod");
        addMethod("init");
    }

    private void destroy() {
        System.out.println("BeanLifeCycleMethods: destroyMethod");
        addMethod("destroy");
    }


}
