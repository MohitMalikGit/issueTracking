package com.fil.issueTracking.payLoad;

import lombok.Data;

@Data
public class CreateNewIssueTypeRequest {
	String name;
	String auto_accept;
}
