package com.fil.issueTracking.payLoad;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;
@Data
public class GetSingleIssueApiResponse {
	int issueId;
	String type;
	String title;
	String description;
	String status;
	Map<String,String> assignee = new HashMap<>();
	Map<String,String> raisedBy = new HashMap<>();
	Timestamp created;
	Timestamp updated;
	String remark;
	
}
