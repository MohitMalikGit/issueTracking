package com.fil.issueTracking.service;


import java.util.List;

import com.fil.issueTracking.payLoad.GetSingleIssueApiResponse;
import com.fil.issueTracking.payLoad.PendingIssueApprovalResponse;
import com.fil.issueTracking.payLoad.UpdateIssueApprovalStatusRequest;
import com.fil.issueTracking.payLoad.UpdateIssueResponse;
import com.fil.issueTracking.payLoad.createIssueApiRequest;
import com.fil.issueTracking.payLoad.createIssueApiResponse;
public interface IssueService {
	GetSingleIssueApiResponse getSingleIssue(Integer id);
	void createIssue(createIssueApiRequest req , String id);
	List<GetSingleIssueApiResponse> getAllTheIssue(Integer pageNumber, Integer pageSize, String issueType,
			String issueStatus, String assigneeId, String sortBy, String sortOrder);
	void updateIssueApprovalStatus(UpdateIssueApprovalStatusRequest request , Integer issueId);
	List<PendingIssueApprovalResponse> getPendingIssueApproval(String category, String employee, String sortBy, String order, Integer page,
			Integer limit);
	void updateIssue(Integer issueId, String status, String assignedto);
}
