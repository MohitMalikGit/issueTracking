package com.fil.issueTracking.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fil.issueTracking.model.IssueType;
import com.fil.issueTracking.payLoad.CreateNewIssueTypeResponse;
import com.fil.issueTracking.repo.IssueTypeRepo;
import com.fil.issueTracking.service.CategoryApiResponse;
import com.fil.issueTracking.service.IssueTypeService;
@Service
public class IssueTypeServiceImpl implements IssueTypeService {
	@Autowired
	IssueTypeRepo repo;
	@Override
	public CategoryApiResponse findAllCategory() {
		List<IssueType> all = repo.findAll();
		List<String> collect = all.stream().map(issueType -> issueType.getType()).collect(Collectors.toList());
		CategoryApiResponse resp = new CategoryApiResponse();
		resp.setIssueType(collect);
		return resp;
	}
	@Override
	@Transactional
	public CreateNewIssueTypeResponse createNewIssue(String name, String auto_accept) {
		IssueType issueType = new IssueType();
		issueType.setAuto_accept(auto_accept);
		issueType.setType(name);
		repo.save(issueType);
		return new CreateNewIssueTypeResponse(name);
	}

}
