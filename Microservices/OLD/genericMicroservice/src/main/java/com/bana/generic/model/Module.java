package com.bana.ck009.model;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class Module {

	private String name;
	private String description;
        private String[] infiles;
        private String outputDirectory;
        private String scriptLocation;
        private String executableLocation;
	public Module() {

	}

	public Module(String name, String description, String[] infiles, String outputDirectory, String scriptLocation, String executableLocation) {
		this.name = name;
		this.description = description;
                int i = 0;
                for (String file : infiles){
                      this.infiles[i] = file;
                      i=++;
                } 
		this.outputDirectory = outputDirectory;
                this.scriptLocation = scriptLocation;
                this.executableLocation = executableLocation;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String[] getInfiles(){
		return infiles;
	}

	public String getOutputDirectory(){
		return outputDirectory;
	}

	public String getScriptLocation(){
		return scriptLocation;
	}

	public String getExecutableLocation(){
		return executableLocation;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setInfiles(String[] infiles) {
                int i = 0;
                for (String file : infiles){
                      this.infiles[i] = file;
                      i=++;
                }
	}

	public void setOutputDirectory(String outputDirectory) {
		this.outputDirectory = outputDirectory;
	}

	public void setScriptLocation(String scriptLocation) {
		this.scriptLocation = scriptLocation;
	}

	public void setExecutableLocation(String executableLocation) {
		this.executableLocation = executableLocation;
	}

        public String[] getFields() {
               List<String> listOfFields = new ArrayList<String>();
               Field[] fields = this.getClass().getDeclaredFields();
               for (Field f : fields){
                   listOfFields.add(f.getName());
               }
               String[] arrayOfFields = new String[listOfFields.size()];
               for ( int i=0; i<listOfFields.size(); i++){
                   arrayOfFields[i] = (String) listOfFields.get(i);
               }
               return arrayOfFields;
        }

	@Override
	public String toString() {
		return "Module [name=" + name + ", description=" + description + ", infile1=" + infiles[0] + 
                   ", outputDirectory=" + outputDirectory + ", script location=" + scriptLocation + ", executable location=" + executableLocation + "]";
	}
}


