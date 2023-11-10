package com.example;

import com.example.extend.components.ComponentAnnotations;
import com.example.extend.components.ComponentContextInterfaces;
import com.example.extend.components.ComponentContextInterfacesPrototype;
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
public class ComponentExtensionsTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    ComponentContextInterfaces componentContextInterfaces;

    @Autowired
    ComponentContextInterfacesPrototype componentContextInterfacesPrototype;

    @Autowired
    ComponentAnnotations componentAnnotations;


    @Test
    public void componentContextInterfaces() {
        Assertions.assertEquals("[setBeanName, setApplicationContext, afterPropertiesSet]", componentContextInterfaces.getMethods());
        // destroy will be called here
        ((ConfigurableApplicationContext) applicationContext).registerShutdownHook();

    }

    @Test
    public void componentContextInterfacesPrototype() {

        Assertions.assertEquals("[setBeanName, setApplicationContext, afterPropertiesSet]", componentContextInterfacesPrototype.getMethods());
        // destroy will be called here
        ((ConfigurableApplicationContext) applicationContext).registerShutdownHook();

    }


    @Test
    public void componentAnnotations() {

        Assertions.assertEquals("[init]", componentAnnotations.getMethods());
        // destroy will be called here
        ((ConfigurableApplicationContext) applicationContext).registerShutdownHook();

    }


}
