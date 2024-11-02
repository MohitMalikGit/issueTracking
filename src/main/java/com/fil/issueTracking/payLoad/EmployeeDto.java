package com.fil.issueTracking.payLoad;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDto {
	private int id;
	@NotNull
	private String name;
	@Email
	private String email;
	private Date dob;
	private Date doj;
	
	
	
}
