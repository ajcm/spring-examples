package com.baeldung.lsd.services;

import com.baeldung.lsd.model.Project;
import com.baeldung.lsd.model.TaskStatus;
import com.baeldung.lsd.repository.ProjectRepository;
import com.baeldung.lsd.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    private TaskRepository taskRepository;

    public ProjectService(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }


    @Transactional
    public void close(Project project) {

        project.getTasks().forEach(t -> t.setStatus(TaskStatus.DONE));

        taskRepository.saveAll(project.getTasks());

    }
}
