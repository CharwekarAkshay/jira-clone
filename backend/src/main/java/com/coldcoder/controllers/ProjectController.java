package com.coldcoder.controllers;

import com.coldcoder.exceptions.ProjectException;
import com.coldcoder.models.dto.AlreadyExist;
import com.coldcoder.models.dto.ProjectRequestDTO;
import com.coldcoder.models.dto.ProjectResponseDTO;
import com.coldcoder.services.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projects")
@AllArgsConstructor
public class ProjectController {

    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> getProjects() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(projectService.getAllProjects());
    }

    @DeleteMapping("/{projectKey}")
    public ResponseEntity<ProjectResponseDTO> deleteProject(@Valid @PathVariable String projectKey) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(projectService.deleteProjectByProjectKey(projectKey));
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> createProject(@Valid @RequestBody ProjectRequestDTO projectRequestDTO) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(projectService.createProject(projectRequestDTO));
        } catch (ProjectException exception) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    exception.getMessage(),
                    exception
            );
        }
    }

    @GetMapping(value = "/already_exist/{projectKey}")
    private ResponseEntity<AlreadyExist> checkIfProjectKeyAlreadyExist(@Valid @PathVariable String projectKey) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(projectService.checkIfProjectKeyAlreadyExist(projectKey));
    }
}
