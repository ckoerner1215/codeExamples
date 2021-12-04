package com.bana.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bana.database.model.Module;
import com.bana.database.model.ModuleInput;

public interface ModuleInputRepository extends JpaRepository<ModuleInput, Integer> {
  List<ModuleInput> findByModuleId(Integer id);
  List<ModuleInput> findByModuleName(Integer name);
  List<ModuleInput> findByFilename(String filename);
}
