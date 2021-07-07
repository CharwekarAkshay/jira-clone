package com.coldcoder.mappers;

import com.coldcoder.models.Project;
import com.coldcoder.models.dto.ProjectRequestDTO;
import com.coldcoder.models.dto.ProjectResponseDTO;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
@AllArgsConstructor
public abstract class ProjectMapper {
    @Mapping(target = "projectId", source = "project.projectId")
    @Mapping(target = "projectName", source = "project.projectName")
    @Mapping(target = "projectKey", source = "project.projectKey")
    @Mapping(target = "createdAt", source = "project.createdAt")
    public abstract ProjectResponseDTO mapProjectToProjectResponseDTO(Project project);

    @Mapping(target = "projectName", source = "projectRequestDTO.projectName")
    @Mapping(target = "projectKey", source = "projectRequestDTO.projectKey")
    public abstract Project mapProjectRequestDTOToProject(ProjectRequestDTO projectRequestDTO);
}
