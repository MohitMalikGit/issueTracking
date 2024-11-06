package com.fil.issueTracking.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fil.issueTracking.config.AppConstants;
import com.fil.issueTracking.payLoad.AllUserApiResponse;
import com.fil.issueTracking.payLoad.CurrentUserResponse;
import com.fil.issueTracking.payLoad.EmployeeDto;
import com.fil.issueTracking.payLoad.EmployeePasswordChangeDto;
import com.fil.issueTracking.payLoad.LoginRequest;
import com.fil.issueTracking.service.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class EmployeeController {
	@Autowired 
	EmployeeService service;
	
	
	@GetMapping("/api/users/me")
	public ResponseEntity<CurrentUserResponse> currentUser(@RequestParam String id) {
		return  new ResponseEntity<>( service.findCurrentUser(id) , HttpStatus.OK);
	}  
	
	@GetMapping("/api/users")
	public AllUserApiResponse getAllUsers(@RequestParam(value="pageNumber" , defaultValue = AppConstants.page_number, required=false)Integer pageNumber, @RequestParam(value="pageSize" ,defaultValue = AppConstants.page_size , required=false)Integer pageSize,
			@RequestParam(value="sortBy",defaultValue = AppConstants.sort_by, required=false)String sortBy ,@RequestParam(value="sortDir",defaultValue = AppConstants.sort_dir,required = false)String sortDir) {
		return service.getAllUsers(pageNumber,pageSize,sortBy,sortDir);
	}
	

 
	//mine
	@PostMapping("/user/password")
	public String postMethodName(@RequestBody EmployeePasswordChangeDto empDto) {
		service.changePassword(empDto.getId(), empDto.getNewPassword());
		return "password changed successfully";
	}
	
	


}
