package com.fil.issueTracking.payLoad;

import lombok.Data;

@Data
public class createIssueApiRequest {
	private String title;
	private String description;
	private String issueType;
}
