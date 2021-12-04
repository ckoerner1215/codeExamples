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
import com.bana.database.repository.ModuleRepository;

//@CrossOrigin(origins = "http://localhost:8081")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ModuleController {

  @Autowired
  ModuleRepository moduleRepository;

  @GetMapping("/modules")
  public ResponseEntity<List<Module>> getAllModules(@RequestParam(required = false) String moduleName) {
    try {
      List<Module> modules = new ArrayList<Module>();

      System.out.println("moduleName:" + moduleName);

      if (moduleName == null){
        moduleRepository.findAll().forEach(modules::add);
      }
      else
        moduleRepository.findByNameContaining(moduleName).forEach(modules::add);

      if (modules.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(modules, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/modules/{id}")
  public ResponseEntity<Module> getModuleById(@PathVariable("id") int id) {
    Optional<Module> moduleData = moduleRepository.findById(id);

    if (moduleData.isPresent()) {
      return new ResponseEntity<>(moduleData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/modules")
  public ResponseEntity<Module> createModule(@RequestBody Module module) {
    try {
      Module _module = moduleRepository
          .save(new Module(module.getName(), module.getDescription(), module.getPurpose(),
                  module.getCategory(), module.getLastBuild(), module.getLastExecuted(),
                  module.getNumberOfKshFiles()));
      return new ResponseEntity<>(_module, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/modules/{id}")
  public ResponseEntity<Module> updateModule(@PathVariable("id") int id, @RequestBody Module module) {
    Optional<Module> moduleData = moduleRepository.findById(id);

    if (moduleData.isPresent()) {
      Module _module = moduleData.get();
      _module.setName(module.getName());
      _module.setDescription(module.getDescription());
      _module.setPurpose(module.getPurpose());
      _module.setCategory(module.getCategory());
      _module.setLastBuild(module.getLastBuild());
      _module.setLastExecuted(module.getLastExecuted());
      _module.setNumberOfKshFiles(module.getNumberOfKshFiles());
      return new ResponseEntity<>(moduleRepository.save(_module), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/modules/{id}")
  public ResponseEntity<HttpStatus> deleteModule(@PathVariable("id") int id) {
    try {
      moduleRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/modules")
  public ResponseEntity<HttpStatus> deleteAllModules() {
    try {
      moduleRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

}
