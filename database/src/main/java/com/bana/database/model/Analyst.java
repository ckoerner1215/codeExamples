package com.bana.database.model;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Analysts")
public class Analyst {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Analyst_ID")
	private int id;

	@Column(name = "Analyst_Last")
	private String lastName;

	@Column(name = "Analyst_First")
	private String firstName;

	@Column(name = "Analyst_Org")
	private String organization;

	@Column(name = "LastUpdated")
	private Date lastUpdated;

    @OneToMany(mappedBy = "analyst", fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    private Set<Workflow> workflows;

    @OneToMany(mappedBy = "analyst", fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    private Set<ModuleExec> moduleExecutions;

    @OneToMany(mappedBy = "analyst", fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    private Set<ModuleNotes> moduleNotes;
    
	public Analyst(String lastName, String firstName, String organization, Date lastUpdated) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.organization = organization;
		this.lastUpdated = lastUpdated;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public Set<ModuleExec> getModuleExecutions() {
		return moduleExecutions;
	}

	public void setModuleExecutions(Set<ModuleExec> moduleExecutions) {
		this.moduleExecutions = moduleExecutions;
	}

	public Set<ModuleNotes> getModuleNotes() {
		return moduleNotes;
	}

	public void setModuleNotes(Set<ModuleNotes> moduleNotes) {
		this.moduleNotes = moduleNotes;
	}

	public Set<Workflow> getWorkflows() {
		return workflows;
	}

	public void setWorkflows(Set<Workflow> workflows) {
		this.workflows = workflows;
	}

	@Override
	public String toString() {
		return "Analyst [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName
				+ ", Organization=" + organization + ", lastUpdated=" + lastUpdated + "]";
	}
}
