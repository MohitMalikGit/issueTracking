package com.fil.issueTracking.service;

import org.springframework.stereotype.Service;

import com.fil.issueTracking.model.Employee;
import com.fil.issueTracking.model.Issue;
import com.fil.issueTracking.payLoad.GetSingleIssueApiResponse;
import com.fil.issueTracking.payLoad.createIssueApiRequest;
import com.fil.issueTracking.payLoad.createIssueApiResponse;
public interface IssueService {
	GetSingleIssueApiResponse getSingleIssue(Integer id);
	createIssueApiResponse createIssue(createIssueApiRequest req , String id);
}
