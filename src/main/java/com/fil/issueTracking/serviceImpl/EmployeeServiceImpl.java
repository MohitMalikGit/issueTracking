package com.fil.issueTracking.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fil.issueTracking.model.Employee;
import com.fil.issueTracking.repo.EmployeeRepo;
import com.fil.issueTracking.service.EmployeeService;
@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeRepo repo;
	@Override
	public Employee findById(Integer id) {
		Optional<Employee> employee = repo.findById(id);
		return employee.get();
	}
	@Override
	public List<Employee> findAll() {
		return repo.findAll();
	}

}
