package com.coldcoder.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import com.coldcoder.models.dto.AlreadyExist;
import com.coldcoder.models.dto.ProjectRequestDTO;
import com.coldcoder.models.dto.ProjectResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectControllerTest {
    @Autowired
    private ProjectController projectController;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String url = "http://localhost:";
    private String endPoint = "/api/projects";

    @Test
    public void checkContext() {
        assertThat(projectController).isNotNull();
    }

    @Test
    public void checkGetProjectApi() {
        String[] response = restTemplate.getForObject(url + port + endPoint, String[].class);
        assertThat(response).isNotNull();
        assertThat(response).isNotEmpty();
    }

    @Test
    public void checkCreateProjectApi() {
        String projectName = "First Project";
        String projectKey = "FPR";

        ProjectRequestDTO projectRequestDTO = new ProjectRequestDTO();
        projectRequestDTO.setProjectName(projectName);
        projectRequestDTO.setProjectKey(projectKey);

        ProjectResponseDTO projectResponseDTO = restTemplate.postForObject(url + port + endPoint, projectRequestDTO, ProjectResponseDTO.class);

        assertThat(projectResponseDTO.getProjectId()).isNotNull();
        assertThat(projectResponseDTO.getCreatedAt()).isNotNull();
        assertThat(projectResponseDTO.getProjectName()).isEqualTo(projectName);
        assertThat(projectResponseDTO.getProjectKey()).isEqualTo(projectKey);
    }
    
    @Test
    public void checkUniquenessOfProjectKey() {
        String projectKey = "FPR";
        String URL = url + port + "/already_exist";
        ResponseEntity<AlreadyExist> alreadyExistResponseEntity= restTemplate.getForEntity(URL, AlreadyExist.class, projectKey);
        AlreadyExist alreadyExist = alreadyExistResponseEntity.getBody();
        assertThat(alreadyExist.getExist()).isTrue();
    }
}
