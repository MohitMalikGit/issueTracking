package com.fil.issueTracking.serviceImpl;


import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fil.issueTracking.enums.ApprovedStatus;
import com.fil.issueTracking.enums.IssueStatus;
import com.fil.issueTracking.exception.ResourceNotFoundException;
import com.fil.issueTracking.model.Employee;
import com.fil.issueTracking.model.Issue;
import com.fil.issueTracking.model.IssueType;
import com.fil.issueTracking.payLoad.GetSingleIssueApiResponse;
import com.fil.issueTracking.payLoad.createIssueApiRequest;
import com.fil.issueTracking.payLoad.createIssueApiResponse;
import com.fil.issueTracking.repo.EmployeeRepo;
import com.fil.issueTracking.repo.IssueRepo;
import com.fil.issueTracking.repo.IssueTypeRepo;
import com.fil.issueTracking.security.JwtTokenHelper;
import com.fil.issueTracking.service.IssueService;

@Service
public class IssueServiceImpl implements IssueService {

	@Autowired
	IssueRepo issueRepo;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	IssueTypeRepo issueTypeRepo;
	@Autowired
	JwtTokenHelper jwtTokenhelper;
	@Autowired
	EmployeeRepo employeeRepo;


	@Override
	public GetSingleIssueApiResponse getSingleIssue(Integer id) {
		UserDetails principal = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Employee> byId = employeeRepo.findById(principal.getUsername());
		Employee emp = byId.get();
		Issue issue = issueRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Issue" , "IssueId", String.valueOf(id)));
		GetSingleIssueApiResponse resp = new GetSingleIssueApiResponse();
		resp.setIssueId(issue.getId());
		resp.setType(issue.getIssueType().getType());
		resp.setTitle(issue.getTitle());
		resp.setDescription(issue.getDescription());
		Map<String,String> assignee = new HashMap<>();
		if(issue.getAssignedTo()!=null) {
			assignee.put("name", issue.getAssignedTo().getName());
			if(emp.getRole().name().equals("manager")) {
				assignee.put("id", issue.getAssignedTo().getId());
			}
		}
		resp.setAssignee(assignee);
		Map<String,String> raisedBy = new HashMap<>();
		raisedBy.put("name", issue.getRaisedBy().getName());
		raisedBy.put("id", issue.getRaisedBy().getId());
		resp.setRaisedBy(raisedBy);
		resp.setCreated(issue.getCreatedAt().toLocalDateTime().toLocalDate());
		resp.setUpdated(issue.getUpdatedAt().toLocalDateTime().toLocalDate());
		resp.setRemark(issue.getFeedback());
		return resp;
	}

	@Override
	public void createIssue(createIssueApiRequest req , String id) {
		//implemeting only for issueType where auto_accept is false
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		Optional<Employee> byId = employeeRepo.findById(id);
		Employee emp = byId.get();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(currentTimestamp);
		currentTimestamp = Timestamp.valueOf(currentTime);		
		Issue issue = new Issue();
		issue.setIssueType(issueTypeRepo.findByType(req.getIssueType()).orElseThrow(() -> new ResourceNotFoundException("IssueType" , "IssueName" , req.getIssueType())));
		issue.setTitle(req.getTitle());
		issue.setDescription(req.getDescription());
		issue.setRaisedBy(emp);
		issue.setCreatedAt(currentTimestamp);
		issue.setUpdatedAt(currentTimestamp);
		issue.setStatus(IssueStatus.in_progess);
		issue.setApprovedStatus(ApprovedStatus.NotApproved);
		issueRepo.save(issue);   		
		createIssueApiResponse resp = new createIssueApiResponse();
		resp.setIssueId(issue.getId());
		resp.setIssueDescription(issue.getDescription());
	}








}
