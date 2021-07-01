package com.coldcoder.services;

import com.coldcoder.models.Project;
import com.coldcoder.models.dto.ProjectRequestDTO;
import com.coldcoder.models.dto.ProjectResponseDTO;
import com.coldcoder.repositories.ProjectRepositories;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@AllArgsConstructor
public class ProjectService {

    private ProjectRepositories projectRepositories;

    @Transactional
    public ProjectResponseDTO createProject(ProjectRequestDTO projectRequestDTO) {
        Project newProject = new Project();
        newProject.setProjectName(projectRequestDTO.getProjectName());
        newProject.setProjectKey(projectRequestDTO.getProjectKey());
        newProject.setCreatedAt(Instant.now());
        Project project = projectRepositories.save(newProject);

        ProjectResponseDTO response = new ProjectResponseDTO();
        response.setProjectId(project.getProjectId());
        response.setProjectKey(project.getProjectKey());
        response.setProjectName(project.getProjectName());
        response.setCreatedAt(project.getCreatedAt());
        return response;
    }

}
