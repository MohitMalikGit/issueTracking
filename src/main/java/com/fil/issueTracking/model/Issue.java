package com.fil.issueTracking.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Issue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="raisedBy")
	private Employee raisedBy;
	@ManyToOne
	@JoinColumn(name="approvedBy")
	private Employee approvedBy;
	@ManyToOne
	@JoinColumn(name = "assignedTo")
	private Employee assignedTo;////////////////////////////////////////////////////////////////////////////////////////////;;;;;;;;;;;
	private String title;
	private String detail;
	//dates
	private Date raisedOn;
	private Date approvedOn;
	private Date solevdOn;
	private Date rejectedOn;
	@OneToOne
	private IssueType issueType;
	@OneToMany
	@JoinColumn(name="logIssue")
	private List<IssueLog> logs;
	
	
}
