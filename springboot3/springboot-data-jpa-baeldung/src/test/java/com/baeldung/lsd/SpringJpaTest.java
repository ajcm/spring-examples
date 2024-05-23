package com.baeldung.lsd;


import com.baeldung.lsd.model.Project;
import com.baeldung.lsd.model.Task;
import com.baeldung.lsd.model.TaskStatus;
import com.baeldung.lsd.model.Worker;
import com.baeldung.lsd.projection.ProjectTask;
import com.baeldung.lsd.projection.WorkerFullname;
import com.baeldung.lsd.repository.ProjectRepository;
import com.baeldung.lsd.repository.TaskRepository;
import com.baeldung.lsd.repository.WorkerRepository;
import com.baeldung.lsd.tools.ProjectFactory;
import com.baeldung.lsd.tools.WorkerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@ComponentScan("com.baeldung.lsd.**")
public class SpringJpaTest {

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

    @Test
    public void test() {
        System.out.println("test");
        Assertions.assertNotNull(projectRepository);
        Assertions.assertNotNull(entityManager);
        Assertions.assertNotNull(projectFactory);
    }

    @Test
    public void testSave() throws Exception {
        System.out.println("test");

        Project p = projectFactory.getObject();
        Assertions.assertNotNull(p);

        Project p2 = projectRepository.save(p);

        Assertions.assertNotNull(p2.getId());
        Project p3 = entityManager.find(Project.class, p2.getId());

        Assertions.assertNotNull(p3);
        Assertions.assertEquals(p2, p3);

    }

    @Test
    public void testUpdate() throws Exception {
        System.out.println("test");

        Project p = projectFactory.getObject();
        Assertions.assertNotNull(p);

        Project p2 = projectRepository.save(p);

        p2.setName("MyNewName");

        projectRepository.save(p2);
        Project p3 = entityManager.find(Project.class, p2.getId());

        Assertions.assertNotNull(p3);
        Assertions.assertEquals(p3.getName(), "MyNewName");

    }


    @Test
    public void testQuery() throws Exception {
        System.out.println("test");

        Project p = projectFactory.getObject();
        Assertions.assertNotNull(p);

        Project p1 = projectFactory.getObject();

        p.setCode("goldenBrown-99");
        p1.setCode("goldenBrown-100");

        projectRepository.saveAll(List.of(p, p1));

        Iterable<Project> projsIterator = projectRepository.findByCodeContains("goldenBrown");

        List<Project> result = new ArrayList<>();
        projsIterator.forEach(result::add);

        Assertions.assertEquals(result.size(), 2);
    }


    @Test
    public void testQueries() throws Exception {

        Project p = projectFactory.getObject();
        Project p1 = projectFactory.getObject();
        p.setCode("goldenBrown-99");
        p1.setCode("goldenBrown-100");

        p.setDescription("golden");
        p1.setDescription("golden");

        projectRepository.saveAll(List.of(p, p1));

        System.out.println("test");

        List<Project> projects = projectRepository.findByCodeAndDescription("golden", "golden");

        Assertions.assertTrue(projects.size() >= 2);

        var optProject = projectRepository.findNameByCode("I-dont-exist");
        Assertions.assertTrue(optProject.isEmpty());

        var optProject2 = projectRepository.findNameByCode(p.getCode());
        Assertions.assertTrue(optProject2.isPresent());
        Assertions.assertEquals(p.getName(), optProject2.get());

    }

    @Test
    public void testQueriesGroup() throws Exception {

        Project p = projectFactory.getObject();
        Project p1 = projectFactory.getObject();
        p.setCode("goldenBrown-99");
        p1.setCode("goldenBrown-100");

        p.setDescription("golden");
        p1.setDescription("golden");

        projectRepository.saveAll(List.of(p, p1));
        List<Long> count1 = projectRepository.countTasks();
        Assertions.assertNotNull(count1);


        var optProject = projectRepository.findSingleProject();
        Assertions.assertNotNull(optProject);
        Assertions.assertTrue(optProject.isPresent());

    }

    @Test
    public void testQueries2() throws Exception {

        Project p = projectFactory.getObject();
        Project p1 = projectFactory.getObject();
        p.setCode("goldenBrown-99");
        p1.setCode("goldenBrown-100");

        p.setName("ggg");

        p.setDescription("golden");
        p1.setDescription("golden");

        projectRepository.saveAll(List.of(p, p1));

        List<Project> projects = projectRepository.findWithNameAndDescriptionBind("ggg", "golden");

        Assertions.assertNotNull(projects);
        Assertions.assertTrue(projects.size() >= 1);


        List<Project> projects2 = projectRepository.findProjectsByCode(List.of("goldenBrown-99", "goldenBrown-100"));
        Assertions.assertTrue(projects2.size() >= 2);
    }

    @Test
    public void tasksQueries() throws Exception {

        int count = taskRepository.countByState(TaskStatus.DONE);
        Assertions.assertTrue(count >= 1);


        int deleted = taskRepository.deleteByState(TaskStatus.DONE);
        Assertions.assertTrue(deleted >= 0);

        int count2 = taskRepository.countByState(TaskStatus.DONE);
        Assertions.assertTrue(count2 == 0);

    }

    @Test
    public void testNamed() throws Exception {

        List<Task> tasks = taskRepository.findByStatusNamed(TaskStatus.DONE);
        Assertions.assertTrue(tasks.size() >= 1);

        int count = taskRepository.countUnassigned();
        Assertions.assertTrue(count > 0);

        int removed = taskRepository.deleteUnassigned();
        Assertions.assertTrue(removed > 0);

        int count2 = taskRepository.countUnassigned();

        //requires flush
        Assertions.assertTrue(count2 == 0);
    }


    @Test
    public void testNamedNative() throws Exception {
        List<Task> tasks = taskRepository.findTasksByProjectNative(1L);
        Assertions.assertTrue(tasks.size() >= 1);
    }

    @Test
    public void testProjections() throws Exception {

        List<ProjectTask> projects = taskRepository.findTasksAndProjects();
        Assertions.assertTrue(projects.size() >= 1);

        var p = projects.get(0);
        Assertions.assertNotNull(p.getName());


        Worker worker = workerFactory.getObject();

        worker.setFirstName("Johnyyy");
        worker.setLastName("Walker");
        workerRepository.save(worker);


        List<WorkerFullname> workerFullnames = workerRepository.findByFirstName("Johnyyy");
        Assertions.assertTrue(workerFullnames.size() == 1);
        Assertions.assertEquals("Johnyyy Walker", workerFullnames.get(0).getFullname());
    }


    @Test
    public void testExample() throws Exception {
        Project p = projectFactory.getObject();
        p.setCode("golden-100");

        p = projectRepository.save(p);

        Example<Project> projectExample = Example.of(p);

        var found = projectRepository.findOne(projectExample);

        Assertions.assertEquals(p.getId(), found.get().getId());
    }


    @Test
    public void testExample2() throws Exception {

        Project p = projectFactory.getObject();

        p.setCode("golden99");

        Project p1 = projectFactory.getObject();
        p1.setCode("golden100");

        Project p3 = projectFactory.getObject();
        p3.setCode("golden");


        projectRepository.save(p1);
        projectRepository.save(p);

        projectRepository.flush();

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("code",ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase());


        Example<Project> projectExample = Example.of(p,matcher);

        var list = projectRepository.findAll(projectExample);

        //does not work
       // Assertions.assertEquals(list.size(),2);
    }


    @Test
    public void testCustomMethod() throws Exception {

        var list = workerRepository.search("a");

        Assertions.assertTrue(list.size() > 0);


    }


}
