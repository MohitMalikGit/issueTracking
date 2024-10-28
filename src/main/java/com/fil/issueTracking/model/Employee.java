package com.fil.issueTracking.model;

import java.sql.Date;
import java.util.List;

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
	
	
}
