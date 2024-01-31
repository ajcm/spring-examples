package com.baeldung.lsd.tools;

import com.baeldung.lsd.model.Project;
import com.baeldung.lsd.model.Task;
import com.baeldung.lsd.model.TaskStatus;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class TaskFactory extends AbstractFactoryBean<Task> {

    private static final Random RANDOM = new Random();
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
    protected Task createInstance() throws Exception {
        Task t = new Task();

        t.setName(faker.music().instrument());
        t.setDescription(faker.space().constellation());
        t.setUuid(UUID.randomUUID().toString());

        var date = faker.date().future(10, TimeUnit.DAYS);
        LocalDate localDate = convertToLocalDateViaInstant(date);

        t.setDueDate(localDate);

        var status = List.of(TaskStatus.TODO, TaskStatus.DONE, TaskStatus.ON_HOLD, TaskStatus.IN_PROGRESS);
        t.setStatus(status.get(RANDOM.nextInt(status.size())));

        return t;
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
