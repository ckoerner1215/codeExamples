package com.bana.fileAccess.fileuploaddownload.model;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class RunStatus {

	private String name;
	private boolean success;
        private int errorCode;
        private String output;
	public RunStatus() {

	}

	public RunStatus(String name, int errorCode, boolean success, String output) {
		this.name = name;
		this.errorCode = errorCode;
		this.success = success;
                this.output = output;
	}

	public String getName() {
		return name;
	}

	public String getOutput() {
		return output;
	}

	public int getErrorCode(){
		return errorCode;
	}

	public boolean getSuccess(){
		return success;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "RunStatus [name=" + name + ", success=" + success + ", error code=" + errorCode + ",  output =" + output + "]";
	}
}
