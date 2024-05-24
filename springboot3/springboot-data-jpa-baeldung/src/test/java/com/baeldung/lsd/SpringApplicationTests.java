package com.baeldung.lsd;

import com.baeldung.lsd.model.Project;
import com.baeldung.lsd.model.Task;
import com.baeldung.lsd.model.Worker;
import com.baeldung.lsd.repository.ProjectRepository;
import com.baeldung.lsd.repository.TaskRepository;
import com.baeldung.lsd.repository.WorkerRepository;
import com.baeldung.lsd.tools.ProjectFactory;
import com.baeldung.lsd.tools.TaskFactory;
import com.baeldung.lsd.tools.WorkerFactory;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class SpringApplicationTests {

    private static final Random RANDOM = new Random();

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectFactory projectFactory;

    @Autowired
    private WorkerFactory workerFactory;

    @Autowired
    private TaskFactory taskFactory;


    @Test
    void contextLoads() {
    }

    @Test
    void testFaker(@Autowired Faker faker) {
        Assertions.assertNotNull(faker);
    }

    @Test
    void testWorker(@Autowired WorkerRepository workerRepository) {
        Assertions.assertNotNull(workerRepository);
    }

    @Test
    void test() throws Exception {


        for (int i = 0; i < 5; i++) {
            Project p = projectFactory.getObject();
            projectRepository.save(p);
        }

        for (int i = 0; i < 5; i++) {
            Worker w = workerFactory.getObject();
            workerRepository.save(w);
        }

        var projects = projectRepository.findAll();

        for (int i = 0; i < 50; i++) {
            Task t = taskFactory.getObject();

            var p = projects.get(RANDOM.nextInt(projects.size()));

            t.setProject(p);
            taskRepository.save(t);
        }


        var workers = workerRepository.findAll();
        var tasks = taskRepository.findAll();

        System.out.println("Projects:");
        projects.forEach(System.out::println);

        System.out.println("Workers:");
        workers.forEach(System.out::println);

        System.out.println("Tasks:");
        tasks.forEach(System.out::println);

    }


}
