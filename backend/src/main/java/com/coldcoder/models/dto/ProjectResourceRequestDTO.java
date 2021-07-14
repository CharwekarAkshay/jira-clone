package com.coldcoder.models.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class ProjectResourceRequestDTO {
    @NotNull(message = "Project Resource can't be null")
    MultipartFile file;

    @NotBlank(message = "Project Key can't be null")
    String projectKey;
}
