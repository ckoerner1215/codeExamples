package com.bana.database;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bana.database.repository.AnalystRepository;
import com.bana.database.repository.ModuleInputRepository;
import com.bana.database.repository.ModuleNotesRepository;
import com.bana.database.repository.ModuleOutputRepository;
import com.bana.database.repository.ModuleRepository;
import com.bana.database.repository.WorkflowRepository;
import com.bana.database.repository.WorkflowPathRepository;
import com.bana.database.repository.ModuleExecRepository;
import com.bana.database.model.Analyst;
import com.bana.database.model.Module;
import com.bana.database.model.ModuleInput;
import com.bana.database.model.ModuleNotes;
import com.bana.database.model.ModuleOutput;
import com.bana.database.model.ModuleExec;
import com.bana.database.model.Workflow;
import com.bana.database.model.WorkflowPath;

@Service("moduleService")
public class ModuleService {

	  @Autowired
	  private ModuleRepository moduleRepository;
	  @Autowired
	  private ModuleInputRepository moduleInputRepository;
	  @Autowired
	  private ModuleOutputRepository moduleOutputRepository;
	  @Autowired
	  private AnalystRepository analystRepository;
	  @Autowired
	  private ModuleNotesRepository moduleNotesRepository;
	  @Autowired
	  private ModuleExecRepository moduleExecRepository;
	  @Autowired
	  private WorkflowRepository workflowRepository;
	  @Autowired
	  private WorkflowPathRepository workflowPathRepository;

