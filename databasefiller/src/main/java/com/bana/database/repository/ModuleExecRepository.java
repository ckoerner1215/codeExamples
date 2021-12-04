package com.bana.database.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bana.database.model.ModuleExec;

public interface ModuleExecRepository extends JpaRepository<ModuleExec, Integer> {
    List<ModuleExec> findByBuildNbr(Integer buildNbr);
    List<ModuleExec> findByLastExecuted(String lastName);
    Optional<ModuleExec> findById(Integer id);
    List<ModuleExec> findByModuleId(Integer id);
    List<ModuleExec> findByAnalystId(Integer id);

}
