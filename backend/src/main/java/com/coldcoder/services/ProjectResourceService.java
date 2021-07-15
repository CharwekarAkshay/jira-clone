package com.coldcoder.services;

import com.coldcoder.exceptions.ProjectResourceException;
import com.coldcoder.models.Project;
import com.coldcoder.models.ProjectResource;
import com.coldcoder.models.dto.ProjectResourceRequestDTO;
import com.coldcoder.repositories.ProjectRepository;
import com.coldcoder.repositories.ProjectResourceRepository;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.util.stream.Stream;

@Service
@Slf4j
public class ProjectResourceService {

    @Value("${application.project.files.location}")
    private String location;

    ProjectResourceRepository projectResourceRepository;
    ProjectRepository projectRepository;

    @Autowired
    public ProjectResourceService(ProjectResourceRepository projectResourceRepository,
                                  ProjectRepository projectRepository) {
        if (location == null) {
            location = "project_assets";
        }
        this.projectResourceRepository = projectResourceRepository;
        this.projectRepository = projectRepository;
    }

    public void store(ProjectResourceRequestDTO projectResourceRequestDTO) {
        MultipartFile file = projectResourceRequestDTO.getFile();
        log.info("Request received to store project resource");
        try {
            if (file.isEmpty()) {
                log.error("Cannot store project with empty file");
                throw new ProjectResourceException("Failed to store empty file");
            }

            log.info("Fetching project with project key: " + projectResourceRequestDTO.getProjectKey());
            // * Checking if the project exist or not
            Project project = projectRepository.getByProjectKey(projectResourceRequestDTO.getProjectKey());

            if (project == null) {
                log.error("Invalid project key can't retrieve project with project key: "
                        + projectResourceRequestDTO.getProjectKey());
                throw new ProjectResourceException(
                        "Can't save project project not present with key:" + projectResourceRequestDTO.getProjectKey());
            }

            // Create root location for saving file
            log.info("Creating root path for file");
            Path rootLocation = Paths.get(this.location).resolve(project.getProjectName());
            log.info("File root location created");

            log.info("Creating Root directory for file");
            try {
                Files.createDirectories(rootLocation);
            } catch (IOException exception) {
                log.error("Can't create project directory");
                throw new ProjectResourceException("Failed to create directory for project");
            }

            String originalFileName = file.getOriginalFilename();
            Path destinationFile = rootLocation.resolve(Paths.get(originalFileName)).normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(rootLocation.toAbsolutePath())) {
                throw new ProjectResourceException("Cannot store file outside directory");
            }

            log.info("Copying file to project destination");
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            log.info("Successfully copied file to a project destination");

            // Store file in database
            log.info("Saving file details in database");
            ProjectResource projectResource = new ProjectResource();
            projectResource.setFileName(originalFileName);
            projectResource.setDocumentType(file.getContentType());
            projectResource.setUploadDir(destinationFile.toString());
            projectResource.setProject(project);
            projectResourceRepository.save(projectResource);
            log.info("Saved all the details in database");

        } catch (IOException exception) {
            throw new ProjectResourceException("Failed to store file", exception);
        }
    }

//    public Path load(String fileName) {
//        return Paths.get(location).resolve(fileName);
//    }
//
//    public Resource loadAsResource(String fileName) {
//        try {
//            Path file = load(fileName);
//            Resource resource = new UrlResource(file.toUri());
//            if (resource.exists() || resource.isReadable()) {
//                return resource;
//            } else {
//                throw new ProjectResourceException("Could not read file: " + fileName);
//            }
//        } catch (MalformedURLException exception) {
//            throw new ProjectResourceException("Could not read file: " + fileName, exception);
//        }
//    }

//	public Stream<Path> loadAll() {
//        try {
//            return Files.walk(this.rootLocation, 1)
//                    .filter(path -> !path.equals(this.rootLocation))
//                    .map(this.rootLocation::relativize);
//        } catch (IOException exception) {
//            throw new ProjectResourceException("Failed to read stored files", exception);
//        }
//	}
}
