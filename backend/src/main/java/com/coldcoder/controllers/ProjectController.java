package com.coldcoder.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
public class ProjectController {

	@GetMapping
	public ResponseEntity<String []> getProjects() {
		String[] projects = { "Porject1", "Project2" };
		return ResponseEntity.status(HttpStatus.OK).body(projects);
	}
}
