package com.coldcoder.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectResource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long resourceId;

    @NotBlank(message = "Resource file name cannot be blank")
    private String fileName;

    @NotBlank(message = "Document type cannot be blank")
    private String documentType;

    @NotBlank(message = "Upload dir cannot be blank")
    private String uploadDir;

    @ManyToOne(optional = false)
    @NotNull(message = "Resource should be associate with project")
    private Project project;

}
