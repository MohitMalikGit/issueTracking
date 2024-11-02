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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	 @NotNull
	private String name;

	@OneToOne
	@JoinColumn(nullable = true)
	private Employee manager;
	@Email
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

	@Override
	public String toString() {
		String output = "empName" + name + "empEmail" + email;
		return output;
	}

	
}
