package com.baeldung.lsd.tools;

import com.baeldung.lsd.model.Worker;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class WorkerFactory extends AbstractFactoryBean<Worker> {

    @Autowired
    private Faker faker;

    @Override
    public Class<?> getObjectType() {
        return Worker.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    protected Worker createInstance() throws Exception {
        Worker w = new Worker();

        w.setFirstName(faker.name().firstName());
        w.setLastName(faker.name().lastName());
        w.setEmail(faker.internet().emailAddress());
        return w;
    }
}
