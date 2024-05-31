package com.example.workshop.factories;

import com.example.workshop.tools.ITool;
import com.example.workshop.tools.Screwdriver;
import org.springframework.beans.factory.config.AbstractFactoryBean;

public class ImprovedToolFactory extends AbstractFactoryBean<ITool> {

    @Override
    public Class<?> getObjectType() {
        return ITool.class;
    }

    @Override
    protected ITool createInstance() throws Exception {
        return new Screwdriver();
    }
}