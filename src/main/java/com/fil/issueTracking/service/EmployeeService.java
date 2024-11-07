package com.fil.issueTracking.service;


import java.util.List;

import com.fil.issueTracking.payLoad.AllUserApiResponse;
import com.fil.issueTracking.payLoad.AssigneeResponseDto;
import com.fil.issueTracking.payLoad.CurrentUserResponse;
import com.fil.issueTracking.payLoad.EmployeeDto;
import com.fil.issueTracking.payLoad.LoginRequest;
import com.fil.issueTracking.payLoad.UpdateIssueApprovalStatusRequest;
import com.fil.issueTracking.payLoad.UpdateUserDetailResponse;

public interface EmployeeService {
	CurrentUserResponse findCurrentUser(String id);
	AllUserApiResponse getAllUsers(String role,Integer pageNumber , Integer pageSize,String sortBy,String sortDir,String gender);
	void changePassword(String id, String password);
	EmployeeDto userAuthentication(LoginRequest loginRequest);
	List<AssigneeResponseDto> getAllAssignee();
	UpdateUserDetailResponse updateUserDetails(String empId,String role, String gender, String managerId);
	

}
