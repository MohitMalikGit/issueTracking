package com.fil.issueTracking.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;




@Entity
public class Employee {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;

	@OneToOne
	@JoinColumn(nullable = true)
	private Employee manager;
	private String email;
	private String phone;
	private Date dob;
	private Date doj;
	private String password;
	@OneToMany
	@JoinColumn(name="raisedBy")
	private List<Issue> issueRaised;
	
	@OneToMany
	@JoinColumn(name="approvedBy")
	private List<Issue> approvals;
	
	
	@OneToMany
	@JoinColumn(name="assignedTo")
	private List<Issue> assignedIssue;
	
	@OneToMany
	private List<IssueType> expertise;
	
	@OneToOne
	private EmployeeType employeeType;

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

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Issue> getIssueRaised() {
		return issueRaised;
	}

	public void setIssueRaised(List<Issue> issueRaised) {
		this.issueRaised = issueRaised;
	}

	public List<Issue> getApprovals() {
		return approvals;
	}

	public void setApprovals(List<Issue> approvals) {
		this.approvals = approvals;
	}

	public List<Issue> getAssignedIssue() {
		return assignedIssue;
	}

	public void setAssignedIssue(List<Issue> assignedIssue) {
		this.assignedIssue = assignedIssue;
	}

	public List<IssueType> getExpertise() {
		return expertise;
	}

	public void setExpertise(List<IssueType> expertise) {
		this.expertise = expertise;
	}

	public EmployeeType getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

	public Employee(int id, String name, Employee manager, String email, String phone, Date dob, Date doj,
			String password, List<Issue> issueRaised, List<Issue> approvals, List<Issue> assignedIssue,
			List<IssueType> expertise, EmployeeType employeeType) {
		super();
		this.id = id;
		this.name = name;
		this.manager = manager;
		this.email = email;
		this.phone = phone;
		this.dob = dob;
		this.doj = doj;
		this.password = password;
		this.issueRaised = issueRaised;
		this.approvals = approvals;
		this.assignedIssue = assignedIssue;
		this.expertise = expertise;
		this.employeeType = employeeType;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		String output = "empName" + name + "empEmail" + email;
		return output;
	}
	
	

	  
	
	
}
