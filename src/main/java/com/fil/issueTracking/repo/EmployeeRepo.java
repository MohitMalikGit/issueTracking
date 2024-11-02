package com.fil.issueTracking.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fil.issueTracking.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, String> {
	
	Optional<Employee> findByEmail(String email);

	

	
}
