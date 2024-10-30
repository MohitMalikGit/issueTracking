package com.fil.issueTracking.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fil.issueTracking.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

	
	
}
