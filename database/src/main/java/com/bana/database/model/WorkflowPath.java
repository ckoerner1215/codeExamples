package com.bana.database.model;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Workflow_Path")
public class WorkflowPath {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Path_ID")
	private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "Module_ID", nullable = false)
	private Module module;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "Workflow_ID", nullable = false)
	private Workflow workflow;

	@Column(name = "Module_ksh")
	private String moduleKsh;

	@Column(name = "Path_Notes")
	private String notes ;

	@Column(name = "Path_Status")
	private String status ;

	@Column(name = "Status_Date")
	private Date statusDate;

	public WorkflowPath(Module module, Workflow workflow, String moduleKsh, String notes, String status, Date statusDate) {
		super();
		this.module = module;
		this.workflow = workflow;
		this.moduleKsh = moduleKsh;
		this.notes = notes;
		this.status = status;
		this.statusDate = statusDate;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModuleKsh() {
		return moduleKsh;
	}

	public void setModuleKsh(String moduleKsh) {
		this.moduleKsh = moduleKsh;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public Workflow getWorkflow() {
		return workflow;
	}

	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	@Override
	public String toString() {
		return "WorkflowPath [id=" + id + ", workflowId=" + workflow.getId() + ", moduleID=" + module.getId() + ", moduleKsh="
				+ moduleKsh + ", notes=" + notes + ", status=" + status + ", statusDate=" + statusDate
				+ "]";
	}


}
