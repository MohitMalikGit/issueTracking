package com.fil.issueTracking.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fil.issueTracking.Enum.ApprovalRequirement;

@Entity
public class IssueType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String type;
	@Enumerated(EnumType.STRING)
	private ApprovalRequirement  approvalRequirement;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ApprovalRequirement getRequirement() {
		return approvalRequirement;
	}
	public void setRequirement(ApprovalRequirement requirement) {
		this.approvalRequirement = requirement;
	}
	public IssueType(int id, String type, ApprovalRequirement requirement) {
		super();
		this.id = id;
		this.type = type;
		this.approvalRequirement = requirement;
	}
	public IssueType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
