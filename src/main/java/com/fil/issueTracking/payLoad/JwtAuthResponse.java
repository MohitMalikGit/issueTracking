package com.fil.issueTracking.payLoad;

import lombok.Data;

@Data
public class JwtAuthResponse {
	private String token;
	private String role;
	private String managerId;
	private String username;
	private String empId;
}
