package com.fil.issueTracking.payLoad;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


import com.fil.issueTracking.enums.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDto {
	@NotEmpty
	private String empId;
	@NotEmpty
	private String name;
	@Email
	private String email;
	@NotEmpty
	private Date dateOfJoining;
	private String gender;
	private String ManagerId;
	private Role role;
	
	
	
}
