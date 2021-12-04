package com.bana.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bana.database.model.Workflow;

public interface WorkflowRepository  extends JpaRepository<Workflow, Integer> {

}
