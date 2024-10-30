package com.fil.issueTracking.service;

import java.util.List;

import com.fil.issueTracking.model.Employee;

public interface EmployeeService {
	Employee findById(Integer id);
	List<Employee> findAll();
	
}
