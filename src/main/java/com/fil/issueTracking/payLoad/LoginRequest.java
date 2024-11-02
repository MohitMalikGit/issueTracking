package com.fil.issueTracking.payLoad;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
	@NotEmpty(message="username can't be empty")
	@Size(min = 3 , message="username must contain atleast 3 characters!")
	String userid;
	 @NotEmpty(message = "Password cannot be empty")
	 String password;
	
}
