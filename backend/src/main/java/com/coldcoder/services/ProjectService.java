package com.coldcoder.services;

import com.coldcoder.mappers.ProjectMapper;
import com.coldcoder.models.Project;
import com.coldcoder.models.dto.AlreadyExist;
import com.coldcoder.models.dto.ProjectRequestDTO;
import com.coldcoder.models.dto.ProjectResponseDTO;
import com.coldcoder.repositories.ProjectRepositories;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProjectService {

    private ProjectRepositories projectRepositories;
    private ProjectMapper projectMapper;

    @Transactional
    public ProjectResponseDTO createProject(ProjectRequestDTO projectRequestDTO) {
        log.info("Creat new project request with project Name: " + projectRequestDTO.getProjectName());
        Project newProject = projectMapper.mapProjectRequestDTOTOProject(projectRequestDTO);
        newProject.setCreatedAt(Instant.now());
        Project project = projectRepositories.save(newProject);
        log.info("New project created with project Name: " + projectRequestDTO.getProjectName());
        ProjectResponseDTO response = projectMapper.mapProjectToProjectResponseDTO(project);
        return response;
    }

    @Transactional
    public AlreadyExist checkIfProjectKeyAlreadyExist(String projectKey) {
        log.info("Checking if project key already exist. Project Key: " + projectKey);
        Boolean existOrNot = projectRepositories.existsByProjectKey(projectKey);
        log.info("Project key " + projectKey + "exist " + existOrNot);
        return new AlreadyExist(existOrNot);
    }

    @Transactional
    public List<ProjectResponseDTO> getAllProjects() {
        log.info("Fetching all the projects");
        List<Project> projects = projectRepositories.findAll();
        log.info("Fetched all projects");
        log.info("Mapping Project to ProjectResponseDTO");
        List<ProjectResponseDTO> projectResponseDTOS = projects
                                                        .stream()
                                                        .map(projectMapper::mapProjectToProjectResponseDTO)
                                                        .collect(Collectors.toList());
        return projectResponseDTOS;
    }

}
