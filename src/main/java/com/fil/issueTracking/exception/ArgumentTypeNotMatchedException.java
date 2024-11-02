package com.fil.issueTracking.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArgumentTypeNotMatchedException extends RuntimeException {
	String argumentName;
	String argumentType;
	public ArgumentTypeNotMatchedException(String argumentName ,String argumentType) {
		super( argumentName + " must be of Type " + argumentType );
	}
}
