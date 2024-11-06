package com.fil.issueTracking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fil.issueTracking.payLoad.GetSingleIssueApiResponse;
import com.fil.issueTracking.service.IssueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class IssueController {
	@Autowired
	IssueService service;
	@GetMapping("/api/issues")
	public ResponseEntity<GetSingleIssueApiResponse>getSingleIssue(@RequestParam Integer issueId) {
		GetSingleIssueApiResponse singleIssue = service.getSingleIssue(issueId);
		return new ResponseEntity<>(singleIssue , HttpStatus.ACCEPTED);
		
	}
	
}
