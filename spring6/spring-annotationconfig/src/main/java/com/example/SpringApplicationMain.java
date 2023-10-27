package com.example;

import com.example.extend.beans.BeanContextAware;
import com.example.extend.beans.BeanLifeCycleMethods;
import com.example.extend.beans.BeanLifecycleAnnotations;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Objects;

public class SpringApplicationMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);


        testBeanExtensions(applicationContext);

        applicationContext.close();
    }


//    public static  void testBeanExtensions(ConfigurableApplicationContext applicationContext){
//
//        var bean  = applicationContext.getBean(ComponentLifecycleAnnotations.class);
//
//        Objects.requireNonNull(bean);
//    }


    public static void testBeanExtensions(ConfigurableApplicationContext applicationContext) {

        var bean = applicationContext.getBean(BeanLifecycleAnnotations.class);
        Objects.requireNonNull(bean);

        var bean2 = applicationContext.getBean(BeanLifeCycleMethods.class);
        Objects.requireNonNull(bean2);

        var bean3 = applicationContext.getBean(BeanLifeCycleMethods.class);
        Objects.requireNonNull(bean3);

        var bean4 = applicationContext.getBean(BeanContextAware.class);
        Objects.requireNonNull(bean4);

    }
}
