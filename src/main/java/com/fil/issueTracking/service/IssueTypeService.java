package com.fil.issueTracking.service;

import com.fil.issueTracking.payLoad.CreateNewIssueTypeResponse;

public interface IssueTypeService {
	CategoryApiResponse findAllCategory();

	CreateNewIssueTypeResponse createNewIssue(String name, String auto_accept);
}
