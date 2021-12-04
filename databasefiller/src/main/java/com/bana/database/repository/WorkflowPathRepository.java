package com.bana.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bana.database.model.WorkflowPath;

public interface WorkflowPathRepository  extends JpaRepository<WorkflowPath, Integer> {

}
