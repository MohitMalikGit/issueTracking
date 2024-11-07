package com.fil.issueTracking.payLoad;

import lombok.Data;

@Data
public class UpdateIssueApprovalStatusRequest {
	String status;
	String comment;
}
