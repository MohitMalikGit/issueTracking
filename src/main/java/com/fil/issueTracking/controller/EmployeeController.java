package com.fil.issueTracking.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fil.issueTracking.config.AppConstants;
import com.fil.issueTracking.exception.UserNameAndPasswordNotMatchedException;
import com.fil.issueTracking.model.Employee;
import com.fil.issueTracking.payLoad.AllUserApiResponse;
import com.fil.issueTracking.payLoad.AssigneeResponseDto;
import com.fil.issueTracking.payLoad.CurrentUserResponse;
import com.fil.issueTracking.payLoad.EmployeeDto;
import com.fil.issueTracking.payLoad.EmployeePasswordChangeDto;
import com.fil.issueTracking.payLoad.GetEmployeesResponse;
import com.fil.issueTracking.payLoad.LoginRequest;
import com.fil.issueTracking.payLoad.UpdateUserDetailRequest;
import com.fil.issueTracking.payLoad.UpdateUserDetailResponse;
import com.fil.issueTracking.repo.EmployeeRepo;
import com.fil.issueTracking.service.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@CrossOrigin(origins = "*")
public class EmployeeController {
	@Autowired 
	EmployeeService service;
	@Autowired
	EmployeeRepo employeeRepo;

	@GetMapping("/api/users/{empId}")
	public ResponseEntity<CurrentUserResponse> currentUser(@PathParam(value="empId") String empId) {

		UserDetails principal = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(principal);

		return  new ResponseEntity<>( service.findCurrentUser(empId) , HttpStatus.OK);


	}  

	@GetMapping("/api/users")
	public AllUserApiResponse getAllUsers(@RequestParam(value="role")String role,@RequestParam(value="page" , defaultValue = AppConstants.page_number, required=false)Integer pageNumber, @RequestParam(value="limit" ,defaultValue = AppConstants.page_size , required=false)Integer pageSize,
			@RequestParam(value="sortBy",defaultValue = AppConstants.sort_by, required=false)String sortBy ,@RequestParam(value="order",defaultValue = AppConstants.sort_dir,required = false)String sortDir , @RequestParam(value="gender") String gender) {
		
		UserDetails principal = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Employee> byId = employeeRepo.findById(principal.getUsername());
		Employee emp = byId.get();
		if( !emp.getRole().name().equals("manager")) throw new UserNameAndPasswordNotMatchedException();
		
		return service.getAllUsers(role,pageNumber,pageSize,sortBy,sortDir,gender);
	}



	//mine
	@PostMapping("/user/password")
	public String postMethodName(@RequestBody EmployeePasswordChangeDto empDto) {
		service.changePassword(empDto.getId(), empDto.getNewPassword());
		return "password changed successfully";
	}

	@GetMapping("/api/assignees")
	public ResponseEntity<List<AssigneeResponseDto>> getAllAssignees() {
		UserDetails principal = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Employee> byId = employeeRepo.findById(principal.getUsername());
		Employee emp = byId.get();
		if( !emp.getRole().name().equals("manager")) throw new UserNameAndPasswordNotMatchedException();
		List<AssigneeResponseDto> allAssignee = service.getAllAssignee();

		return ResponseEntity.ok(allAssignee);
	}
	
	
	@PutMapping("/api/users/{userId}")
	public ResponseEntity<UpdateUserDetailResponse> updateUserDetail(@RequestBody UpdateUserDetailRequest request , @PathParam("userId") String userId) {		
		return ResponseEntity.ok(service.updateUserDetails(userId,request.getRole(),request.getGender(),request.getManagerId()));
	}

	
	@GetMapping("/api/employees")
	public ResponseEntity<List<GetEmployeesResponse>> getEmployees() {
		
		return ResponseEntity.ok(service.getAllEmployees());
	}


}
