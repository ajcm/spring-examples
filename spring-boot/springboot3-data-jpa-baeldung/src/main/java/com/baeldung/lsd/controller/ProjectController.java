package com.baeldung.lsd.controller;


import com.baeldung.lsd.exception.NotFoundException;
import com.baeldung.lsd.model.Project;
import com.baeldung.lsd.repository.ProjectRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("project")
public class ProjectController {

    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping(path = "hello")
    public String sayHello() {
        return "hello";
    }

    @GetMapping(path = "/code/{code}")
    public Project getByCode(@PathVariable String code) {
        var optProject = projectRepository.findByCodeEquals(code);

        if (optProject.isPresent()) {
            return optProject.get();
        }

        throw new NotFoundException();
    }

    @GetMapping(path = "/codeContains/{expression}")
    public Iterable<Project> getByCodeName(@PathVariable String expression) {
        return projectRepository.findByCodeContains(expression);
    }

    @DeleteMapping(path = "/codeContains/{expression}")
    public Long deleteByCodeName(@PathVariable String expression) {
        return projectRepository.deleteByCodeContains(expression);
    }

    @GetMapping("all")
    public List<Project> getAll() {
        return projectRepository.findAll();
    }


}
