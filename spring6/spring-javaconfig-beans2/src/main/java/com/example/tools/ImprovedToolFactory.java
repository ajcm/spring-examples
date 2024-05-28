package com.example.tools;

import org.springframework.beans.factory.config.AbstractFactoryBean;

public class ImprovedToolFactory extends AbstractFactoryBean<ITool> {

    private int factoryId;
    private int toolId;

    @Override
    public Class<?> getObjectType() {
        return ITool.class;
    }

    @Override
    protected ITool createInstance() throws Exception {
        return new Screwdriver();
    }

    // standard setters and getters
}