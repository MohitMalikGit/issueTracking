package com.fil.issueTracking.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fil.issueTracking.payLoad.CurrentUserResponse;
import com.fil.issueTracking.payLoad.EmployeeDto;
import com.fil.issueTracking.payLoad.EmployeePasswordChangeDto;
import com.fil.issueTracking.payLoad.LoginRequest;
import com.fil.issueTracking.payLoad.LoginResponse;
import com.fil.issueTracking.service.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class EmployeeController {
	@Autowired 
	EmployeeService service;
	
	
	//original
	@PostMapping("/api/login")
	public ResponseEntity<EmployeeDto> authentication(@Valid @RequestBody LoginRequest loginRequest) {
		EmployeeDto employeeDto = service.userAuthentication(loginRequest);
		return new ResponseEntity<>(employeeDto , HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
//	//mine
//	@GetMapping("/user")
//	public List<EmployeeDto> findAllEmployee() {
//		return service.findAll();
//	}
//
	@GetMapping("/api/users/me")
	public ResponseEntity<CurrentUserResponse> currentUser(@RequestParam String id) {
		System.out.println(id);
		return  new ResponseEntity<>( service.findCurrentUser(id) , HttpStatus.OK);
	}  
// 
//
//	@PostMapping("/user/password")
//	public String postMethodName(@RequestBody EmployeePasswordChangeDto empDto) {
//		service.changePassword(empDto.getId(), empDto.getNewPassword());
//		return "password changed successfully";
//	}
	
	


}
