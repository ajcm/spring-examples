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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Random;

@SpringBootApplication
public class SpringApplication implements ApplicationRunner {

    private static final Random RANDOM = new Random();
    private static final Logger LOG = LoggerFactory.getLogger(SpringApplication.class);
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

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            createData();
        };
    }


    @Bean
    public CommandLineRunner commandLineRunner2(ApplicationContext ctx) {
        return args -> {
            testSpecification2();
        };
    }

    void createData() throws Exception {


        for (int i = 0; i < 5; i++) {
            Project p = projectFactory.getObject();
            projectRepository.save(p);
        }

        for (int i = 0; i < 5; i++) {
            Worker w = workerFactory.getObject();
            workerRepository.save(w);
        }

        var projects = projectRepository.findAll();

        for (int i = 0; i < 25; i++) {
            Task t = taskFactory.getObject();

            var p = projects.get(RANDOM.nextInt(projects.size()));

            t.setProject(p);
            taskRepository.save(t);
        }
    }


    public void testPagination() {

        Pageable pageable = PageRequest.of(2, 5);

        Page<Task> page = taskRepository.findAll(pageable);

        System.out.println("getTotalElements -> " + page.getTotalElements());
        System.out.println("getTotalPages -> " + page.getTotalPages());

        var tasks = page.getContent();
        System.out.println("Tasks:");
        tasks.forEach(System.out::println);

        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        var tasks2 = taskRepository.findAll(sort);

        System.out.println("Tasks Sorted:");
        tasks2.forEach(System.out::println);

    }

    public void testPagination2() {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        Pageable pageWithSort = PageRequest.of(1, 5, sort);

        Page<Task> page = taskRepository.findAll(pageWithSort);

        System.out.println("Tasks Sorted:");
        var tasks = page.getContent();
        tasks.forEach(System.out::println);
    }

    public void testSort() {
        var tasks = taskRepository.findFirst5ByOrderByDueDateDesc();
        System.out.println("Tasks Sorted by Due date:");
        tasks.forEach(System.out::println);


        var tasks2 = taskRepository.findFirst5ByOrderByStatusDesc();
        System.out.println("Tasks Sorted by Status:");
        tasks2.forEach(System.out::println);


    }

    public void testSort2() {
        var tasks = taskRepository.findAllByOrderByDueDateDescStatusDesc();
        System.out.println("Tasks Sorted by Due date:");
        tasks.forEach(System.out::println);
    }

    public void testSort3() {

        Sort sort = Sort.by(Sort.Direction.ASC, "status").and(Sort.by(Sort.Direction.DESC, "dueDate"));

        var tasks = taskRepository.findByNameContaining("a", sort);
        System.out.println("Tasks:");
        tasks.forEach(System.out::println);
    }

    public void testTypedSort() {

        Sort.TypedSort<Task> typeTaskSort = Sort.TypedSort.sort(Task.class);
        Sort statusAsc = typeTaskSort.by(Task::getStatus).ascending();
        Sort dueDateDesc = typeTaskSort.by(Task::getDueDate).descending();

        Sort joinSort = statusAsc.and(dueDateDesc);

        var tasks = taskRepository.findByNameContaining("a", joinSort);
        System.out.println("Tasks:");
        tasks.forEach(System.out::println);
    }

    public void testQuerySort() {
        var projects = projectRepository.allProjects(Sort.unsorted());
        System.out.println("Projects:");
        projects.forEach(System.out::println);

        Sort.TypedSort<Project> projectTypedSort = Sort.TypedSort.sort(Project.class);
        Sort sort = projectTypedSort.by(Project::getCode).ascending();

        var projects2 = projectRepository.allProjects(sort);
        System.out.println("Projects:");
        projects2.forEach(System.out::println);

        Pageable pageRequest = PageRequest.of(0, 1, sort);
        var projects3 = projectRepository.allProjectsByPage(pageRequest);
        System.out.println("Projects:");
        projects3.forEach(System.out::println);
    }


    public void testQuerySlice() {

        Pageable pageRequest = PageRequest.of(0, 5, Sort.unsorted());

        do {

            Slice<Task> slice = taskRepository.getAll(pageRequest);

            if (slice.hasContent()) {

                System.out.println("got:  " + slice.getNumberOfElements());
                System.out.println("is last:  " + slice.isLast());

                if (slice.hasNext()) {
                    pageRequest = slice.nextPageable();
                    continue;
                }

                break;
            }

        } while (true);


    }


    public void testQuerySlice2() {

        Pageable pageRequest = PageRequest.of(0, 5, Sort.unsorted());
        Slice<Task> slice = taskRepository.findByNameLike("%a%", pageRequest);
        System.out.println("got:  " + slice.getNumberOfElements());
    }

    public void testSpecification() {
        Pageable pageRequest = PageRequest.of(0, 5, Sort.unsorted());

        var list = taskRepository.findAll(TaskRepository.isTaskInProgress, pageRequest);
        System.out.println("got:  " + list.getContent().size());

        list.forEach(System.out::println);
    }


    public void testSpecification2() {
        LocalDate fromDate = LocalDate.of(2023, 11, 21);
        LocalDate toDate = LocalDate.of(2023, 11, 23);

        Specification<Task> taskInBetweenDates = TaskRepository.isDueDateBetween(fromDate, toDate);

        var spec = taskInBetweenDates.and(TaskRepository.isTaskInProgress);

        Pageable pageRequest = PageRequest.of(0, 5, Sort.unsorted());

        var list = taskRepository.findAll(spec, pageRequest);
        System.out.println("got:  " + list.getContent().size());

        list.forEach(System.out::println);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOG.info("Starting Spring Boot application...");
    }
}
