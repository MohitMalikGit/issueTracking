package com.fil.issueTracking.exception;

public class UserNameAndPasswordNotMatchedException extends RuntimeException {
	
	public UserNameAndPasswordNotMatchedException() {
		super("username and password does not match");
	}
}
