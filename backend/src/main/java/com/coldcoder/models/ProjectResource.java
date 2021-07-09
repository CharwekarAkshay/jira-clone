package com.coldcoder.models;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@AllArgsConstructor
@Data
@ConfigurationProperties("project_resources")
public class ProjectResource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long resourceId;

    @NotBlank(message = "Resource file name cannot be blank")
    private String fileName;

    @NotBlank(message = "Document type cannot be blank")
    private String documentType;

    @NotBlank(message = "Document format cannot be blank")
    private String documentFormat;

    @NotBlank(message = "Upload dir cannot be blank")
    private String uploadDir;

    @ManyToOne
    private Project project;

}
