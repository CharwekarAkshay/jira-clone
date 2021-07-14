package com.coldcoder.services;

import com.coldcoder.exceptions.ProjectException;
import com.coldcoder.mappers.ProjectMapper;
import com.coldcoder.models.Project;
import com.coldcoder.models.dto.AlreadyExist;
import com.coldcoder.models.dto.ProjectRequestDTO;
import com.coldcoder.models.dto.ProjectResponseDTO;
import com.coldcoder.repositories.ProjectRepository;
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

    private ProjectRepository projectRepository;
    private ProjectMapper projectMapper;

    @Transactional
    public ProjectResponseDTO createProject(ProjectRequestDTO projectRequestDTO) throws ProjectException {
        log.info("Checking if projectKey Already exist");
        Boolean exist = projectRepository.existsByProjectKey(projectRequestDTO.getProjectKey());
        if (exist) {
            log.info("Project can't be created with projectKey: " + projectRequestDTO.getProjectKey());
            throw new ProjectException("Project key already exist");
        }
        log.info("Creat new project request with project Name: " + projectRequestDTO.getProjectName());
        Project newProject = projectMapper.mapProjectRequestDTOToProject(projectRequestDTO);
        newProject.setCreatedAt(Instant.now());
        Project project = projectRepository.save(newProject);
        log.info("New project created with project Name: " + projectRequestDTO.getProjectName());
        ProjectResponseDTO response = projectMapper.mapProjectToProjectResponseDTO(project);
        return response;
    }

    @Transactional
    public AlreadyExist checkIfProjectKeyAlreadyExist(String projectKey) {
        log.info("Checking if project key already exist. Project Key: " + projectKey);
        Boolean existOrNot = projectRepository.existsByProjectKey(projectKey);
        log.info("Project key " + projectKey + "exist " + existOrNot);
        return new AlreadyExist(existOrNot);
    }

    @Transactional
    public List<ProjectResponseDTO> getAllProjects() {
        log.info("Fetching all the projects");
        List<Project> projects = projectRepository.findAll();
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
        Project project = projectRepository.getByProjectKey(projectKey);
        log.info("Trying to delete project by project key: " + projectKey);
        projectRepository.deleteProjectByProjectKey(projectKey);
        log.info("Project deleted by project key: " + projectKey);
        return projectMapper.mapProjectToProjectResponseDTO(project);
    }
}
