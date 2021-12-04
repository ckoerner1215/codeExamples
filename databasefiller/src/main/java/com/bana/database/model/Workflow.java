package com.bana.database.model;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Workflow")
public class Workflow {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Workflow_ID")
	private int id;

	@Column(name = "Workflow_Name")
	private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "Analyst_ID", nullable = false)
	private Analyst analyst;

    @OneToMany(mappedBy = "workflow", fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    private Set<WorkflowPath> paths;

	@Column(name = "Workflow_Date")
	private Date date;

	@Column(name = "Workflow_Notes")
	private String notes ;

	@Column(name = "Workflow_Status")
	private String status ;

	@Column(name = "Status_Date")
	private Date statusDate;

	public Workflow(String name, Analyst analyst, Date date, String notes, String status, Date statusDate) {
		super();
		this.name = name;
		this.analyst = analyst;
		this.date = date;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public Analyst getAnalyst() {
		return analyst;
	}

	public void setAnalyst(Analyst analyst) {
		this.analyst = analyst;
	}

	public Set<WorkflowPath> getPaths() {
		return paths;
	}

	public void setPaths(Set<WorkflowPath> paths) {
		this.paths = paths;
	}

	@Override
	public String toString() {
		return "Workflow [id=" + id + ", name=" + name + ", analyst=" + analyst + ", date="
				+ date + ", notes=" + notes + ", status=" + status
				+ ", statusDate=" + statusDate + "]";
	}

}
