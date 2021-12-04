package com.bana.generic.model;


public class RunInfo {

	private String name;
        private String scriptLocation;
        private String kshFile;
        private String kshFileContents;
        private String kshFileName;
	public RunInfo() {

	}

	public RunInfo(String name, 
		String kshFile, 
		String scriptLocation){ 
		this.name = name;
		this.kshFile = kshFile;
                this.scriptLocation = scriptLocation;
	}

	public String getName() {
		return name;
	}

	public String getKshFile(){
		return kshFile;
	}

	public String getKshFileContents(){
		return kshFileContents;
	}

	public String getKshFileName(){
		return kshFileName;
	}

	public String getScriptLocation(){
		return scriptLocation;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setKshFile(String kshFile) {
		this.kshFile = kshFile;
	}

	public void setKshFileContents(String kshFileContents) {
		this.kshFileContents = kshFileContents;
	}

	public void setKshFileName(String kshFileName) {
		this.kshFileName = kshFileName;
	}

	public void setScriptLocation(String scriptLocation) {
		this.scriptLocation = scriptLocation;
	}

	@Override
	public String toString() {
		return "Inputs [name=" + name + ", kshFile=" + kshFile + 
                   ", script location=" + scriptLocation + "]";
	}
}


