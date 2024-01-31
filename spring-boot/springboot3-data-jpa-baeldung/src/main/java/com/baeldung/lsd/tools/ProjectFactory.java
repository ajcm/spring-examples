package com.baeldung.lsd.tools;

import com.baeldung.lsd.model.Project;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class ProjectFactory extends AbstractFactoryBean<Project> {

    @Autowired
    private Faker faker;

    @Override
    public Class<?> getObjectType() {
        return Project.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    protected Project createInstance() throws Exception {
        Project p = new Project();

        p.setDescription(faker.commerce().productName());
        p.setName(faker.beer().name());
        var code = (faker.color().name() + "-" + faker.random().nextInt(1000));
        p.setCode(code.replaceAll(" ", "_").toLowerCase());

        return p;
    }
}
