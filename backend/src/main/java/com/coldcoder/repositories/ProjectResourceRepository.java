package com.coldcoder.repositories;

import com.coldcoder.models.ProjectResource;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectResourceRepository extends JpaRepository<ProjectResource, Long>{
}
