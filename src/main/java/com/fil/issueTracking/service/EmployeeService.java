package com.fil.issueTracking.service;

import java.util.List;

import com.fil.issueTracking.payLoad.AllUserApiResponse;
import com.fil.issueTracking.payLoad.CurrentUserResponse;
import com.fil.issueTracking.payLoad.EmployeeDto;
import com.fil.issueTracking.payLoad.LoginRequest;
import com.fil.issueTracking.payLoad.LoginResponse;

public interface EmployeeService {
	CurrentUserResponse findCurrentUser(String id);
	AllUserApiResponse getAllUsers(Integer pageNumber , Integer pageSize,String sortBy,String sortDir);
	void changePassword(String id, String password);
	EmployeeDto userAuthentication(LoginRequest loginRequest);
}
