package com.colcoder.backend.controllers;

import lombok.AllArgsConstructor;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/project")
@AllArgsConstructor
public class ProjectController {
    @GetMapping()
    public ResponseEntity<String[]> getProject() {
        String[] projects = {"ProjectABC", "ProjectPQR"};
        return ResponseEntity.status(OK).body(projects);
    }
}
