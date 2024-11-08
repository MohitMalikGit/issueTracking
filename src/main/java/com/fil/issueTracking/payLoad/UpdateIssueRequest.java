package com.fil.issueTracking.payLoad;

import java.util.List;

import lombok.Data;

@Data
public class UpdateIssueRequest {
	String assigneeId;
	List<String> remarks;
	String status;
}
