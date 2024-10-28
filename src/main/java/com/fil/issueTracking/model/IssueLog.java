package com.fil.issueTracking.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class IssueLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int des;
	private Date date;
	@ManyToOne
	@JoinColumn(name="logIssue")
	private Issue logIssue;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDes() {
		return des;
	}
	public void setDes(int des) {
		this.des = des;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Issue getLogIssue() {
		return logIssue;
	}
	public void setLogIssue(Issue logIssue) {
		this.logIssue = logIssue;
	}
	public IssueLog(int id, int des, Date date, Issue logIssue) {
		super();
		this.id = id;
		this.des = des;
		this.date = date;
		this.logIssue = logIssue;
	}
	public IssueLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
