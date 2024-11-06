package com.fil.issueTracking.service;


import com.fil.issueTracking.payLoad.GetSingleIssueApiResponse;
import com.fil.issueTracking.payLoad.createIssueApiRequest;
import com.fil.issueTracking.payLoad.createIssueApiResponse;
public interface IssueService {
	GetSingleIssueApiResponse getSingleIssue(Integer id);
	createIssueApiResponse createIssue(createIssueApiRequest req , String id);
}
