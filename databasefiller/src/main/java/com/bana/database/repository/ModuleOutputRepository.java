package com.bana.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bana.database.model.Module;
import com.bana.database.model.ModuleOutput;

public interface ModuleOutputRepository extends JpaRepository<ModuleOutput, Integer> {
  List<ModuleOutput> findByModuleId(Integer id);
  List<ModuleOutput> findByModuleName(String name);
  List<ModuleOutput> findByFilename(String filename);
}
