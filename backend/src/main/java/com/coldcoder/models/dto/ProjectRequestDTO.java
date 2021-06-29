package com.coldcoder.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectRequestDTO {
    @NotBlank(message = "Project Name cannot be empty")
    private String projectName;
    @NotBlank(message = "Project Key cannot be empty")
    private String projectKey;
}
