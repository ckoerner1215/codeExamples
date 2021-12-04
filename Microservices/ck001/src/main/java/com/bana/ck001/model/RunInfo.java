package com.bana.ck001.model;


public class RunInfo {

	private String name;
	private String description;
        private String infile1;
        private String infile2;
        private String outputDirectory;
        private String scriptLocation;
        private String executableLocation;
	public RunInfo() {

	}

	public RunInfo(String name, 
		String description, 
		String infile1, 
		String infile2, 
		String outputDirectory, 
		String scriptLocation, 
		String executableLocation){
		this.name = name;
		this.description = description;
		this.infile1 = infile1;
		this.infile2 = infile2;
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

	public String getInfile1(){
		return infile1;
	}

	public String getInfile2(){
		return infile2;
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

	public void setInfile1(String infile1) {
		this.infile1 = infile1;
	}

	public void setInfile2(String infile2) {
		this.infile2 = infile2;
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


	@Override
	public String toString() {
		return "Inputs [name=" + name + ", description=" + description + ", infile1=" + infile1 + ", infile2=" + infile2 + 
                   ", outputDirectory=" + outputDirectory + ", script location=" + scriptLocation + ", executable location=" + executableLocation + "]";
	}
}


