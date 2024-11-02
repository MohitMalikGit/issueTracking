package com.fil.issueTracking.service;

import java.util.List;


import com.fil.issueTracking.Dto.EmployeeDto;

public interface EmployeeService {
	EmployeeDto findById(Integer id);
	List<EmployeeDto> findAll();
	void changePassword(int id, String password);
	
}
