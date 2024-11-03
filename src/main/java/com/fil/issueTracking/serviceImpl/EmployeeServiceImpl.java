package com.fil.issueTracking.serviceImpl;

import java.util.List;
import java.util.Optional;

import com.fil.issueTracking.exception.ArgumentTypeNotMatchedException;
import com.fil.issueTracking.exception.ResourceNotFoundException;
import com.fil.issueTracking.exception.UserNameAndPasswordNotMatchedException;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.validator.EmailValidator;
import org.hibernate.internal.build.AllowSysOut;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fil.issueTracking.model.Employee;
import com.fil.issueTracking.payLoad.AllUserApiResponse;
import com.fil.issueTracking.payLoad.CurrentUserResponse;
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
			return modelMapper.map(emp, EmployeeDto.class);
		}
		else {
			Employee emp = repo.findById(userid).orElseThrow(() -> new ResourceNotFoundException("User" , "id" , String.valueOf(userid) ));
			if(!emp.getPassword().equals(password)) throw new UserNameAndPasswordNotMatchedException();
			return modelMapper.map(emp, EmployeeDto.class);

		}
	}
	
	
	
	 


	@Override  
	public CurrentUserResponse findCurrentUser(String id) {
		Employee emp = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User Not Found" , "userId" , String.valueOf(id)));
		CurrentUserResponse resp = new CurrentUserResponse(emp.getId(), emp.getName(),emp.getRole().getRole(),emp.getEmail(),emp.getDob(),emp.getDoj(),emp.getGender());
		if(emp.getManager()!=null)resp.setManagerName(emp.getManager().getName());
		return resp; 
	}
	
	
	
	@Override
	public AllUserApiResponse getAllUsers(Integer pageNumber , Integer pageSize) {
		System.out.println(pageNumber+" "+pageSize);
		Pageable p = PageRequest.of(pageNumber, pageSize);		
		Page<Employee> pageUser = repo.findAll(p);
		List<EmployeeDto> empDtoList = pageUser.getContent().stream().map(emp-> modelMapper.map(emp , EmployeeDto.class)).collect(Collectors.toList());
		AllUserApiResponse resp = new AllUserApiResponse();
		resp.setContent(empDtoList);
		resp.setPageNumber(pageUser.getNumber()); 
		resp.setPageSize(pageUser.getSize());
		resp.setTotalElements(pageUser.getTotalElements());
		resp.setLastPage(pageUser.isLast()); 
		return resp; 
		
	}


	@Override 
	@Transactional
	public void changePassword(String id, String password) {
		Employee emp = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User Not Found" , "userId" , String.valueOf(id)));
		emp.setPassword(password);
		repo.save(emp);
	}




}
