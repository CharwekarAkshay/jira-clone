package com.coldcoder.models;

import java.time.Instant;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long projectId;
	
	@NotBlank(message = "Project name cannot be empty")
	private String projectName;
	
	@NotBlank(message = "Project key cannot be empty")
	@Size(min = 3, max = 5)
	@Column(length = 5)
	private String projectKey;
	
	@NotBlank(message = "Project key creation time cannot be empty")
	private Instant createdAt;
}
	