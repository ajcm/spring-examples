package com.example;

import com.example.extend.beans.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class BeanExtensionsTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    BeanContextAware beanContextAware;

    @Autowired
    BeanLifecycleAnnotations beanLifecycleAnnotations;

    @Autowired
    BeanLifeCycleInterfaces beanLifeCycleInterfaces;

    @Autowired
    BeanLifeCyclePrototype beanLifeCyclePrototype;

    @Autowired
    BeanLifeCycleMethods beanLifeCycleMethods;

    @Test
    public void beanContextAware() {
        Assertions.assertEquals("beanContextAware", beanContextAware.name);

        var configurableApplicationContext =  (ConfigurableApplicationContext )beanContextAware.contex;
        var scope = configurableApplicationContext.getBeanFactory().getBeanDefinition(beanContextAware.name).getScope();

        Assertions.assertEquals("prototype",scope);
    }


    @Test
    public void testMethods(){
        Assertions.assertEquals("[setBeanName, setApplicationContext]", beanContextAware.getMethods());

        Assertions.assertEquals("[init]", beanLifecycleAnnotations.getMethods());

        Assertions.assertEquals("[afterPropertiesSet]", beanLifeCycleInterfaces.getMethods());

        Assertions.assertEquals("[afterPropertiesSet]", beanLifeCyclePrototype.getMethods());

        Assertions.assertEquals("[init]", beanLifeCycleMethods.getMethods());


    }

}
