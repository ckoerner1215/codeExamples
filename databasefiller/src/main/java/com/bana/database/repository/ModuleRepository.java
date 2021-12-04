package com.bana.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bana.database.model.Module;

public interface ModuleRepository extends JpaRepository<Module, Integer> {
    List<Module> findByName(String name);
    List<Module> findByCategory(String category);
    List<Module> findByNumberOfKshFiles(int numberOfKshFiles);
    List<Module> findByNameContaining(String name);
}
