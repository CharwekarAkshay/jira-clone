package com.coldcoder.models;

import java.time.Instant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Project {
	
	private Long porjectId;
	
	@NotBlank(message = "Project name cannot be empty")
	private String porjectName;
	
	@NotBlank(message = "Project key cannot be empty")
	@Size(min = 3, max = 5)
	private String projectKey;
	
	@NotBlank(message = "Project key creation time cannot be empty")
	private Instant createdAt;
}
	