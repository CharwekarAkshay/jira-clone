package com.coldcoder.services;

import com.coldcoder.exceptions.ProjectResourceException;
import com.coldcoder.models.Project;
import com.coldcoder.models.ProjectResource;
import com.coldcoder.models.dto.ProjectResourceRequestDTO;
import com.coldcoder.repositories.ProjectRepository;
import com.coldcoder.repositories.ProjectResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.util.stream.Stream;

@Service
public class ProjectResourceService {
    private Path rootLocation;

    @Value("${application.project.files.location}")
    private String location;

    ProjectResourceRepository projectResourceRepository;
    ProjectRepository projectRepository;

    @Autowired
    public ProjectResourceService(ProjectResourceRepository projectResourceRepository, ProjectRepository projectRepository) {
        if (location == null) {
            location = "project_assets";
        }
        this.rootLocation = Paths.get(location);
        this.projectResourceRepository = projectResourceRepository;
        this.projectRepository = projectRepository;
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException exception) {
            throw new ProjectResourceException("Could not initialize storage", exception);
        }
    }

    public void store(ProjectResourceRequestDTO projectResourceRequestDTO) {
        MultipartFile file = projectResourceRequestDTO.getFile();
        try {
            if (file.isEmpty()) {
                throw new ProjectResourceException("Failed to store empty file");
            }
            // * Checking if the project exist or not
            Project project = projectRepository.getByProjectKey(projectResourceRequestDTO.getProjectKey());

            if (project == null)
                throw new ProjectResourceException("Can't save project project not present with key:"
                        + projectResourceRequestDTO.getProjectKey());


            this.rootLocation = this.rootLocation.resolve(Paths.get(project.getProjectName())).normalize().toAbsolutePath();

            String originalFileName = file.getOriginalFilename();
            Path destinationFile = this.rootLocation.resolve(
                    Paths.get(originalFileName))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new ProjectResourceException("Cannot store file outside directory");
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }


            // Store file in database
            ProjectResource projectResource = new ProjectResource();
            projectResource.setFileName(originalFileName);
            projectResource.setDocumentType(file.getContentType());
            projectResource.setUploadDir(destinationFile.toString());
            projectResource.setProject(project);
            projectResourceRepository.save(projectResource);

        } catch (IOException exception) {
            throw new ProjectResourceException("Failed to store file", exception);
        }
    }

    public Path load(String fileName) {
        return rootLocation.resolve(fileName);
    }

    public Resource loadAsResource(String fileName) {
        try {
            Path file = load(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new ProjectResourceException("Could not read file: " + fileName);
            }
        } catch (MalformedURLException exception) {
            throw new ProjectResourceException("Could not read file: " + fileName, exception);
        }
    }

    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException exception) {
            throw new ProjectResourceException("Failed to read stored files", exception);
        }
    }
}
