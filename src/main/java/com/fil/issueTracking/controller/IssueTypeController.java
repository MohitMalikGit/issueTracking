package com.fil.issueTracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fil.issueTracking.payLoad.CreateNewIssueTypeResponse;
import com.fil.issueTracking.service.CategoryApiResponse;
import com.fil.issueTracking.service.IssueTypeService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@CrossOrigin(origins = "*")
public class IssueTypeController {
	@Autowired
	IssueTypeService service;
	@GetMapping("/api/categories")
	public ResponseEntity<CategoryApiResponse> getAllCategories() {
		return ResponseEntity.ok(service.findAllCategory());
		
	}
	
	@PutMapping("/api/issues/type")
	public ResponseEntity<CreateNewIssueTypeResponse> createIssueType(@RequestParam("name") String name , @RequestParam("auto_accept") String auto_accept) {
		//TODO: process PUT request
		
		return ResponseEntity.ok(service.createNewIssue(name , auto_accept));
	}
	
}
