package com.bana.ck100.model;

import java.util.ArrayList;
import java.util.List;

public class Input {

        private String key;
	private String label;
	private String description;
        private String value;
	public Input() {

	}

	public Input(String key,
                String label, 
		String description, 
		String value) {
		this.label = label;
		this.description = description;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getLabel() {
		return label;
	}

	public String getDescription() {
		return description;
	}

	public String getValue(){
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "Input [label=" + label + ", description=" + description + ", value=" + value + ", key=" + key + "]";
	}
}


