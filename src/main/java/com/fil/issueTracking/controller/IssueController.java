package com.fil.issueTracking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fil.issueTracking.model.Employee;
import com.fil.issueTracking.payLoad.GetSingleIssueApiResponse;
import com.fil.issueTracking.payLoad.createIssueApiRequest;
import com.fil.issueTracking.payLoad.createIssueApiResponse;
import com.fil.issueTracking.service.IssueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class IssueController {
	@Autowired
	IssueService service;
	@GetMapping("/api/issues")
	public ResponseEntity<GetSingleIssueApiResponse>getSingleIssue(@RequestParam Integer issueId) {
		GetSingleIssueApiResponse singleIssue = service.getSingleIssue(issueId);
		return new ResponseEntity<>(singleIssue , HttpStatus.ACCEPTED);
		
	}
	
	@PostMapping("/api/issues")
	public HttpStatus createIssue(@RequestBody createIssueApiRequest req) {

		 User details = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 System.out.println(details);
		 service.createIssue(req, details.getUsername());
		 return HttpStatus.ACCEPTED;
	}
	
	
}
