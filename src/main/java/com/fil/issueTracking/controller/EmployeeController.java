package com.fil.issueTracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fil.issueTracking.payLoad.EmployeeDto;
import com.fil.issueTracking.payLoad.EmployeePasswordChangeDto;
import com.fil.issueTracking.service.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class EmployeeController {
	@Autowired 
	EmployeeService service;
	
	
	@GetMapping("/user")
	public List<EmployeeDto> FindAllEmployee() {
		return service.findAll();
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<EmployeeDto> getMethodName(@PathVariable(name="id")Integer id) {
		return  new ResponseEntity<>( service.findById(id) , HttpStatus.OK);
	}  
 

	@PostMapping("/user/password")
	public String postMethodName(@RequestBody EmployeePasswordChangeDto empDto) {
		service.changePassword(empDto.getId(), empDto.getNewPassword());
		return "password changed successfully";
	}
	
	


}
