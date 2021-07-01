package com.coldcoder.controllers;

import com.coldcoder.models.dto.ProjectRequestDTO;
import com.coldcoder.models.dto.ProjectResponseDTO;
import com.coldcoder.services.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/projects")
@AllArgsConstructor
public class ProjectController {

    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<String[]> getProjects() {
        String[] projects = {"Porject1", "Project2"};
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(projects);
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> createProject(@Valid @RequestBody ProjectRequestDTO projectRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(projectService.createProject(projectRequestDTO));
    }
}