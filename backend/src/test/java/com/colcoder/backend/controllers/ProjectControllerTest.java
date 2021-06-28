package com.colcoder.backend.controllers;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProjectControllerTest {

    @Autowired
    ProjectController projectController;

    @Test
    public void contextLoad() throws Exception {
        Assertions.assertThat(projectController).isNotNull();
    }


}
