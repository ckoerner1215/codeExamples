package com.bana.ck100.model;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class RunStatus {

	private String name;
	private boolean success;
        private int errorCode;
        private String description;
	public RunStatus() {

	}

	public RunStatus(String name, String description, int errorCode, boolean success) {
		this.name = name;
		this.description = description;
		this.errorCode = errorCode;
		this.success = success;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
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

	public void setDescription(String description) {
		this.description = description;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "RunStatus [name=" + name + ", description=" + description + ", success=" + success + ", error code=" + errorCode + "]";
	}
}
