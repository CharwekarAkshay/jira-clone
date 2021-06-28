package com.colcoder.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;

@Data
//@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Project {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectId;

    @NotBlank(message = "Project Name cannot be null")
    private String projectName;

    @NotBlank(message = "Project key cannot be null")
//    @Column(length = 5)
    @Size(min = 3, max = 5)
    private String projectKey;

    @NotBlank(message = "Project description cannot be null")
//    @Lob
    private String projectDescription;

    @NotBlank(message = "Project creation date needed")
    private Instant createdAt;

}

