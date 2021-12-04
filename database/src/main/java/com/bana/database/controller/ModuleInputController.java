package com.bana.database.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bana.database.model.Module;
import com.bana.database.model.ModuleInput;
import com.bana.database.repository.ModuleRepository;
import com.bana.database.repository.ModuleInputRepository;

//@CrossOrigin(origins = "http://localhost:8081")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ModuleInputController {

  @Autowired
  private ModuleInputRepository moduleInputRepository;

  @Autowired
  private ModuleRepository moduleRepository;

  @GetMapping("/moduleinputs")
  public ResponseEntity<List<ModuleInput>> getAllModuleInputs() {
    try {
      List<ModuleInput> moduleInputs = new ArrayList<ModuleInput>();
      moduleInputRepository.findAll().forEach(moduleInputs::add);

      if (moduleInputs.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(moduleInputs, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/moduleinputs/{id}")
  public ResponseEntity<ModuleInput> getModuleInputById(@PathVariable("id") int id) {
    Optional<ModuleInput> moduleInputData = moduleInputRepository.findById(id);

    if (moduleInputData.isPresent()) {
      return new ResponseEntity<>(moduleInputData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @PostMapping("/moduleinputs")
  public ResponseEntity<ModuleInput> createModuleInput(@RequestBody ModuleInput moduleInput) {
    try {
      ModuleInput _moduleInput = moduleInputRepository
          .save(new ModuleInput(moduleInput.getModule(), moduleInput.getFilename(), 
        		        moduleInput.getType(), moduleInput.getNotes()));
      return new ResponseEntity<>(_moduleInput, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @DeleteMapping("/moduleinputs/{id}")
  public ResponseEntity<HttpStatus> deleteModuleInput(@PathVariable("id") int id) {
    try {
      moduleInputRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/moduleinputs")
  public ResponseEntity<HttpStatus> deleteAllModuleInputs() {
    try {
      moduleInputRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @GetMapping("/moduleinputs/module/{moduleId}")
  public ResponseEntity<List<ModuleInput>> getInputsByModuleId(@PathVariable("moduleId") Integer moduleId) {
      List<ModuleInput> moduleInputList =  moduleInputRepository.findByModuleId(moduleId);
    if (!moduleInputList.isEmpty()) {
      return new ResponseEntity<>(moduleInputList, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
