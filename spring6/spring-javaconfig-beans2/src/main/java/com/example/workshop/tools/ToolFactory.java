package com.example.workshop.tools;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

public class ToolFactory implements FactoryBean<ITool> {

    // qualifies by name
    @Autowired
    ITool hammer;

    @Override
    public ITool getObject() throws Exception {
        return hammer;
    }

    @Override
    public Class<?> getObjectType() {
        return ITool.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
