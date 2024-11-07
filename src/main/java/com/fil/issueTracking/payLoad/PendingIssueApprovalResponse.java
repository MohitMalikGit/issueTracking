package com.fil.issueTracking.payLoad;

import lombok.Data;

@Data
public class PendingIssueApprovalResponse {
	String issueType;
	String issueId;
	String description;
	String issueTitle;
	String raisedBy;
	String createdAt;
}
