package com.fil.issueTracking.payLoad;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDto {
	@NotEmpty
	private String id;
	@NotEmpty
	private String name;
	@Email
	private String email;
	@NotEmpty
	private Date dob;
	@NotEmpty
	private Date doj;
	private Integer ManagerId;
	
	
	
}
