package com.fil.issueTracking.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fil.issueTracking.model.IssueType;

@Repository
public interface IssueTypeRepo extends JpaRepository<IssueType, Integer> {
	
	Optional<IssueType> findByType(String type);
}
