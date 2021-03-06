package com.coldcoder.repositories;

import com.coldcoder.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepositories extends JpaRepository<Project, Long> {
    Boolean existsByProjectKey(String projectKey);
    Project getByProjectKey(String projectKey);
    void deleteProjectByProjectKey(String projectKey);
}
