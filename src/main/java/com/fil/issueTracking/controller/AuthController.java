package com.fil.issueTracking.controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fil.issueTracking.exception.UserNameAndPasswordNotMatchedException;
import com.fil.issueTracking.model.Employee;
import com.fil.issueTracking.payLoad.JwtAuthRequest;
import com.fil.issueTracking.payLoad.JwtAuthResponse;
import com.fil.issueTracking.repo.EmployeeRepo;
import com.fil.issueTracking.security.JwtTokenHelper;
import com.fil.issueTracking.service.EmployeeService;

@RestController
public class AuthController {
	

	@Autowired 
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	@Autowired
	private ModelMapper mapper;
	
	@PostMapping("/api/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetails);
		Optional<Employee> empOpt = employeeRepo.findById(userDetails.getUsername());
		Employee emp = empOpt.get();
		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		response.setRole(emp.getRole().name());
		response.setManagerId(emp.getManager()==null?"null":(emp.getManager().getId()));
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);

		try {

			this.authenticationManager.authenticate(authenticationToken);

		} catch (BadCredentialsException e) {
			System.out.println("Invalid Detials !!");
			throw new UserNameAndPasswordNotMatchedException();
		}

	}
	
}
