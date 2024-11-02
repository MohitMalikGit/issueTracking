package com.fil.issueTracking.serviceImpl;

import java.util.List;
import java.util.Optional;

import com.fil.issueTracking.exception.ArgumentTypeNotMatchedException;
import com.fil.issueTracking.exception.ResourceNotFoundException;
import com.fil.issueTracking.exception.UserNameAndPasswordNotMatchedException;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.validator.EmailValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fil.issueTracking.model.Employee;
import com.fil.issueTracking.payLoad.EmployeeDto;
import com.fil.issueTracking.payLoad.LoginRequest;
import com.fil.issueTracking.payLoad.LoginResponse;
import com.fil.issueTracking.repo.EmployeeRepo;
import com.fil.issueTracking.service.EmployeeService;
@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeRepo repo;
	@Autowired
	ModelMapper modelMapper;


	//original
	@Override
	public EmployeeDto userAuthentication(LoginRequest loginRequest) {
		String userid = loginRequest.getUserid();
		String password = loginRequest.getPassword();
		EmailValidator validator = EmailValidator.getInstance();


		if( validator.isValid(userid)) {
			Employee emp = repo.findByEmail(userid).orElseThrow(()-> new ResourceNotFoundException("User" , "Email" , userid));
			if(!emp.getPassword().equals(password)) throw new UserNameAndPasswordNotMatchedException();
			System.out.println(emp.getEmployeeType());
			return modelMapper.map(emp, EmployeeDto.class);
		}
		else {
			Employee emp = repo.findById(userid).orElseThrow(() -> new ResourceNotFoundException("User" , "id" , String.valueOf(userid) ));
			if(!emp.getPassword().equals(password)) throw new UserNameAndPasswordNotMatchedException();
			System.out.println(emp.getEmployeeType());
			return modelMapper.map(emp, EmployeeDto.class);

		}
	}


	@Override  
	public EmployeeDto findById(String id) {
		Employee employee = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User Not Found" , "userId" , String.valueOf(id)));
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
	public void changePassword(String id, String password) {
		Employee emp = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User Not Found" , "userId" , String.valueOf(id)));
		emp.setPassword(password);
		repo.save(emp);
	}




}
