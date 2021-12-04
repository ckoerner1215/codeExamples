package com.bana.ck009.model;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class Inputs {

	private String name;
	private String description;
        private String infile1;
        private String infile1Description;
        private String outputDirectory;
        private String outputDirectoryDescription;
        private String scriptLocation;
        private String scriptLocationDescription;
        private String executableLocation;
        private String executableLocationDescription;
	public Inputs() {

	}

	public Inputs(String name, 
		String description, 
		String infile1, 
		String infile1Description, 
		String outputDirectory, 
		String outputDirectoryDescription, 
		String scriptLocation, 
		String scriptLocationDescription, 
		String executableLocation,
		String executableLocationDescription) {
		this.name = name;
		this.description = description;
		this.infile1 = infile1;
		this.infile1Description = infile1Description;
		this.outputDirectory = outputDirectory;
		this.outputDirectoryDescription = outputDirectoryDescription;
                this.scriptLocation = scriptLocation;
                this.scriptLocationDescription = scriptLocationDescription;
                this.executableLocation = executableLocation;
                this.executableLocationDescription = executableLocationDescription;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getInfile1Description(){
		return infile1Description;
	}

	public String getOutputDirectoryDescription(){
		return outputDirectoryDescription;
	}

	public String getScriptLocationDescription(){
		return scriptLocationDescription;
	}

	public String getExecutableLocationDescription(){
		return executableLocationDescription;
	}

	public String getInfile1(){
		return infile1;
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

	public void setInfile1Description(String infile1Description) {
		this.infile1Description = infile1Description;
	}

	public void setOutputDirectoryDescription(String outputDirectoryDescription) {
		this.outputDirectoryDescription = outputDirectoryDescription;
	}

	public void setScriptLocationDescription(String scriptLocationDescription) {
		this.scriptLocationDescription = scriptLocationDescription;
	}

	public void setExecutableLocationDescription(String executableLocationDescription) {
		this.executableLocationDescription = executableLocationDescription;
	}

	public void setInfile1(String infile1) {
		this.infile1 = infile1;
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
		return "Inputs [name=" + name + ", description=" + description + ", infile1=" + infile1 +
                   ", outputDirectory=" + outputDirectory + ", scriptLocation=" + scriptLocation + ", executableLocation=" + executableLocation + "]";
	}
}


