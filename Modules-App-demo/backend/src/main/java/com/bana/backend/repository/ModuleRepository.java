package com.bana.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bana.backend.model.Module;

public interface ModuleRepository extends JpaRepository<Module, Long> {
  List<Module> findByPublished(boolean published);
  List<Module> findByTitleContaining(String title);
}
