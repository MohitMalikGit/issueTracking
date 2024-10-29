package com.fil.issueTracking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fil.issueTracking.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
	
//	Employee findEmployeeById(Integer id);
//	@Query("select e from Employee e where e.name = :name")
//	List<Employee> findEmployeeByName(String name);
//	@Query("select e from Employee e where e.employeeType.type = :type")
//	List<Employee> findEmployeeByEmployeeType( String type);
//	@Query("select e from Employee e where e.expertise.type = :expertise")
//	List<Employee> findEmployeeByExpertise(String expertise);
	
}
