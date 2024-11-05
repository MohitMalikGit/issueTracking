package com.fil.issueTracking.payLoad;

import lombok.Data;

@Data
public class JwtAuthRequest {
	private String username;
	private String password;
}
