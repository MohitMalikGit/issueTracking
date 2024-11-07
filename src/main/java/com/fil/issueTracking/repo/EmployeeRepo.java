package com.fil.issueTracking.repo;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fil.issueTracking.enums.Role;
import com.fil.issueTracking.model.Employee;
import com.fil.issueTracking.model.IssueType;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, String> {
	
	Optional<Employee> findByEmail(String email);
	List<Employee> findAllByRole(Role role);
	Page<Employee> findAllByRole(Role role ,Pageable p);

	
	 
	
	
}
