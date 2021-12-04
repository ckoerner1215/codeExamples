package com.bana.database.model;
import javax.persistence.*;

import com.bana.database.model.Module;

import java.util.Date;

@Entity
@Table(name = "Module_Notes")
public class ModuleNotes {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Notes_ID")
	private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "Module_ID", nullable = false)
	private Module module;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "Analyst_ID", nullable = false)
	private Analyst analyst;

	@Column(name = "Date_Entered")
	private Date dateEntered ;

	@Column(name = "AnalystNotes")
	private String analystNotes;

	public ModuleNotes(Module module, Analyst analyst, Date dateEntered, String analystNotes) {
		super();
		this.module = module;
		this.analyst = analyst;
		this.dateEntered = dateEntered;
		this.analystNotes = analystNotes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Date getDateEntered() {
		return dateEntered;
	}

	public void setDateEntered(Date dateEntered) {
		this.dateEntered = dateEntered;
	}

	public String getAnalystNotes() {
		return analystNotes;
	}

	public void setAnalystNotes(String analystNotes) {
		this.analystNotes = analystNotes;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Analyst getAnalyst() {
		return analyst;
	}

	public void setAnalyst(Analyst analyst) {
		this.analyst = analyst;
	}

	@Override
	public String toString() {
		return "ModuleNotes [id=" + id + ", moduleID=" + module.getId() + ", analystID=" + analyst.getId() + ", dateEntered="
				+ dateEntered + ", analystNotes=" + analystNotes + "]";
	}

}
