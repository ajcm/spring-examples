package com.baeldung.lsd.controller;

import com.baeldung.lsd.model.Project;
import com.baeldung.lsd.repository.ProjectRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app")
public class ApplicationController {

    private final ProjectRepository projectRepository;

    public ApplicationController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping(path = "projectByCode")
    ResponseEntity<Project> getProjectByCode(@RequestParam String code) {
        var optProject = projectRepository.findByCodeEquals(code);

        if (optProject.isPresent()) {
            return ResponseEntity.ok(optProject.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
