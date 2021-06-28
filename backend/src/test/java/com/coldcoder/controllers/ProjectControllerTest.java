package com.coldcoder.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProjectControllerTest {
	@Autowired
	ProjectController projectController;
	
	@Test
	public void checkContext() {
		assertThat(projectController).isNotNull();
	}
}
