package com.bana.fileAccess.fileuploaddownload.controller;


public class MoveInfo {

        private String filename;
        private String destination;
	public MoveInfo() {

	}

	public MoveInfo(String destination, 
		String filename){ 
		this.destination = destination;
                this.filename = filename;
	}


	public String getDestination(){
		return destination;
	}

	public String getFilename(){
		return filename;
	}


	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public String toString() {
		return "Inputs [destination=" + destination + 
                   ", filename=" + filename + "]";
	}
}

