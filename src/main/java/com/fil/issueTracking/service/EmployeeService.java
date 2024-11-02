package com.fil.issueTracking.service;

import java.util.List;

import com.fil.issueTracking.payLoad.CurrentUserResponse;
import com.fil.issueTracking.payLoad.EmployeeDto;
import com.fil.issueTracking.payLoad.LoginRequest;
import com.fil.issueTracking.payLoad.LoginResponse;

public interface EmployeeService {
	CurrentUserResponse findCurrentUser(String id);
	List<EmployeeDto> findAll();
	void changePassword(String id, String password);
	EmployeeDto userAuthentication(LoginRequest loginRequest);
}
