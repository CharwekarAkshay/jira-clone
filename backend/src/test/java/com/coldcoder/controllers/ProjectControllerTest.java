package com.coldcoder.controllers;

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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectControllerTest {
    @Autowired
    private ProjectController projectController;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    final private String url = "http://localhost:";
    final private String endPoint = "/api/projects";

    String projectName = "Test Project";
    String projectKey = "TFR";

    @Test
    public void checkContext() {
        assertThat(projectController).isNotNull();
    }

    @Test
    public void checkGetProjectApi() {
        createProject(projectName, projectKey);
        ProjectResponseDTO[] response = restTemplate.getForObject(url + port + endPoint, ProjectResponseDTO[].class);
        assertThat(response).isNotNull();
        assertThat(response).isNotEmpty();
        //cleaning env

        deleteProject(projectKey);
    }

    @Test
    public void checkUniquenessOfProjectKey() {
        String URL = url + port + endPoint + "/already_exist/" + projectKey;
        HttpEntity<String> request = new HttpEntity<>(projectKey);
        // Create environment
        createProject(projectName, projectKey);

        AlreadyExist exist = restTemplate
                .exchange(URL, HttpMethod.GET, request, AlreadyExist.class)
                .getBody();
        assertThat(exist.getExist()).isTrue();
    }

    @Test
    public void checkCreateProjectApi() {
        ProjectResponseDTO projectResponseDTO = createProject(projectName, projectKey);

        assertThat(projectResponseDTO.getProjectId()).isNotNull();
        assertThat(projectResponseDTO.getCreatedAt()).isNotNull();
        assertThat(projectResponseDTO.getProjectName()).isEqualTo(projectName);
        assertThat(projectResponseDTO.getProjectKey()).isEqualTo(projectKey);

        // Clean Testing env
        deleteProject(projectKey);
    }

    @Test
    public void deleteExistingProjectByProjectKey() {
        // Create env
        createProject(projectName, projectKey);
        // Start checking
        ProjectResponseDTO projectResponseDTO = deleteProject(projectKey);
        assertThat(projectResponseDTO).isNotNull();
        assertThat(projectResponseDTO.getProjectKey()).isEqualTo(projectKey);
        assertThat(projectResponseDTO.getProjectName()).isEqualTo(projectName);
    }

    // Helper methods
    public ProjectResponseDTO createProject(String projectName, String projectKey) {
        ProjectRequestDTO projectRequestDTO = new ProjectRequestDTO();
        projectRequestDTO.setProjectName(projectName);
        projectRequestDTO.setProjectKey(projectKey);
        ProjectResponseDTO projectResponseDTO = restTemplate.postForObject(url + port + endPoint, projectRequestDTO, ProjectResponseDTO.class);
        return projectResponseDTO;
    }

    public ProjectResponseDTO deleteProject(String projectKey) {
        HttpEntity<String> request = new HttpEntity<>(projectKey);
        ProjectResponseDTO projectResponseDTO = restTemplate
                .exchange(url + port + endPoint + "/" + projectKey, HttpMethod.DELETE, request, ProjectResponseDTO.class)
                .getBody();
        return projectResponseDTO;
    }
}
