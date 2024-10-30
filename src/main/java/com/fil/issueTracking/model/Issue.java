package com.fil.issueTracking.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import com.fil.issueTracking.Enum.ApprovedStatus;
import com.fil.issueTracking.Enum.IssueStatus;

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
	private Employee assignedTo;
	private String title;
	private String detail;
	//dates
	private Date raisedOn;
	private Date approvedOn;
	private Date solvedOn;
	private Date rejectedOn;
	@OneToOne
	private IssueType issueType;
	@OneToMany
	@JoinColumn(name="logIssue")
	private List<IssueLog> logs;

	public Issue(int id, Employee raisedBy, Employee approvedBy, Employee assignedTo, String title, String detail,
			Date raisedOn, Date approvedOn, Date solevdOn, Date rejectedOn, IssueType issueType, List<IssueLog> logs,
			IssueStatus status, ApprovedStatus approvedStatus, String feedback) {
		super();
		this.id = id;
		this.raisedBy = raisedBy;
		this.approvedBy = approvedBy;
		this.assignedTo = assignedTo;
		this.title = title;
		this.detail = detail;
		this.raisedOn = raisedOn;
		this.approvedOn = approvedOn;
		this.solvedOn = solevdOn;
		this.rejectedOn = rejectedOn;
		this.issueType = issueType;
		this.logs = logs;
		this.status = status;
		this.approvedStatus = approvedStatus;
		this.feedback = feedback;
	}
	@Enumerated(EnumType.STRING)
	private IssueStatus status;
	@Enumerated(EnumType.STRING)
	private ApprovedStatus approvedStatus;
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	private String feedback;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Employee getRaisedBy() {
		return raisedBy;
	}
	public void setRaisedBy(Employee raisedBy) {
		this.raisedBy = raisedBy;
	}
	public Employee getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(Employee approvedBy) {
		this.approvedBy = approvedBy;
	}
	public Employee getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(Employee assignedTo) {
		this.assignedTo = assignedTo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Date getRaisedOn() {
		return raisedOn;
	}
	public void setRaisedOn(Date raisedOn) {
		this.raisedOn = raisedOn;
	}
	public Date getApprovedOn() {
		return approvedOn;
	}
	public void setApprovedOn(Date approvedOn) {
		this.approvedOn = approvedOn;
	}
	public Date getSolevdOn() {
		return solvedOn;
	}
	public void setSolevdOn(Date solevdOn) {
		this.solvedOn = solevdOn;
	}
	public Date getRejectedOn() {
		return rejectedOn;
	}
	public void setRejectedOn(Date rejectedOn) {
		this.rejectedOn = rejectedOn;
	}
	public IssueType getIssueType() {
		return issueType;
	}
	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}
	public List<IssueLog> getLogs() {
		return logs;
	}
	public void setLogs(List<IssueLog> logs) {
		this.logs = logs;
	}
	public IssueStatus getStatus() {
		return status;
	}
	public void setStatus(IssueStatus status) {
		this.status = status;
	}
	public ApprovedStatus getApprovedStatus() {
		return approvedStatus;
	}
	public void setApprovedStatus(ApprovedStatus approvedStatus) {
		this.approvedStatus = approvedStatus;
	}
	public Issue() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	
}
