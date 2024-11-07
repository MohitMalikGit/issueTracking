package com.fil.issueTracking.payLoad;

import lombok.Data;

@Data
public class UpdateUserDetailRequest {
	String role,gender,managerId;
}
