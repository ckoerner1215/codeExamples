package com.bana.database;

import java.util.Date;
import com.bana.database.model.Module;
import com.bana.database.model.ModuleInput;
import com.bana.database.repository.ModuleInputRepository;
import com.bana.database.repository.ModuleRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseApplication.class, args);
		
	}
	
	/*
	@Bean
	  public CommandLineRunner demo(ModuleInputRepository moduleInputRepository, ModuleRepository moduleRepository) {
	    return (args) -> {
			String name = "module1";
			String description = "my description";
			String purpose = "my purpose";
			String category = "my category";
			Date lastBuild = new Date();
			Date lastExecuted = new Date();
			int numberOfKshFiles = 10;
			Module module = new Module(name,description,purpose,category,
					lastBuild,lastExecuted,numberOfKshFiles);
			
			String filename = "input.dat";
			String type = "seq bin";
			String notes = "slkjdfksdf;lsdjf";
			
			ModuleInput moduleInput = new ModuleInput(module,filename, type, notes);
			moduleRepository.save(module);
			moduleInputRepository.save(moduleInput);
	    	
	    };
	}
	*/
}
