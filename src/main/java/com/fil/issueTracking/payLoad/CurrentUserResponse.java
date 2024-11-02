package com.fil.issueTracking.payLoad;
//Current user profile data (empId, name, image, role, email,  dateOfBirth, dateOfJoining, gender, manager’s name)

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import com.fil.issueTracking.model.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
public class CurrentUserResponse {
	
	public CurrentUserResponse(String empId, String name, String role, String email, Date dob, Date doj, String gender) {
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
	private String role;
	private String email;
	private Date dob;
	private Date doj;
	private String gender;
	private String managerName;
	
	
}
