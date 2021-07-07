package com.coldcoder.services;

import com.coldcoder.exceptions.ProjectException;
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
import java.util.Formatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProjectService {

    private ProjectRepositories projectRepositories;
    private ProjectMapper projectMapper;

    @Transactional
    public ProjectResponseDTO createProject(ProjectRequestDTO projectRequestDTO) throws ProjectException {
        log.info("Checking if projectKey Already exist");
        Boolean exist = projectRepositories.existsByProjectKey(projectRequestDTO.getProjectKey());
        if (exist) {
            log.info("Project can't be created with projectKey: " + projectRequestDTO.getProjectKey());
            throw new ProjectException("Project key already exist");
        }
        log.info("Creat new project request with project Name: " + projectRequestDTO.getProjectName());
        Project newProject = projectMapper.mapProjectRequestDTOToProject(projectRequestDTO);
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

    @Transactional
    public ProjectResponseDTO deleteProjectByProjectKey(String projectKey) {
        log.info("Fetching Project to be deleted");
        Project project = projectRepositories.getByProjectKey(projectKey);
        log.info("Trying to delete project by project key: " + projectKey);
        projectRepositories.deleteProjectByProjectKey(projectKey);
        log.info("Project deleted by project key: " + projectKey);
        return projectMapper.mapProjectToProjectResponseDTO(project);
    }
}
