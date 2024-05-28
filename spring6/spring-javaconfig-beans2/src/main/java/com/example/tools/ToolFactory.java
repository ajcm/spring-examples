package com.example.tools;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

public class ToolFactory implements FactoryBean<ITool> {

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
