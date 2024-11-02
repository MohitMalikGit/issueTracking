package com.fil.issueTracking.service;

import java.util.List;

import com.fil.issueTracking.payLoad.EmployeeDto;
import com.fil.issueTracking.payLoad.LoginRequest;
import com.fil.issueTracking.payLoad.LoginResponse;

public interface EmployeeService {
	EmployeeDto findById(String id);
	List<EmployeeDto> findAll();
	void changePassword(String id, String password);
	LoginResponse userAuthentication(LoginRequest loginRequest);
}
