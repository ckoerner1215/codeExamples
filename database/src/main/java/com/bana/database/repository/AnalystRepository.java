package com.bana.database.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bana.database.model.Analyst;

public interface AnalystRepository extends JpaRepository<Analyst, Integer> {
    List<Analyst> findByFirstName(String firstName);
    List<Analyst> findByLastName(String lastName);
    Optional<Analyst> findById(Integer id);
    List<Analyst> findByOrganization(String organization);

}
