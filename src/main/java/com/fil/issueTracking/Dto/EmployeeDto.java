package com.fil.issueTracking.Dto;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDto {
	private int id;
	private String name;
	private String email;
	private Date dob;
	private Date doj;
	
	
	
}
