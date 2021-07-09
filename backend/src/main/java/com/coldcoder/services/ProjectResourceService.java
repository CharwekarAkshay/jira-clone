package com.coldcoder.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.coldcoder.exceptions.ProjectResourceException;
import com.coldcoder.models.ProjectResource;
import com.coldcoder.repositories.ProjectRepositories;
import com.coldcoder.repositories.ProjectResourceRepositories;

import org.hibernate.validator.internal.metadata.location.TypeArgumentConstraintLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProjectResourceService {

    @Value("${application.project.files.location}")
    private String projectResourceLocation;

    private final Path fileStoragePath;

    private ProjectResourceRepositories projectResourceRepositories;

    @Autowired
    public ProjectResourceService(ProjectResourceRepositories projectResourceRepositories,
            ProjectResource projectResource) {
        this.projectResourceRepositories = projectResourceRepositories;
        this.fileStoragePath = Paths.get(projectResourceLocation).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStoragePath);
        } catch (IOException exception) {
            throw new ProjectResourceException("Cannot create directory for storing images.", exception);
        }
    }

    public String storeFile(MultipartFile file, String documentType, Long projectId, String projectKey) {
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileName = "";

        try {
            if (originalFileName.contains("..")) {
                throw new ProjectResourceException("Filename contain invalid path sequence " + originalFileName);
            }
            String fileExtension = "";
            try {
                fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            } catch (Exception exception) {
                fileExtension = "";
            }

            fileName = projectId + "_" + projectKey + "_" + documentType + fileExtension;
            Path targetLocation = this.fileStoragePath.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            

        } catch (IOException exception) {
            throw new ProjectResourceException("Could not store file " + fileName + ". Please try again!", exception);
        }

        return "";
    }
}
