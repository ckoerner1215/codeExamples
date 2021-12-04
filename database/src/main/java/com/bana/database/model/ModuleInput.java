package com.bana.database.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Module_Input")
public class ModuleInput implements Serializable{
	

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Input_ID")
	private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "Module_ID", nullable = false)
	private Module module;
	
	@Column(name = "Input_Filename")
	private String filename;
	
	@Column(name = "Input_Type")
	private String type;
	
	@Column(name = "Input_Notes")
	private String notes;
	
	public ModuleInput(Module module, String filename, String type, String notes) {
		super();
		this.module = module;
		this.filename = filename;
		this.type = type;
		this.notes = notes;
	}
	
  
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "ModuleInput [id=" + id + ", module=" + module + ", Filename=" + filename + ", Type="
				+ type + ", Notes=" + notes + "]";
	}

}
