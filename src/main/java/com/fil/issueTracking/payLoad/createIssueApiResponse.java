package com.fil.issueTracking.payLoad;

import lombok.Data;

@Data
public class createIssueApiResponse {
	int issueId;
	String issueDescription;
}
