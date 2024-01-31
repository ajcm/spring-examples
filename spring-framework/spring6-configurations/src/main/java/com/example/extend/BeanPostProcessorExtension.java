package com.example.extend;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class BeanPostProcessorExtension implements BeanPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        var beanFactory = ((ConfigurableApplicationContext) applicationContext).getBeanFactory();

        if (beanFactory.containsBean(beanName)) {

            var scope = ((ConfigurableApplicationContext) applicationContext).getBeanFactory().getBeanDefinition(beanName).getScope();

            if ("prototype".equalsIgnoreCase(scope)) {
                System.out.println("Found prototype bean: " + beanName);
            }
        }

        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //     System.out.println("postProcessBeforeInitialization: " + beanName);
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }


}
