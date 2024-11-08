package com.fil.issueTracking.serviceImpl;


import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fil.issueTracking.enums.ApprovedStatus;
import com.fil.issueTracking.enums.IssueStatus;
import com.fil.issueTracking.enums.Role;
import com.fil.issueTracking.exception.ResourceNotFoundException;
import com.fil.issueTracking.model.Employee;
import com.fil.issueTracking.model.Issue;
import com.fil.issueTracking.model.IssueType;
import com.fil.issueTracking.payLoad.GetSingleIssueApiResponse;
import com.fil.issueTracking.payLoad.PendingIssueApprovalResponse;
import com.fil.issueTracking.payLoad.UpdateIssueApprovalStatusRequest;
import com.fil.issueTracking.payLoad.UpdateIssueResponse;
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
	@Transactional
	public void createIssue(createIssueApiRequest req , String id) {
		//implemeting only for issueType where auto_accept is false
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		Optional<Employee> byId = employeeRepo.findById(id);
		Employee emp = byId.get();
		Optional<IssueType> byType = issueTypeRepo.findByType(req.getIssueType());
		IssueType issueType = byType.get();
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
		System.out.println(req.getIssueType());
		if( !issueType.getAuto_accept().equals("true"))
		issue.setStatus(IssueStatus.not_accepted);
		else {
			List<Employee> allByRole = employeeRepo.findAllByRole(Role.support);
			for( Employee e : allByRole) {
				for( IssueType it : e.getExpertise()) {
					if( it.getType().equals(req.getIssueType())) {
						issue.setStatus(IssueStatus.in_progress);
						issue.setAssignedTo(e);
						e.getAssignedIssue().add(issue);
					}
				}
			}
		}
		issueRepo.save(issue);   		
		createIssueApiResponse resp = new createIssueApiResponse();
		resp.setIssueId(issue.getId());
		resp.setIssueDescription(issue.getDescription());
		
		
	}

	
	

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
		resp.setStatus((issue.getStatus()==null)?"null":(issue.getStatus().name()));
		resp.setRemark(issue.getFeedback());
		return resp;
	}
	
	
	
	



	@Override
	@Transactional
	public void updateIssueApprovalStatus(UpdateIssueApprovalStatusRequest request,Integer issueId) {
		Optional<Issue> byId = issueRepo.findById(issueId);
		Issue issue = byId.get();
		issue.setStatus(IssueStatus.valueOf(request.getStatus()));
		issue.setApprovedBy(issue.getRaisedBy().getManager());
		if(request.getStatus().equals("in_progress")) {
			List<Employee> allByRole = employeeRepo.findAllByRole(Role.support);
			for( Employee e : allByRole) {
				for( IssueType it : e.getExpertise()) {
					if( it.getType().equals(issue.getIssueType().getType())) {
						issue.setStatus(IssueStatus.in_progress);
						issue.setAssignedTo(e);
						e.getAssignedIssue().add(issue);
					}
				}
			}
		}
		
		issue.setFeedback(request.getComment());
		
	}

	@Override
	public List<GetSingleIssueApiResponse> getAllTheIssue(Integer pageNumber, Integer pageSize, String issueType,
			String issueStatus, String assigneeId, String sortBy , String sortDir) {
		UserDetails principal = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Employee> byId = employeeRepo.findById(principal.getUsername());
		Employee emp = byId.get();
		Sort sort = (sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable p = PageRequest.of(pageNumber, pageSize ,sort);	
		Page<Issue> all = issueRepo.findAll(p);
		List<Issue> content = all.getContent();
		if(!issueType.equals("all")){
			content = content.stream().filter(i->i.getIssueType().getType().equals(issueType)).toList();
		}
		if(!issueStatus.equals("all")) {
			content = content.stream().filter(i-> i.getStatus().name().equals(issueStatus)).toList();
		}
		if(!assigneeId.equals("all")) {
			content = content.stream().filter(i -> i.getAssignedTo().getId().equals(assigneeId)).toList();
		}
		
		List<GetSingleIssueApiResponse> response = new ArrayList<>();
		for( Issue issue : content) {
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
			resp.setStatus((issue.getStatus()==null)?"null":(issue.getStatus().name()));
			resp.setRemark(issue.getFeedback());
			response.add(resp);
		}
		
		
		return response;
	}



	@Override
	public List<PendingIssueApprovalResponse> getPendingIssueApproval(String issueType, String empId, String sortBy,
			String sortDir, Integer pageNumber, Integer pageSize) {
		UserDetails principal = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Employee> byId = employeeRepo.findById(principal.getUsername());
		Employee emp = byId.get();
		Sort sort = (sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable p = PageRequest.of(pageNumber, pageSize ,sort);
		Page<Issue> allByStatus = issueRepo.findAll(p);
		List<Issue> content = allByStatus.getContent();
		content = content.stream().filter(i-> i.getStatus() == (IssueStatus.not_accepted)).toList();
		if(!issueType.equals("all")) {
			content = content.stream().filter(i-> i.getIssueType().getType().equals(issueType)).toList();
		}
		if(!empId.equals("all")) {
			content = content.stream().filter(i-> i.getRaisedBy().getId().equals(empId)).toList();
		}
		content = content.stream().filter(i->i.getRaisedBy().getManager()==emp).toList();
		List<PendingIssueApprovalResponse> response = new ArrayList<>();
		for( Issue i : content) {
			PendingIssueApprovalResponse resp = new PendingIssueApprovalResponse();
			resp.setIssueId(String.valueOf(i.getId()));
			resp.setCreatedAt(i.getCreatedAt().toLocalDateTime().toLocalDate().toString());
			resp.setDescription(i.getDescription());
			resp.setIssueType(i.getIssueType().getType());
			resp.setRaisedBy(i.getRaisedBy().getName());
			response.add(resp);
			
		}
		
		return response;
	}




	@Override
	@Transactional
	public void updateIssue(Integer issueId, String status, String assignedto ){
			Optional<Issue> byId = issueRepo.findById(issueId);
			Issue issue = byId.get();
			issue.setAssignedTo(employeeRepo.findById(assignedto).get());
			issue.setStatus(IssueStatus.valueOf(status));
			
	}








}
