package com.fil.issueTracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fil.issueTracking.service.CategoryApiResponse;
import com.fil.issueTracking.service.IssueTypeService;


@RestController
@CrossOrigin(origins = "*")
public class IssueTypeController {
	@Autowired
	IssueTypeService service;
	@GetMapping("/api/categories")
	public ResponseEntity<CategoryApiResponse> getAllCategories() {
		return ResponseEntity.ok(service.findAllCategory());
		
	}
	
}
