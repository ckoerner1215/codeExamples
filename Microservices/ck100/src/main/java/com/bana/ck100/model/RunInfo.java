package com.bana.ck100.model;


public class RunInfo {

	private String name;
	private String description;
        private String numberOfPoints;
        private String points;
        private String outputDirectory;
        private String scriptLocation;
        private String executableLocation;
	public RunInfo() {

	}

	public RunInfo(String name, 
		String description, 
		String numberOfPoints, 
		String points, 
		String outputDirectory, 
		String scriptLocation, 
		String executableLocation){
		this.name = name;
		this.description = description;
		this.numberOfPoints = numberOfPoints;
		this.points = points;
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

	public String getNumberOfPoints(){
		return numberOfPoints;
	}

	public String getPoints(){
		return points;
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

	public void setNumberOfPoints(String numberOfPoints) {
		this.numberOfPoints = numberOfPoints;
	}

	public void setPoints(String points) {
		this.points = points;
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
		return "Inputs [name=" + name + ", description=" + description + ", number of points=" + numberOfPoints + ", points: " + points +  
                   ", outputDirectory=" + outputDirectory + ", script location=" + scriptLocation + ", executable location=" + executableLocation + "]";
	}
}


