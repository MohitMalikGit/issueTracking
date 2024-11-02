package com.fil.issueTracking.serviceImpl;

import java.util.List;

import com.fil.issueTracking.exception.ResourceNotFoundException;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fil.issueTracking.model.Employee;
import com.fil.issueTracking.payLoad.EmployeeDto;
import com.fil.issueTracking.repo.EmployeeRepo;
import com.fil.issueTracking.service.EmployeeService;
@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeRepo repo;
	@Autowired
	ModelMapper modelMapper;
	
	 
	@Override  
	public EmployeeDto findById(Integer id) {
		Employee employee = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User Not Found" , "userId" , id));
		EmployeeDto employeeDto = modelMapper.map(employee , EmployeeDto.class);
		return employeeDto; 
	}
	@Override
	public List<EmployeeDto> findAll() {
		List<Employee> emplyeeList = repo.findAll();
		return emplyeeList.stream().map(emp-> modelMapper.map(emp, EmployeeDto.class)).collect(Collectors.toList());
	}
	

	@Override
	@Transactional
	public void changePassword(int id, String password) {
		Employee emp = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User Not Found" , "userId" , id));
		emp.setPassword(password);
		repo.save(emp);
	}

}
