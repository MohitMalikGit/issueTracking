package com.fil.issueTracking.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fil.issueTracking.enums.IssueStatus;
import com.fil.issueTracking.model.Issue;

@Repository
public interface IssueRepo extends JpaRepository<Issue, Integer> {
	
	Page<Issue>findAll(Pageable p);
}
