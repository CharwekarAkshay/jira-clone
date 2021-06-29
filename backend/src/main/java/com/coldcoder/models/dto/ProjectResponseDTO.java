package com.coldcoder.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectResponseDTO {
    private Long projectId;
    private String projectName;
    private String projectKey;
    private Instant createdAt;
}
