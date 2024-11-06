package com.fil.issueTracking.security;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fil.issueTracking.exception.ResourceNotFoundException;
import com.fil.issueTracking.model.Employee;
import com.fil.issueTracking.repo.EmployeeRepo;

@Service
public class EmployeeUserDetailsService implements UserDetailsService {
	@Autowired
	EmployeeRepo repo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee = repo.findById(username).orElseThrow(()-> new ResourceNotFoundException("username", "userid", username));
		List<GrantedAuthority> authorities = new ArrayList<>();
		return new org.springframework.security.core.userdetails.User(employee.getId(), employee.getPassword(), authorities);
		
	}

}
