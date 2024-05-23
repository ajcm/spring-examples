package com.baeldung.lsd;


import com.baeldung.lsd.model.Project;
import com.baeldung.lsd.repository.ProjectRepository;
import com.baeldung.lsd.repository.TaskRepository;
import com.baeldung.lsd.repository.WorkerRepository;
import com.baeldung.lsd.services.ProjectService;
import com.baeldung.lsd.tools.ProjectFactory;
import com.baeldung.lsd.tools.WorkerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;

import java.util.Optional;

@DataJpaTest
@ComponentScan("com.baeldung.lsd.**")
public class OtherSpringJpaTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProjectFactory projectFactory;


    @Autowired
    private WorkerFactory workerFactory;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private ProjectService projectService;

    @Test
    public void test() {
        Optional<Project> optional = projectRepository.findByIdEquals(1L);
        Assertions.assertTrue(optional.isPresent());

        var project = optional.get();
        var tasks = project.getTasks();

        tasks.forEach(System.out::println);


        projectService.close(project);

        Optional<Project> optional2 = projectRepository.findById(1L);
        Assertions.assertTrue(optional2.isPresent());

        var project2 = optional2.get();
        var tasks2 = project2.getTasks();

        System.out.println("Done");

        tasks2.forEach(System.out::println);

    }


}
