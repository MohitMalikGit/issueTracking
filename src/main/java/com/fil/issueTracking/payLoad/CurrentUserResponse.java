package com.fil.issueTracking.payLoad;
//Current user profile data (empId, name, image, role, email,  dateOfBirth, dateOfJoining, gender, manager’s name)

import java.sql.Date;


import com.fil.issueTracking.enums.Gender;
import com.fil.issueTracking.enums.Role;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CurrentUserResponse {
	
	public CurrentUserResponse(String empId, String name, Role role, String email, Date dob, Date doj, Gender gender) {
		super();
		this.empId = empId;
		this.name = name;
		this.role = role;
		this.email = email;
		this.dob = dob;
		this.doj = doj;
		this.gender = gender;
		
	}
	

	private String empId;
	private String name;
	private Role role;
	private String email;
	private Date dob;
	private Date doj;
	private Gender gender;
	private String managerName;
	
	
}
