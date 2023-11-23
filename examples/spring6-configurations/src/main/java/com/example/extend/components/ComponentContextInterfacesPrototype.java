package com.example.extend.components;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ComponentContextInterfacesPrototype extends ComponentContextInterfaces {


    /* there are not called */
    @Override
    public void afterPropertiesSet() throws Exception {
        addMethod("afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        throw new RuntimeException();

    }
}
