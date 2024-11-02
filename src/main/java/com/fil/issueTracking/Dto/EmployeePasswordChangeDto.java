package com.fil.issueTracking.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePasswordChangeDto {
	private int id;
	private String newPassword;
	
}
