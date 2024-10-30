package com.fil.issueTracking.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fil.issueTracking.model.Employee;
import com.fil.issueTracking.service.EmployeeService;

@RestController
public class EmployeeController {
	@Autowired 
	EmployeeService service;
	@GetMapping("/user")
	public List<Employee> FindAllEmployee(ModelMap map) {
		return service.findAll();
	}
	
	@GetMapping("/user/{id}")
	public Employee getMethodName(@PathVariable(name="id")Integer id , ModelMap map) {
		return service.findById(id);
	}  
	
	
	
	@ExceptionHandler(NoSuchElementException.class)
	public String NoSuchElementExceptionHandler() {
		System.out.println("passed through exception handler");
		return "The requested Employee/s is/are not present";
	}
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public String ArgumentMisMatchExceptionHandler() {
		System.out.println("passed through exception handler");
		return "Some Argument type has been mismatched please provide correct argument type";
	}
	
	
}
