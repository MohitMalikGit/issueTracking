package com.fil.issueTracking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fil.issueTracking.model.Employee;
import com.fil.issueTracking.payLoad.GetSingleIssueApiResponse;
import com.fil.issueTracking.payLoad.PendingIssueApprovalResponse;
import com.fil.issueTracking.payLoad.UpdateIssueApprovalStatusRequest;
import com.fil.issueTracking.payLoad.UpdateIssueRequest;
import com.fil.issueTracking.payLoad.UpdateIssueResponse;
import com.fil.issueTracking.payLoad.UpdateUserDetailRequest;
import com.fil.issueTracking.payLoad.UpdateUserDetailResponse;
import com.fil.issueTracking.payLoad.createIssueApiRequest;
import com.fil.issueTracking.payLoad.createIssueApiResponse;
import com.fil.issueTracking.service.IssueService;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@CrossOrigin(origins = "*")
public class IssueController {
	@Autowired
	IssueService service;
	@GetMapping("/api/issues/{issueId}")
	public ResponseEntity<GetSingleIssueApiResponse>getSingleIssue(@PathParam(value = "issueId") Integer issueId) {
		GetSingleIssueApiResponse singleIssue = service.getSingleIssue(issueId);
		return new ResponseEntity<>(singleIssue , HttpStatus.ACCEPTED);

	}
	
	@GetMapping("/api/issues")
	public ResponseEntity<List<GetSingleIssueApiResponse>>getAllIssue(@RequestParam(value="page")Integer pageNumber,@RequestParam(value="limit")Integer pageSize,
			@RequestParam(value="category")String issueType,@RequestParam(value="status") String issueStatus,@RequestParam(value="assignee") String assigneeId
			,@RequestParam String sortBy , @RequestParam String sortOrder) {
		
		List<GetSingleIssueApiResponse> allTheIssue = service.getAllTheIssue(pageNumber, pageSize, issueType, issueStatus, assigneeId, sortBy,sortOrder);
		
		return ResponseEntity.ok(allTheIssue);
	}

	@PostMapping("/api/issues")
	public HttpStatus createIssue(@RequestBody createIssueApiRequest req) {

		User details = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(req.getIssueType());
		service.createIssue(req, details.getUsername());
		return HttpStatus.ACCEPTED;
	}
	
	@PostMapping("/api/approvalIssue/{issueId}")
	public HttpStatus updateIssue( @PathParam(value="issueId")Integer issueId ,@RequestBody UpdateIssueApprovalStatusRequest request) {
		service.updateIssueApprovalStatus(request, issueId);
		return HttpStatus.ACCEPTED;
	}
	
	
	@GetMapping("/api/approvalIssues")
	public ResponseEntity<List<PendingIssueApprovalResponse>> getMethodName(@RequestParam String category,@RequestParam String employee , @RequestParam String sortBy,
			@RequestParam String order ,@RequestParam Integer page,@RequestParam Integer limit) {
		
		return ResponseEntity.ok(service.getPendingIssueApproval(category,employee,sortBy,order,page,limit));
	}
	
	
	@PutMapping("/api/issues/{issueId}")
	public HttpStatus updateIssuess(@PathParam(value = "issueId") String issueId, @RequestBody UpdateIssueRequest req) {
		System.out.println(issueId + " " + "hi");
		service.updateIssue(Integer.parseInt(issueId), req.getStatus(),req.getAssigneeId());
		return HttpStatus.OK;
	}
	
	
	




}







