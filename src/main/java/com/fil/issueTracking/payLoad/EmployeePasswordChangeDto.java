package com.fil.issueTracking.payLoad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePasswordChangeDto {
	private String id;
	private String newPassword;
	
}
