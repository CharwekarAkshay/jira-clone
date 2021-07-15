package com.coldcoder.controllers;

import com.coldcoder.exceptions.ProjectResourceException;
import com.coldcoder.models.dto.ProjectResourceRequestDTO;
import com.coldcoder.services.ProjectResourceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/files")
public class ProjectResourceController {
    private final ProjectResourceService projectResourceService;

    @PostMapping
    public ResponseEntity<String> fileUpload(@Valid ProjectResourceRequestDTO projectResourceRequestDTO) {
        projectResourceService.store(projectResourceRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body("File upload success");
    }

    @ExceptionHandler(ProjectResourceException.class)
    public ResponseEntity<?> handleProjectResourceException(ProjectResourceException exception) {
        return ResponseEntity.notFound().build();
    }


}