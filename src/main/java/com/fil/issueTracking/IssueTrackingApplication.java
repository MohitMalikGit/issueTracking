package com.fil.issueTracking;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fil.issueTracking.model.Employee;
import com.fil.issueTracking.payLoad.EmployeeDto;
import com.fil.issueTracking.security.JwtTokenHelper;

@SpringBootApplication
public class IssueTrackingApplication implements CommandLineRunner {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	JwtTokenHelper jwtTokenhelper;
	
	public static void main(String[] args) {
		SpringApplication.run(IssueTrackingApplication.class, args);
	}

	@Bean
	ModelMapper getModelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		  // Define custom mappings
        modelMapper.addMappings(new PropertyMap<Employee, EmployeeDto>() {
            @Override
            protected void configure() {
                map().setEmpId(source.getId());
                map().setDateOfJoining(source.getDob());
                
            }
        });
        return modelMapper;
	}

	@Override
	public void run(String... args) throws Exception {
		
		String encode = passwordEncoder.encode("password123");
		System.out.println(encode);
		
	}

}
