package com.fil.issueTracking.serviceImpl;

import java.util.List;
import com.fil.issueTracking.exception.ResourceNotFoundException;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fil.issueTracking.Dto.EmployeeDto;
import com.fil.issueTracking.model.Employee;
import com.fil.issueTracking.repo.EmployeeRepo;
import com.fil.issueTracking.service.EmployeeService;
@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeRepo repo;
	@Override
	public EmployeeDto findById(Integer id) {
		Employee employee = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User Not Found" , "userId" , id));
		EmployeeDto employeeDto = employeeToemployeeDto(employee);
		return employeeDto;
	}
	@Override
	public List<EmployeeDto> findAll() {
		List<Employee> emplyeeList = repo.findAll();
		return emplyeeList.stream().map(emp-> employeeToemployeeDto(emp)).collect(Collectors.toList());
	}
	

	EmployeeDto employeeToemployeeDto( Employee e){
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId(e.getId());
		employeeDto.setDob(e.getDob());
		employeeDto.setName(e.getName());
		employeeDto.setEmail(e.getEmail());
		employeeDto.setDoj(e.getDoj());
		return employeeDto;
	}
	@Override
	@Transactional
	public void changePassword(int id, String password) {
		Employee emp = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User Not Found" , "userId" , id));
		emp.setPassword(password);
		repo.save(emp);
	}

}
