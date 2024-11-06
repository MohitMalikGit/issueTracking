package com.fil.issueTracking.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
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

import com.fil.issueTracking.enums.ApprovedStatus;
import com.fil.issueTracking.enums.IssueStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor; 
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Issue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="raisedBy")
	private Employee raisedBy;
	@ManyToOne
	@JoinColumn(name="approvedBy", nullable = true)
	private Employee approvedBy;
	@ManyToOne
	@JoinColumn(name = "assignedTo" , nullable = true)
	private Employee assignedTo;
	private String title;
	private String description; 
	//dates 
	private Timestamp createdAt;
	private Timestamp updatedAt;
	@OneToOne
	private IssueType issueType;
	@OneToMany
	@JoinColumn(name="logIssue")
	private List<IssueLog> logs;
	@Enumerated(EnumType.STRING)
	private IssueStatus status;
	@Enumerated(EnumType.STRING)
	private ApprovedStatus approvedStatus;
	@Column(nullable = true)
	String feedback;
	
	

	
	
	
	
	
}
