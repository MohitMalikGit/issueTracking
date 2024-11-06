package com.fil.issueTracking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fil.issueTracking.model.Issue;

@Repository
public interface IssueRepo extends JpaRepository<Issue, Integer> {
	
}
