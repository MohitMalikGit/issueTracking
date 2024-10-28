package com.fil.issueTracking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class IssueType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String type;
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
	public IssueType(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}
	public IssueType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
