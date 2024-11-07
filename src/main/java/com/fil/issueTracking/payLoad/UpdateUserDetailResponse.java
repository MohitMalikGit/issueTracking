package com.fil.issueTracking.payLoad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDetailResponse {
	String empId,name,email,role,gender,dateOfJoining;
}