	  public void addStuffToDatabase() {
		  
		    List<String> names = new ArrayList<String>();
		    List<String> descriptions = new ArrayList<String>();
		    List<String> purposes = new ArrayList<String>();
		    List<String> categories = new ArrayList<String>();
		    
		    List<String> lastNames = new ArrayList<String>();
		    List<String> firstNames = new ArrayList<String>();
		    List<String> organizations = new ArrayList<String>();

		    List<String> vms = new ArrayList<String>();
		    List<String> status = new ArrayList<String>();
		    List<String> kshs = new ArrayList<String>();
		    
		    List<Module> modules = new ArrayList<Module>();
		    List<ModuleInput> moduleInputs = new ArrayList<ModuleInput>();
		    List<ModuleOutput> moduleOutputs = new ArrayList<ModuleOutput>();
		    List<Analyst> analysts = new ArrayList<Analyst>();
		    List<ModuleNotes> moduleNotes = new ArrayList<ModuleNotes>();
		    List<ModuleExec> moduleExecs = new ArrayList<ModuleExec>();
		    List<Workflow> workflows = new ArrayList<Workflow>();
		    List<WorkflowPath> workflowPaths = new ArrayList<WorkflowPath>();
			Date thedate = new Date();
			Calendar c = Calendar.getInstance(); 
			c.setTime(thedate); 
		    
		    for (int i=1; i<=10; i++) {
		    	names.add("module"+i);
		    	descriptions.add("description for module "+i);
		    	purposes.add("This is the purpose of module "+i);
		    	categories.add("category " + i);
		    }
		    
		    
		    for (int i = 0; i<10; i++) {
				c.add(Calendar.DATE, 1);
				thedate = c.getTime();			
		    	
		    	modules.add(new Module(names.get(i),descriptions.get(i),purposes.get(i),categories.get(i),
		    			thedate,thedate,i));
		    }
		    
		    for (int i =0; i<10; i++) {
		    	moduleRepository.save(modules.get(i));
		    }
		  
			
			List<String> filenames = new ArrayList<String>();
			List<String> types = new ArrayList<String>();
			List<String> notes = new ArrayList<String>();
			
			for(int i=1; i<=10; i++) {
				filenames.add("input"+i+".dat");
				types.add("type"+i);
				notes.add("Notes for input "+i);					
			}
			
			for(int i=0; i<10; i++) {
				moduleInputs.add(new ModuleInput(modules.get(i),filenames.get(i),types.get(i),notes.get(i)));
			}
			
			for(int i=11; i<=20; i++) {
				filenames.add("input"+i+".dat");
				types.add("type"+i);
				notes.add("Notes for input "+i);					
			}

			
			for(int i=10; i<20; i++) {
				moduleInputs.add(new ModuleInput(modules.get(i-10),filenames.get(i),types.get(i),notes.get(i)));
			}
		    for (int i =0; i<20; i++) {
		    	moduleInputRepository.save(moduleInputs.get(i));
		    }
		    
		    filenames.clear();
		    types.clear();
		    notes.clear();
			for(int i=1; i<=10; i++) {
				filenames.add("output"+i+".dat");
				types.add("output type"+i);
				notes.add("Notes for output "+i);					
			}
			
			for(int i=0; i<10; i++) {
				moduleOutputs.add(new ModuleOutput(modules.get(i),filenames.get(i),types.get(i),notes.get(i)));
			}
		    for (int i =0; i<10; i++) {
		    	moduleOutputRepository.save(moduleOutputs.get(i));
		    }
		    
		    for(int i=1; i<=5; i++) {
		    	lastNames.add("LastName"+i);
		    	firstNames.add("FirstName"+i);
		    	organizations.add("NGA"+i);
		    }
		    for (int i = 0; i<5; i++) {
				c.add(Calendar.DATE, 1);
				thedate = c.getTime();			
		    	
		    	analysts.add(new Analyst(lastNames.get(i),firstNames.get(i),organizations.get(i),thedate));
		    }
		    for (int i =0; i<5; i++) {
		    	analystRepository.save(analysts.get(i));
		    }
		    notes.clear();
			for(int i=1; i<=10; i++) {
				notes.add("Notes for module "+i);					
			}
			
			Analyst tempAnalyst;
			for(int i=0; i<10; i++) {
				c.add(Calendar.DATE, 1);
				thedate = c.getTime();	
				if(i < 5) {
					tempAnalyst = analysts.get(i);
				} else {
					tempAnalyst = analysts.get(i-5);
				}
				moduleNotes.add(new ModuleNotes(modules.get(i),tempAnalyst,thedate,notes.get(i)));
			}
		    for (int i =0; i<10; i++) {
		    	moduleNotesRepository.save(moduleNotes.get(i));
		    }

		    notes.clear();
		    for(int i=1; i<=10; i++) {
                             notes.add("Execution notes for module "+i);
                             vms.add("VM"+i);
                    }

                    for(int i=0; i<10; i++) {
                           if(i < 5) {
                                  tempAnalyst = analysts.get(i);
                           } else {
                                  tempAnalyst = analysts.get(i-5);
                           }
                           c.add(Calendar.DATE, 1);
                           thedate = c.getTime();
                           moduleExecs.add(new ModuleExec(modules.get(i), tempAnalyst, thedate,i,vms.get(i), thedate, notes.get(i)));
                    }
                    for (int i=0; i<10; i++) {
                        moduleExecRepository.save(moduleExecs.get(i));
                    }


                    notes.clear();
                    names.clear();
                    for(int i=1; i<=5; i++) {
                             notes.add("Workflow notes "+i);
                             status.add("Status "+i);
                             names.add("Workflow name "+i);
                    }

                    for(int i=0; i<5; i++) {
                           if(i < 5) {
                                  tempAnalyst = analysts.get(i);
                           } else {
                                  tempAnalyst = analysts.get(i-5);
                           }
                           c.add(Calendar.DATE, 1);
                           thedate = c.getTime();
                           workflows.add(new Workflow(names.get(i), tempAnalyst, thedate, notes.get(i), status.get(i), thedate));
                    }
                    for (int i=0; i<5; i++) {
                        workflowRepository.save(workflows.get(i));
                    }




                    notes.clear();
                    status.clear();
                    for(int i=1; i<=10; i++) {
                             notes.add("Workflow Path notes "+i);
                             status.add("Workflow Path Status "+i);
                             kshs.add("module"+i+".ksh");
                    }

                    Workflow tempWorkflow;
                    for(int i=0; i<10; i++) {
                           if(i < 5) {
                                  tempWorkflow = workflows.get(i);
                           } else {
                                  tempWorkflow = workflows.get(i-5);
                           }
                           c.add(Calendar.DATE, 1);
                           thedate = c.getTime();
                           workflowPaths.add(new WorkflowPath(modules.get(i), tempWorkflow, kshs.get(i), notes.get(i), status.get(i), thedate));
                    }
                    for (int i=0; i<10; i++) {
                        workflowPathRepository.save(workflowPaths.get(i));
                    }




					  
	  }
}
