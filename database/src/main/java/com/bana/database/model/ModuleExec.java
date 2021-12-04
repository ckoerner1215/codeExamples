package com.bana.database.model;

import java.util.Date;

import javax.persistence.*;

import com.bana.database.model.Module;

@Entity
@Table(name = "Module_Exec")
public class ModuleExec {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Exec_ID")
	private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "Module_ID", nullable = false)
	private Module module;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "Analyst_ID", nullable = false)
	private Analyst analyst;

	@Column(name = "LastExecuted")
	private Date lastExecuted ;

	@Column(name = "BuildNbr")
	private int buildNbr;

	@Column(name = "Exec_VM")
	private String vm;

	@Column(name = "Exec_Time")
	private Date time ;

	@Column(name = "Exec_Notes")
	private String notes;

        public ModuleExec(Module module, Analyst analyst, Date lastExecuted, int buildNbr, 
              String vm, Date time, String notes) {
		super();
		this.module = module;
		this.analyst = analyst;
		this.lastExecuted = lastExecuted;
		this.buildNbr = buildNbr;
                this.vm = vm;
		this.time = time;
		this.notes = notes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getLastExecuted() {
		return lastExecuted;
	}

	public void setLastExecuted(Date lastExecuted) {
		this.lastExecuted = lastExecuted;
	}

	public int getBuildNbr() {
		return buildNbr;
	}

	public void setBuildNbr(int buildNbr) {
		this.buildNbr = buildNbr;
	}

	public String getVm() {
		return vm;
	}

	public void setVm(String vm) {
		this.vm = vm;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	@Override
	public String toString() {
		return "ModuleExec [id=" + id + ", module=" + module + ", lastExecuted=" + lastExecuted + ", buildNbr="
				+ buildNbr + ", analystID=" + analyst.getId() + ", vm=" + vm + ", time=" + time
				+ ", notes=" + notes + "]";
	}

}
